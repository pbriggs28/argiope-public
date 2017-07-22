package com.preston.argiope.app.constant.legacy;

import com.preston.argiope.app.constant.dev.DevWebConstants;
import com.preston.argiope.app.constant.dev.DevWebConstants.TestElement;

/**
 * ================================<br/>
 * THESE CONSTANTS HAVE BEEN MIGRATED<br/>
 * ================================<br/>
 * <br/>
 * The constants in this class have been migrated to
 * {@link DevWebConstants} to use the new hierarchical style
 * constants. The constants in this class are used by the JSP which cannot
 * access constants from inner classes. Until we change our view implementation
 * (Likely to Thymeleaf: DEV-46) or find a solution to reference inner class
 * constants in the JSP, the constants in this class will simply be a pointer to
 * the new style constants.
 * 
 * @author pbriggs
 *
 */
public class ArgiopeConstantTestElement {
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
	 * Steps to generate getters
	 * 1-Delete all getter/setters (Use outline)
	 * (IGNORE UNDERSCORES in for the following lines, they are there so they do not get replaced with find/replace)
	 * 2-Find/Replace 
	 * 		"p_ublic s_tatic f_inal" with "p_ublic f_inal" (removes static from all fields)
	 * 3-Generate getters (No setters)
	 * 4-Find/Replace (enable case sensitive) 
	 * 		"p_ublic f_inal" with "p_ublic s_tatic f_inal" (adds static back to all fields)
	 */	
	
	// Use this in any jsp: ${argiopeConstantTestElement.CLASS_ERROR_LOGIN_FORM_IP_BLOCKED}
	
	/* ~~~~~~~~~~~~~~~~~Pages~~~~~~~~~~~~~~~~~ */
	/*Must update jsp files if modifying these constants*/
	public static final String CLASS_PAGE_CREATE_USER 			= TestElement.Page.CREATE_USER; //"page-createuser";
	public static final String CLASS_PAGE_LOGIN 				= TestElement.Page.LOGIN; //"page-login";
	public static final String CLASS_PAGE_INDEX 				= TestElement.Page.INDEX; //"page-index";
	public static final String CLASS_PAGE_DELETE_USER			= TestElement.Page.DELETE_USER; //"page-deleteuser";
	public static final String CLASS_PAGE_DISPLAY_USER			= TestElement.Page.DISPLAY_USER; //"page-displayusers";
	public static final String CLASS_PAGE_ADMIN					= TestElement.Page.ADMIN; //"page-admin";

	/* ~~~~~~~~~~~~~~~~~Status Elements~~~~~~~~~~~~~~~~~ */
	/*These should only be in the footer inside the element with the class CLASS_AUTOM_TEST_ELEMS*/
	public static final String CLASS_AUTOM_TEST_ELEMS 					= TestElement.Container.CLASS; //"automation-testing-elements";
	public static final String CLASS_STATUS_USER_AUTH_TRUE 				= TestElement.Status.UserAuth.TRUE; //"user-authenticated-true";
	public static final String CLASS_STATUS_USER_AUTH_FALSE 			= TestElement.Status.UserAuth.FALSE; //"user-authenticated-false";
	/** This displays in client browser! Mask definition! */
	public static final String CLASS_STATUS_IP_BLOCKED 					= TestElement.Status.IP_BLOCKED; //"b34O3BIc4yV57khTxZRC";
	public static final String CLASS_STATUS_NUM_FAILED_LOGIN_ATTEMPTS 	= TestElement.Status.NUM_FAILED_LOGIN_ATTEMPTS; //"MXyI5oaDEyNSNMx4Pu1b";
	public static final String CLASS_STATUS_IP_BLOCKING_RESET			= TestElement.Status.IP_BLOCKING_RESET; //"woFgheJOI7y4WbRBHqv7";

	/* ~~~~~~~~~~~~~~~~~Elements~~~~~~~~~~~~~~~~~ */
	/*Must update jsp files if modifying these constants*/
	public static final String CLASS_ERROR_LOGIN_FORM 					= TestElement.Error.LOGIN_FORM; //"error-login-page-form";
	public static final String CLASS_ERROR_LOGIN_FORM_IP_BLOCKED		= TestElement.Error.LOGIN_FORM_IP_BLOCKED; //"error-login-page-form-ip-blocked";
	public static final String CLASS_ERROR_CREATE_USER_FORM 			= TestElement.Error.CREATE_USER_FORM; //"error-create-user";
	public static final String CLASS_ERROR_CREATE_USER_FORM_UNAUTH 		= TestElement.Error.CREATE_USER_FORM_UNAUTH; //"error-login-to-create-user";
	public static final String CLASS_ERROR_DELETE_USER 					= TestElement.Error.DELETE_USER; //"error-delete-user";
	public static final String CLASS_SUCCESS_LOGOUT 					= TestElement.Success.LOGOUT; //"success-logout";
	public static final String CLASS_SUCCESS_CREATE_USER		 		= TestElement.Success.CREATE_USER; //"success-create-user";
	public static final String CLASS_SUCCESS_DELETE_USER 				= TestElement.Success.DELETE_USER; //"success-delete-user";
	public static final String CLASS_INPUT_LOGIN_FORM_USERNAME 			= TestElement.LoginForm.Input.USERNAME; //"input-login-page-form-username";
	public static final String CLASS_INPUT_LOGIN_FORM_PASSWORD 			= TestElement.LoginForm.Input.PASSWORD; //"input-login-page-form-password";
	public static final String CLASS_INPUT_CREATE_USER_FORM_FIRST_NAME 	= TestElement.CreateUserForm.Input.FIRST_NAME; //"input-create-user-page-form-firstname";
	public static final String CLASS_INPUT_CREATE_USER_FORM_LAST_NAME 	= TestElement.CreateUserForm.Input.LAST_NAME; //"input-create-user-page-form-lastname";
	public static final String CLASS_INPUT_CREATE_USER_FORM_USERNAME 	= TestElement.CreateUserForm.Input.USERNAME; //"input-create-user-page-form-username";
	public static final String CLASS_INPUT_CREATE_USER_FORM_PASSWORD 	= TestElement.CreateUserForm.Input.PASSWORD; //"input-create-user-page-form-password";
	public static final String CLASS_TEXT_DISPLAY_USERS_USERNAME		= TestElement.DisplayUsersTable.USERNAME_CELL; //"text-display-users-username";
	public static final String CLASS_BUTTON_LOGOUT						= TestElement.Header.LOGOUT_BUTTON; //"link-logout";
	public static final String CLASS_BUTTON_LOGIN						= TestElement.LoginForm.Input.SUBMIT; //"link-login";
	public static final String CLASS_BUTTON_DISPLAY_USERS_DELETE		= TestElement.DisplayUsersTable.Input.DELETE; //"button-display-users-delete";
	public static final String CLASS_TABLE_DISPLAY_USERS				= TestElement.DisplayUsersTable.CLASS; //"table-display-users";

