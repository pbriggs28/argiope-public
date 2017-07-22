package com.preston.argiope.app.constant.dev;

import com.preston.argiope.app.constant.WebConstants;
import com.preston.argiope.app.constant.legacy.ArgiopeConstantUrl;

/**
 * This Class holds constant values for automation testing elements. These
 * element names are referenced both in automation test classes as well as jsp
 * files to retain synchronization when changing names. These class/id names
 * should <b>only</b> be used by the test classes. Do <b>not</b> use these class
 * names in css or javascript as this will break synchronization. <br/>
 * <br/>
 * Simply use <code>${argiopeConstantTestElement.CLASS_PAGE_ADMIN}</code> (as an
 * example to reference the class name of an element expected to be on the admin
 * page) in any jsp file to reference these values.<br/>
 * <br/>
 * <h2>Constant naming conventions.</h2> The prefixes "CLASS_" and "ID_" specify
 * the element attribute as a class or id attribute.<br/>
 * <br/>
 * The constants are sectioned into groups:
 * <ul>
 * <li>PAGE_
 * <ul>
 * <li>An element that should be present on the respective page. (e.g., an
 * element with the class {@link #CLASS_PAGE_CREATE_USER} should be an present
 * on the {@link ArgiopeConstantUrl#REQ_MAP_CREATE_USER} page. Commonly these
 * will be a direct correlation from the URL ("/CreateUser") to a lowercase
 * version with hyphens directly prior to capitals ("create-user") however this
 * is not gauranteed.</li>
 * </ul>
 * </li>
 * <li>STATUS_
 * <ul>
 * <li>An element that should be present (generally in the footer) under
 * specific situations (e.g., an element with the class
 * {@link #CLASS_STATUS_USER_AUTH_TRUE} should be an present on an page where
 * the user is authorized.</li>
 * </ul>
 * </li>
 * <li>Others
 * <ul>
 * <li>If none of these suffixes exist after "CLASS_" then the element is a true
 * element either to be asserted as present on the current page or to be used
 * (e.g., "BUTTON_", "INPUT_", etc.).</li>
 * </ul>
 * </li>
 * </ul>
 * Since this class is used in jsp files, it requires getters. Please see
 * comments in code if making modifications to this class as there are special
 * steps required.
 * @author pbriggs
 *
 */
public class DevWebConstants {

	public class RequestMappings {
		public class Dev {
			public static final String DOMAIN_PATH 			= WebConstants.RequestMappings.APP_ROOT_PATH + "dev/";
			public class AutomationTesting {
				public static final String DOMAIN_PATH 		= Dev.DOMAIN_PATH + "at/";
				public class Pages {
					/* Security entrance (Must disguise on client side) */
					public static final String SYNCHRONIZE_TESTING_STATE= DOMAIN_PATH + "oitLNCgHVS7Vr84ZsI4E";
					public static final String RESET_IP_BLOCKING		= DOMAIN_PATH + "0jia4vKbbD948dsNtoCr";						
				}
			}
		}
		
		public class H2Console {
			public static final String DOMAIN_PATH = "/h2-console/";
		}
	}

	public static class ResponseAttributes {
		/*The following section is sensitive and is displayed on client end and therefore must be disguised*/
		public static class IpBlocked {
			public static final String ATTRIBUTE = "wOsiHdYvSPMRDFPOk6ZV";
			public static final String TRUE = "HKnCNgNuM1yPdJnvC7tF";
			public static final String FALSE = "Hc2HJrzixMBcqv3WV8b0";
		}
		public static class FailedLoginCount {
			public static final String ATTRIBUTE = "UQhuO4qPnW08PKRSfR1L";
		}
		public static class IpBlockReset {
			public static final String ATTRIBUTE = "YcArkxP7jC5RdoU7pVmE";
			public static final String TRUE = "sCfu6iFsH8d4BU09lWcw";
			public static final String FALSE = "YuMutrjQt2FCh5P33Xby";
		}
	}
	
	public static final class TestElement {
		public static final class Page {
			public static final String CREATE_USER 		= "page-createuser";
			public static final String LOGIN 			= "page-login";
			public static final String INDEX 			= "page-index";
			public static final String DELETE_USER		= "page-deleteuser";
			public static final String DISPLAY_USER		= "page-displayusers";
			public static final String ADMIN			= "page-admin";
		}
		public static final class Container {
			public static final String CLASS			= "automation-testing-elements";
		}
		public static final class Status {
			public static final class UserAuth {
				public static final String TRUE = "user-authenticated-true";
				public static final String FALSE = "user-authenticated-false";
			}
			/** This displays in client browser! Mask definition! */
			public static final String IP_BLOCKED 					= "b34O3BIc4yV57khTxZRC";
			public static final String NUM_FAILED_LOGIN_ATTEMPTS 	= "MXyI5oaDEyNSNMx4Pu1b";
			public static final String IP_BLOCKING_RESET			= "woFgheJOI7y4WbRBHqv7";
		}
		public static final class Error {
			public static final String LOGIN_FORM 					= "error-login-page-form";
			public static final String LOGIN_FORM_IP_BLOCKED		= "error-login-page-form-ip-blocked";
			public static final String CREATE_USER_FORM 			= "error-create-user";
			public static final String CREATE_USER_FORM_UNAUTH 		= "error-login-to-create-user";
			public static final String DELETE_USER 					= "error-delete-user";
		}
		public static final class Success {
			public static final String LOGOUT 					= "success-logout";
			public static final String CREATE_USER		 		= "success-create-user";
			public static final String DELETE_USER 				= "success-delete-user";
		}
		public static final class LoginForm {
			public static final class Input {
				public static final String USERNAME	= "input-login-page-form-username";
				public static final String PASSWORD	= "input-login-page-form-password";
				public static final String SUBMIT	= "link-login";		
			}
		}
		public static final class CreateUserForm {
			public static final class Input {
				public static final String FIRST_NAME 	= "input-create-user-page-form-firstname";
				public static final String LAST_NAME 	= "input-create-user-page-form-lastname";
				public static final String USERNAME 	= "input-create-user-page-form-username";
				public static final String PASSWORD 	= "input-create-user-page-form-password";				
			}
		}
		public static final class DisplayUsersTable {
			public static final String CLASS 	= "table-display-users";
			public static final String USERNAME_CELL		= "text-display-users-username";
			public static final class Input {
				public static final String DELETE		= "button-display-users-delete";
			}
		}
		public static final class IpBlockingResetForm {
			public static final String CLASS 	= "P5esbtvNjfBxwgRDp0Dx";
			public static final class Input {
				public static final String SUBMIT		= "HsvywMoxs1xvFKg3h4u4";
			}
		}
		public static final class Header {
			public static final String LOGOUT_BUTTON = "link-logout";
		}
		
	}
}
