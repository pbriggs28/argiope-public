package com.preston.argiope.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.preston.argiope.app.constant.WebConstants.RequestMappings.Secured;
import com.preston.argiope.exception.common.form.MissingRequiredFieldException;
import com.preston.argiope.exception.service.user.DeleteUserException;
import com.preston.argiope.exception.service.user.UserNotFoundException;
import com.preston.argiope.exception.service.user.UsernameAlreadyExistsException;
import com.preston.argiope.model.user.CreateUserForm;
import com.preston.argiope.model.user.User;
import com.preston.argiope.service.user.UserService;

@Controller
public class AdminController extends AbstractController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private UserService userService;
			
	@RequestMapping(Secured.Pages.UNATHORIZED)
	public ModelAndView unauthorizedPage(ModelMap model, HttpServletRequest req) {
		logger.info("UNAUTHORIZED: User [{}] is not permitted to access page [{}]. Forwarded to [{}].",
				super.userFromModel(model).getUsername(), super.forwardedFromUrl(req), Secured.Pages.UNATHORIZED);
		
		return new ModelAndView(AbstractController.Views.VIEW_403);
	}
	
	@RequestMapping(Secured.Admin.Pages.HOME)
	public ModelAndView adminPage() {
		return new ModelAndView(AbstractController.Views.VIEW_ADMIN);
	}

	@RequestMapping(value=Secured.Admin.EditUsers.Pages.CREATE_USER, method=RequestMethod.GET)
	public ModelAndView createUserPageGet() {
		return new ModelAndView(AbstractController.Views.VIEW_CREATE_USER, Spring.COMMAND, new CreateUserForm());
	}
	
	@RequestMapping(value=Secured.Admin.EditUsers.Pages.CREATE_USER, method=RequestMethod.POST)
	public ModelAndView createUserPagePost(@ModelAttribute(Spring.COMMAND) CreateUserForm createUserForm, ModelMap model) {
		ModelAndView mv = new ModelAndView();
		logger.debug("Attempting to create user from form: {}", createUserForm);
		
		User newUser;
		try {
			newUser = userService.createUserFromForm(createUserForm);
			Assert.notNull(newUser, "User returned from userService.createUserFromForm(...) cannot be null.");
			
			mv.setViewName(AbstractController.Views.VIEW_CREATE_USER);
			String createUserSuccessMsg = AbstractController.Model.Values.VAL_CREATE_USER_SUCCESS_MSG.replace(AbstractController.Model.Values.RPLC_USERNAME, newUser.getUsername());
			model.addAttribute(AbstractController.Model.Attributes.ATT_CREATE_USER_SUCCESS, createUserSuccessMsg);
			
			return mv;
		} catch (MissingRequiredFieldException e) {
			logger.debug("Cannot create user from [{}]. Form is missing fields. Msg: [{}]. Form [{}].", 
					CreateUserForm.class.getSimpleName(), e.getMessage(), createUserForm);
			
			// TODO: Handle this with Spring validation on the front end
			mv.setViewName(AbstractController.Views.VIEW_CREATE_USER);
			model.addAttribute(AbstractController.Model.Attributes.ATT_CREATE_USER_ERROR, AbstractController.Model.Values.VAL_CREATE_USER_ERROR_MSG_FIELDS_MISSING);
			
			return mv;
		} catch (UsernameAlreadyExistsException e) {
			logger.debug("Cannot create user from [{}]. Username [{}] already exists. Form [{}]. Msg: [{}].", 
					CreateUserForm.class.getSimpleName(), createUserForm.getUsername(), createUserForm, e.getMessage());
			
			mv.setViewName(AbstractController.Views.VIEW_CREATE_USER);
			model.addAttribute(AbstractController.Model.Attributes.ATT_CREATE_USER_ERROR, AbstractController.Model.Values.VAL_CREATE_USER_ERROR_MSG_ALREADY_EXISTS);
			
			return mv;
		}
	}
	
	@RequestMapping(value=Secured.Admin.EditUsers.Pages.DISPLAY_USERS)
	public ModelAndView displayUsersPage() {
		ModelAndView mv = new ModelAndView(AbstractController.Views.VIEW_DISPLAY_USERS);
		
		Iterable<User> listUsers = userService.getAllUsers();
		mv.addObject(AbstractController.Model.Attributes.ATT_LIST_USERS, listUsers);
		return mv;
	}
	
	// TODO: Why is this not a POST?? Change this...
	// TODO: Remove user ID from all code, we should be accessing the user by username, not DB id (Look up references to ATT_ID)
	@RequestMapping(value=Secured.Admin.EditUsers.Pages.DELETE_USER)
	public ModelAndView deleteUsersPage(@RequestParam(value=AbstractController.Model.Attributes.ATT_USERNAME) String username) { //, @RequestParam(value=ATT_ID) Long id) {
		ModelAndView mv = displayUsersPage();
		logger.debug("Attempting to delete user [{}].", username);

		
		try {
			User user = userService.getUser(username);
			if(user == null)
				/* Caught below */
				throw new UserNotFoundException(String.format("Cannot delete user with a username of [%s] as it does not exist.", 
						username));
			userService.deleteUser(user);
			
			// TODO: Change this .replace to use String.format or something similar.
			String successMsg = AbstractController.Model.Values.VAL_DELETE_USER_SUCCESS_MSG.replace(AbstractController.Model.Values.RPLC_USERNAME, username);
			mv.addObject(AbstractController.Model.Attributes.ATT_DELETE_USER_SUCCESS, successMsg);
			logger.debug("Successfully deleted user [{}].", username);
			
			return mv;
		} catch (UserNotFoundException e) {
			// TODO: Change this error message to say the user does not exist and to try again (page was probably stale)
			// TODO: Change this .replace to use String.format or something similar.
			String errorMsg = AbstractController.Model.Values.VAL_DELETE_USER_ERROR_MSG.replace(AbstractController.Model.Values.RPLC_USERNAME, username);
			mv.addObject(AbstractController.Model.Attributes.ATT_DELETE_USER_ERROR, errorMsg);
			logger.debug("Error deleting user: ", errorMsg);
			
			return mv;
		} catch (DeleteUserException e) {
			// TODO: Change this .replace to use String.format or something similar.
			String errorMsg = AbstractController.Model.Values.VAL_DELETE_USER_ERROR_MSG.replace(AbstractController.Model.Values.RPLC_USERNAME, username);
			mv.addObject(AbstractController.Model.Attributes.ATT_DELETE_USER_ERROR, errorMsg);
			logger.debug("Error deleting user: ", errorMsg);
			
			return mv;
		}
	}
}