	// @formatter:on
	public String getCLASS_PAGE_CREATE_USER() {
		return CLASS_PAGE_CREATE_USER;
	}
	public String getCLASS_PAGE_LOGIN() {
		return CLASS_PAGE_LOGIN;
	}
	public String getCLASS_PAGE_INDEX() {
		return CLASS_PAGE_INDEX;
	}
	public String getCLASS_PAGE_DELETE_USER() {
		return CLASS_PAGE_DELETE_USER;
	}
	public String getCLASS_PAGE_DISPLAY_USER() {
		return CLASS_PAGE_DISPLAY_USER;
	}
	public String getCLASS_PAGE_ADMIN() {
		return CLASS_PAGE_ADMIN;
	}
	public String getCLASS_AUTOM_TEST_ELEMS() {
		return CLASS_AUTOM_TEST_ELEMS;
	}
	public String getCLASS_STATUS_USER_AUTH_TRUE() {
		return CLASS_STATUS_USER_AUTH_TRUE;
	}
	public String getCLASS_STATUS_USER_AUTH_FALSE() {
		return CLASS_STATUS_USER_AUTH_FALSE;
	}
	public String getCLASS_STATUS_IP_BLOCKED() {
		return CLASS_STATUS_IP_BLOCKED;
	}
	public String getCLASS_STATUS_NUM_FAILED_LOGIN_ATTEMPTS() {
		return CLASS_STATUS_NUM_FAILED_LOGIN_ATTEMPTS;
	}
	public String getCLASS_STATUS_IP_BLOCKING_RESET() {
		return CLASS_STATUS_IP_BLOCKING_RESET;
	}
	public String getCLASS_ERROR_LOGIN_FORM() {
		return CLASS_ERROR_LOGIN_FORM;
	}
	public String getCLASS_ERROR_LOGIN_FORM_IP_BLOCKED() {
		return CLASS_ERROR_LOGIN_FORM_IP_BLOCKED;
	}
	public String getCLASS_ERROR_CREATE_USER_FORM() {
		return CLASS_ERROR_CREATE_USER_FORM;
	}
	public String getCLASS_ERROR_CREATE_USER_FORM_UNAUTH() {
		return CLASS_ERROR_CREATE_USER_FORM_UNAUTH;
	}
	public String getCLASS_ERROR_DELETE_USER() {
		return CLASS_ERROR_DELETE_USER;
	}
	public String getCLASS_SUCCESS_LOGOUT() {
		return CLASS_SUCCESS_LOGOUT;
	}
	public String getCLASS_SUCCESS_CREATE_USER() {
		return CLASS_SUCCESS_CREATE_USER;
	}
	public String getCLASS_SUCCESS_DELETE_USER() {
		return CLASS_SUCCESS_DELETE_USER;
	}
	public String getCLASS_INPUT_LOGIN_FORM_USERNAME() {
		return CLASS_INPUT_LOGIN_FORM_USERNAME;
	}
	public String getCLASS_INPUT_LOGIN_FORM_PASSWORD() {
		return CLASS_INPUT_LOGIN_FORM_PASSWORD;
	}
	public String getCLASS_INPUT_CREATE_USER_FORM_FIRST_NAME() {
		return CLASS_INPUT_CREATE_USER_FORM_FIRST_NAME;
	}
	public String getCLASS_INPUT_CREATE_USER_FORM_LAST_NAME() {
		return CLASS_INPUT_CREATE_USER_FORM_LAST_NAME;
	}
	public String getCLASS_INPUT_CREATE_USER_FORM_USERNAME() {
		return CLASS_INPUT_CREATE_USER_FORM_USERNAME;
	}
	public String getCLASS_INPUT_CREATE_USER_FORM_PASSWORD() {
		return CLASS_INPUT_CREATE_USER_FORM_PASSWORD;
	}
	public String getCLASS_TEXT_DISPLAY_USERS_USERNAME() {
		return CLASS_TEXT_DISPLAY_USERS_USERNAME;
	}
	public String getCLASS_BUTTON_LOGOUT() {
		return CLASS_BUTTON_LOGOUT;
	}
	public String getCLASS_BUTTON_LOGIN() {
		return CLASS_BUTTON_LOGIN;
	}
	public String getCLASS_BUTTON_DISPLAY_USERS_DELETE() {
		return CLASS_BUTTON_DISPLAY_USERS_DELETE;
	}
	public String getCLASS_TABLE_DISPLAY_USERS() {
		return CLASS_TABLE_DISPLAY_USERS;
	}
}
