package com.preston.argiope.app.constant.legacy;

import com.preston.argiope.app.constant.WebConstants;
import com.preston.argiope.app.constant.WebConstants.RequestMappings.Annonymous;
import com.preston.argiope.app.constant.WebConstants.RequestMappings.Secured;
import com.preston.argiope.app.constant.WebConstants.RequestMappings.Static;
import com.preston.argiope.app.constant.dev.DevWebConstants.RequestMappings.Dev;

public class ArgiopeConstantUrl {
// @formatter:off
	/*
	 * ~~~~~This comment is used in any ArgiopeConstant Class that is used in jsp pages. Be sure to update all if modifying comment~~~~~
	 * 
	 * This class is used by the jsp pages so it needs getters
	 * that follow a specific naming convention. Eclipse'S "generate 
	 * getters/setters" function will change the case if the fields are final.
	 * 
	 * When adding new constants you will need to regenerate the getters. See steps below.
	 * 
	 * When modifying a constants name you will need to UPDATE THE JSP files. These will not
	 * automatically refactor. Use search before renaming and notate the references to be updated.
	 * 
	 * Steps to generate getters
	 * 1-Delete all getter/setters (Use outline)
	 * (Ignore underscores in for the following lines, they are there so they do not get replaced with find/replace)
	 * 2-Find/Replace 
	 * 		"p_ublic s_tatic f_inal" with "p_ublic f_inal" (removes static from all fields)
	 * 3-Generate getters (No setters)
	 * 4-Find/Replace (enable case sensitive) 
	 * 		"p_ublic f_inal" with "p_ublic s_tatic f_inal" (adds static back to all fields)
	 */	
	
	// Use this in any jsp: ${argiopeConstantUrl.URL_RESET_IP_BLOCKING}
	
	public static final String REQ_MAP_INDEX 						= Secured.Pages.HOME;
	public static final String REQ_MAP_LOGIN 						= Annonymous.Pages.LOGIN;
	public static final String REQ_MAP_LOGOUT 						= Secured.Pages.LOGOUT;
	public static final String REQ_MAP_CREATE_USER 					= Secured.Admin.EditUsers.Pages.CREATE_USER;
	public static final String REQ_MAP_DISPLAY_USERS 				= Secured.Admin.EditUsers.Pages.DISPLAY_USERS;
	public static final String REQ_MAP_DELETE_USER 					= Secured.Admin.EditUsers.Pages.DELETE_USER;
	public static final String REQ_MAP_ADMIN 						= Secured.Admin.Pages.HOME;
	public static final String REQ_MAP_403	 						= Secured.Pages.UNATHORIZED;
	public static final String DOMAIN_PATH_STATIC 					= Static.DOMAIN_PATH;
	/*Automation Testing (Must disguise on client side)*/
	public static final String REQ_MAP_SYNCHRONIZE_TESTING_STATE 	= Dev.AutomationTesting.Pages.SYNCHRONIZE_TESTING_STATE; // "/oitLNCgHVS7Vr84ZsI4E";
	public static final String REQ_MAP_RESET_IP_BLOCKING		 	= Dev.AutomationTesting.Pages.RESET_IP_BLOCKING; // "/0jia4vKbbD948dsNtoCr";
	

	public static final String QRY_STR_LOGIN_ERROR 		= WebConstants.QueryStringKeys.QRY_STR_LOGIN_ERROR;
	public static final String QRY_STR_LOGOUT_SUCCESS 	= WebConstants.QueryStringKeys.QRY_STR_LOGOUT_SUCCESS;
	
	public String getREQ_MAP_INDEX() {
		return REQ_MAP_INDEX;
	}
	public String getREQ_MAP_LOGIN() {
		return REQ_MAP_LOGIN;
	}
	public String getREQ_MAP_LOGOUT() {
		return REQ_MAP_LOGOUT;
	}
	public String getREQ_MAP_CREATE_USER() {
		return REQ_MAP_CREATE_USER;
	}
	public String getREQ_MAP_DISPLAY_USERS() {
		return REQ_MAP_DISPLAY_USERS;
	}
	public String getREQ_MAP_DELETE_USER() {
		return REQ_MAP_DELETE_USER;
	}
	public String getREQ_MAP_ADMIN() {
		return REQ_MAP_ADMIN;
	}
	public String getREQ_MAP_403() {
		return REQ_MAP_403;
	}
	public String getDOMAIN_PATH_STATIC() {
		return DOMAIN_PATH_STATIC;
	}
	public String getREQ_MAP_SYNCHRONIZE_TESTING_STATE() {
		return REQ_MAP_SYNCHRONIZE_TESTING_STATE;
	}
	public String getREQ_MAP_RESET_IP_BLOCKING() {
		return REQ_MAP_RESET_IP_BLOCKING;
	}
	public String getQRY_STR_LOGIN_ERROR() {
		return QRY_STR_LOGIN_ERROR;
	}
	public String getQRY_STR_LOGOUT_SUCCESS() {
		return QRY_STR_LOGOUT_SUCCESS;
	}

// @formatter:on


}
