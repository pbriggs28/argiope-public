package com.preston.argiope.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.preston.argiope.app.constant.WebConstants;
import com.preston.argiope.app.constant.WebConstants.RequestMappings.Annonymous;
import com.preston.argiope.app.constant.WebConstants.RequestMappings.Secured;
import com.preston.argiope.model.user.User;
import com.preston.argiope.model.user.UserLoginForm;
import com.preston.argiope.service.user.UserService;

@Controller
public class UserController extends AbstractController{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private UserService userService;
	@Autowired private UserLoginForm userLoginForm;
	
	@RequestMapping(value=Annonymous.Pages.LOGIN, method=RequestMethod.GET)
	public ModelAndView loginPageGet(ModelMap model,  
			@RequestParam(value=WebConstants.QueryStringKeys.QRY_STR_LOGOUT_SUCCESS, required=false) String logoutSuccess,
			@RequestParam(value=WebConstants.QueryStringKeys.QRY_STR_LOGIN_ERROR, required=false) String loginError,
			HttpServletRequest req) {
		
		ModelAndView mv = new ModelAndView();
		
		/* Redirect authenticated users to home page */
		User user = (User) super.userFromModel(model);
		if(user != null) {
			String redirectTo = AbstractController.Redirects.REDIRECT_HOME;
			logger.debug("User [{}] is already authenticated. Redirecting from [{}] to [{}].",
					user.getUsername(), req.getRequestURI(), redirectTo);
			
			mv.setViewName(redirectTo);
			return mv;
		}

		mv.setViewName(AbstractController.Views.VIEW_LOGIN);
		mv.addObject(Spring.COMMAND, userLoginForm);
		
		// TODO Handle this with Spring
		/* Set error/success messages */
		if(loginError == "") {
			String msg = AbstractController.Model.Values.VAL_LOGIN_ERROR_MSG;
			logger.debug("Adding login error message to model: {}", msg);
			
			mv.addObject(AbstractController.Model.Attributes.ATT_LOGIN_ERROR, msg);
		} else if(logoutSuccess == "") {
			String msg = AbstractController.Model.Values.VAL_LOGOUT_SUCCESS_MSG;
			logger.debug("Adding logout success message to model: {}", msg);
			
			mv.addObject(AbstractController.Model.Attributes.ATT_LOGOUT_SUCCESS_MSG, msg);
		}
		
		return mv;
	}

	/** This should never be called. Handled by Spring. */
	@RequestMapping(value=Annonymous.Pages.LOGIN, method=RequestMethod.POST)
	public ModelAndView loginPagePost(HttpServletRequest req) {
		logger.error("POST requests to [{}] are expected to be handled by Spring. Requested path is [{}].", 
				Secured.Pages.LOGOUT, req.getRequestURI());
		
		return new ModelAndView("ThisShouldNeverBeCalled-showLoginPagePost");
	}
	
	/** Logout only allowed through POST. Redirect any direct URL calls to home page. */
	@RequestMapping(value=Secured.Pages.LOGOUT, method=RequestMethod.GET)
	public ModelAndView logoutPageGet(HttpServletRequest req) {
		String redirectUrl = AbstractController.Redirects.REDIRECT_HOME;
		logger.debug("Logout only permitted through POST. Redirecting from [{}] to [{}].", 
				req.getRequestURI(), redirectUrl);
		
		return new ModelAndView(redirectUrl);
	}
	
	/** This should never be called. Handled by Spring. */
	@RequestMapping(value=Secured.Pages.LOGOUT, method=RequestMethod.POST)
	public ModelAndView logoutPagePost(HttpServletRequest req) {
		logger.error("POST requests to [{}] are expected to be handled by Spring. Requested path is [{}].", 
				Secured.Pages.LOGOUT, req.getRequestURI());
		return new ModelAndView("ThisShouldNeverBeCalled-showLogoutPagePOST");
	}
}