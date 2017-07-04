package com.preston.argiope.selenium.constant;

import com.preston.argiope.app.constant.WebConstants.RequestMappings.Annonymous;
import com.preston.argiope.app.constant.WebConstants.RequestMappings.Secured;
import com.preston.argiope.app.constant.dev.DevWebConstants.RequestMappings.Dev;

public class ArgiopeConstantTesting {
	// @formatter:off
	
	public static final long DRIVER_TIMEOUT = 60L;
	public static final String INCORRECT_USERNAME 					= "thisUserNameDoesntExist";
	public static final String INCORRECT_PASSWORD 					= "thisPasswordDoesntExist";
	public static final String NEW_USER_FIRST_NAME					= "NewFirst";
	public static final String NEW_USER_LAST_NAME					= "NewLast";
	public static final String NEW_USER_USERNAME					= "NewUsername";
	public static final String NEW_USER_PASSWORD					= "NewPassword"; 
	public static final String EMPTY_STRING = "";

	// TODO: Extract out base URL
	public static final String URL_ARGIOPE 						= "localhost:8080";
	public static final String URL_INDEX 						= URL_ARGIOPE + Secured.Pages.HOME;
	public static final String URL_LOGIN 						= URL_ARGIOPE + Annonymous.Pages.LOGIN;
	public static final String URL_LOGOUT 						= URL_ARGIOPE + Secured.Pages.LOGOUT;
	public static final String URL_CREATE_USER 					= URL_ARGIOPE + Secured.Admin.EditUsers.Pages.CREATE_USER;
	public static final String URL_DISPLAY_USERS				= URL_ARGIOPE + Secured.Admin.EditUsers.Pages.DISPLAY_USERS;
	public static final String URL_DELETE_USER 					= URL_ARGIOPE + Secured.Admin.EditUsers.Pages.DELETE_USER;
	public static final String URL_ADMIN 						= URL_ARGIOPE + Secured.Admin.Pages.HOME;
	public static final String URL_403	 						= URL_ARGIOPE + Secured.Pages.UNATHORIZED;
	public static final String URL_SYNCHRONIZE_TESTING_STATE	= URL_ARGIOPE + Dev.AutomationTesting.Pages.SYNCHRONIZE_TESTING_STATE;
	public static final String URL_RESET_IP_BLOCKING	 		= URL_ARGIOPE + Dev.AutomationTesting.Pages.RESET_IP_BLOCKING;
	//@formatter:on
	
}