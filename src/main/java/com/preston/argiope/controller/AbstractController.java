package com.preston.argiope.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.constant.WebConstants.RequestMappings.Secured;
import com.preston.argiope.app.constant.dev.DevWebConstants;
import com.preston.argiope.app.constant.legacy.ArgiopeConstantSecurity;
import com.preston.argiope.app.constant.legacy.ArgiopeConstantTestElement;
import com.preston.argiope.app.constant.legacy.ArgiopeConstantUrl;
import com.preston.argiope.model.user.User;

@Controller
public abstract class AbstractController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private ConfigurableEnvironment env;
// @formatter:off
	
	public static class Views {
		public static final String VIEW_INDEX 				= "index";
		public static final String VIEW_LOGIN 				= "user/login";
		public static final String VIEW_CREATE_USER 		= "user/createuser";
		public static final String VIEW_DISPLAY_USERS		= "user/displayusers";
		public static final String VIEW_ADMIN 				= "admin/admin";
		public static final String VIEW_403 				= "403";
	}
	
	public static class Redirects {
		private static final String REDIRECT_PREFIX			= "redirect:";
		public static final String REDIRECT_HOME			= REDIRECT_PREFIX + Secured.Pages.HOME;
	}

	public static class Spring {
		public static final String COMMAND 					= "command";
		public static final String SPRING_WEB 				= "SpringWeb";		
	}

	public static class Model {
		public static class Attributes {
			public static final String ATT_MESSAGE 				= "message";
			public static final String ATT_LOGIN				= "login";
			public static final String ATT_LOGIN_ERROR 			= "loginError";
			public static final String ATT_LOGOUT_SUCCESS_MSG	= "logoutSuccessMsg";
			public static final String ATT_CREATE_USER_ERROR	= "createUserError";
			public static final String ATT_CREATE_USER_SUCCESS	= "createUserSuccess";
			public static final String ATT_DELETE_USER_ERROR	= "deleteUserError";
			public static final String ATT_DELETE_USER_SUCCESS	= "deleteUserSuccess";
			public static final String ATT_USERNAME 			= "username";
			public static final String ATT_USER					= "user";
			public static final String ATT_USER_NEW				= "newUser";
			public static final String ATT_LIST_USERS			= "listUsers";
			public static final String ATT_ID					= "id";
		}
		public static class Values {
			public static final String RPLC_USERNAME = "{username}";
			
			public static final String VAL_LOGIN_ERROR_MSG 						= "The username and/or password provided are incorrect.";
			public static final String VAL_LOGIN_SUCCESS_MSG 					= "Login successful!";
			public static final String VAL_LOGOUT_SUCCESS_MSG 					= "Successfully logged out."; 
			public static final String VAL_CREATE_USER_ERROR_MSG_ALREADY_EXISTS = "The username provided already exists.";
			public static final String VAL_CREATE_USER_ERROR_MSG_FIELDS_MISSING = "Please populate all required fields.";
			public static final String VAL_CREATE_USER_SUCCESS_MSG 				= "User " + RPLC_USERNAME + " created successfully.";
			public static final String VAL_DELETE_USER_SUCCESS_MSG 				= "User " + RPLC_USERNAME + " deleted successfully.";
			public static final String VAL_DELETE_USER_ERROR_MSG 				= "Error deleting user " + RPLC_USERNAME + ". Please contact your system administrator.";
		}
	}
// @formatter:on
	

	// Model Attributes (Will be added to every AbstractController request)
	// ====================================================================================================
	@ModelAttribute(name=Model.Attributes.ATT_USER)
	public User user() {
		User user = null;
		Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if("anonymousUser".equals(userPrincipal))
			return null;
		
		if(userPrincipal == null) {
			logger.warn("Failed retrieving user from SecurityContextHolder: User was not expected to be null.");
			return null;			
		}
		
		if( !(userPrincipal instanceof User) ) {
			logger.warn("Failed retrieving user from SecurityContextHolder: Expected user of type [{}] but instead found [{}].",
					User.class.getName(), userPrincipal.getClass().getName());
			return null;
		}
		user = (User) userPrincipal;
		/* Logs every single request. Turn on if needed. */
		logger.trace("Adding user to model: {}", user);
		
		return user;
	}
	
	@ModelAttribute
	protected ArgiopeConstantTestElement argiopeConstantTestElement() {
		return new ArgiopeConstantTestElement();
	}
	
	@ModelAttribute
	protected ArgiopeConstantUrl argiopeConstantUrl() {
		return new ArgiopeConstantUrl();
	}
	
	@ModelAttribute
	protected ArgiopeConstantSecurity argiopeConstantSecurity() {
		return new ArgiopeConstantSecurity();
	}
	
	@ModelAttribute(name="devUser")
	protected User devUser() {
		// TODO: There has to be a better way to check if dev profile is active (I'm on a plane and can't google this)
		boolean devProfActive = Arrays.asList(env.getActiveProfiles()).contains(AppConstants.Profiles.DEV);
				
		if(!devProfActive)
			return null;
		
		User devUser = new User();
		devUser.setUsername(AppConstants.Users.Dev.USERNAME);
		devUser.setPassword(AppConstants.Users.Dev.PASSWORD);
		
		return devUser;
	}
	
	@ModelAttribute(name="h2ConsoleUrl")
	protected String h2ConsoleUrl() {
		// TODO: There has to be a better way to check if h2-console profile is active (I'm on a plane and can't google this)
		boolean h2ConsoleProfActive = Arrays.asList(env.getActiveProfiles()).contains(AppConstants.Profiles.DEV_EMBEDDED_DB_CONSOLE);
				
		if(!h2ConsoleProfActive)
			return null;
		
		return DevWebConstants.RequestMappings.H2Console.DOMAIN_PATH;
	}
	
	/*Do not define @RequestMappings here, this is an Abstract class and will thus result in duplicate mappings*/

	
	
	// Controller Helper Methods
	// ====================================================================================================
	protected User userFromModel(ModelMap model) {
		Object userObj = model.get(AbstractController.Model.Attributes.ATT_USER);
		if(userObj == null)
			return null;
		
		if(!(userObj instanceof User)) {
			logger.warn("Failed retrieving user from model. Expected user of type [{}] but instead found [{}].",
					User.class.getName(), userObj.getClass().getName());
			return null;
		}
		User user = (User) userObj;
		
		return user;
	}
	
	protected String forwardedFromUrl(HttpServletRequest req) {
		String url = "URL_NOT_FOUND";
		Object urlObj = req.getAttribute("javax.servlet.forward.request_uri");
		if (urlObj instanceof String)
			url = (String) urlObj;

		return url;
	}

	protected ModelAndView redirctToHomePage() {
		return new ModelAndView(AbstractController.Redirects.REDIRECT_HOME);
	}

	protected ModelAndView forwardToHomePage() {
		return new ModelAndView(AbstractController.Views.VIEW_INDEX);
	}
}
