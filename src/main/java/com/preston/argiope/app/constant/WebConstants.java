package com.preston.argiope.app.constant;

import org.springframework.web.bind.annotation.RequestMapping;

import com.preston.argiope.app.constant.WebConstants.RequestMappings.Static;

/**
 * See {@link AppConstants} javadocs for an overview. See inner class
 * javadocs for specific details.
 * @author pbriggs
 *
 */
public class WebConstants {
	/**
	 * <p>
	 * Contains URLs of the application in a hierarchical representation.
	 * </p>
	 * <h2>Using these constants</h2>
	 * <p>
	 * Due to the hierarchical nature of these constants, referencing them can
	 * be rather verbose. <br/>
	 * <br/>
	 * <b>Example:</b>
	 * <code>WebConstants.RequestMappings.Secured.Admin.EditUsers.Pages.DELETE_USER</code><br/>
	 * <br/>
	 * To reduce the length of the statements you can import the
	 * {@link RequestMappings} class:<br/>
	 * <br/>
	 * <code>import com.preston.argiope.app.constant.WebConstants.RequestMappings;</code><br/>
	 * <br/>
	 * <b>Example:</b> <code>RequestMappings.Secured.Pages.HOME</code><br/>
	 * <br/>
	 * If the scenario makes it very clear you are referencing a request mapping
	 * (for example within an @RequestMapping annotation) you may import down to
	 * the top level request mapping domains:<br/>
	 * <br/>
	 * <code>import com.preston.argiope.app.constant.WebConstants.RequestMappings.Annonymous;</code><br/>
	 * <code>import com.preston.argiope.app.constant.WebConstants.RequestMappings.Secured;</code><br/>
	 * <br/>
	 * <b>Example:</b> <code>Secured.Pages.HOME</code><br/>
	 * </p>
	 * 
	 * <h2>Special Cases</h2>
	 * The {@link RequestMappings.Static} does not follow the rules defined here. This is
	 * because it is used in static resources (css/js) and must be 'hard coded'. If this
	 * domain referenced the {@link RequestMappings#APP_ROOT_PATH} then changing the
	 * {@link RequestMappings#APP_ROOT_PATH} would break all static references.
	 *  
	 * <h2>Composition</h2>
	 * <p>
	 * A request URL path is comprised of:
	 * <ul>
	 * <li>Application root path</li>
	 * <ul>
	 * <li>Ex: <b>/Argiope/</b></li>
	 * <li>Defined by: {@link RequestMappings#APP_ROOT_PATH}</li>
	 * <li>This can be used to quickly change an application's base URL</li>
	 * </ul>
	 * <li>Path domains</li>
	 * <ul>
	 * <li>All pages are grouped into 'domains'</li>
	 * <li>Domains can have nested domains</li>
	 * <li>Ex: <b>Secure/</b> with the nested domain <b>Admin/</b> with the
	 * nested domain <b>EditUsers/</b></li>
	 * </ul>
	 * <li>Page</li>
	 * <ul>
	 * <li>This is the last part of the URL before the query string</li>
	 * <li>Used in @{@link RequestMapping} to identify a unique page</li>
	 * <li>Ex: <b>DeleteUser</b></li>
	 * <li>Represented in the <code>Pages</code> inner class of each domain</li>
	 * </ul>
	 * 
	 * </ul>
	 * </p>
	 * <p>
	 * The examples above would form the URL
	 * <b>/Argiope/Secure/Admin/EditUsers/DeleteUser</b>
	 * </p>
	 * <h2>Parent Reference</h2>
	 * <p>
	 * Each level needs a reference to its parent's full path to make its own
	 * full path available to all children. This is done by each level just
	 * getting the reference to its direct parent. Taking the path
	 * <b>/Argiope/Secure/Admin/EditUsers/DeleteUser</b> as an example, the
	 * DeleteUser <b>page</b> has a reference to the <b>EditUsers</b> domain
	 * which has a reference to its parent domain <b>Admin</b> which has a
	 * reference to its parent domain <b>Secure</b> which has a reference to the
	 * root path <b>Argiope</b>.
	 * </p>
	 * <p>
	 * <b>/Argiope/ <- Secure/ <- Admin/ <- EditUsers/ <- DeleteUser</b>
	 * </p>
	 * <p>
	 * This is important because it allows us to easily change the URL structure
	 * if needed.
	 * </p>
	 * <h2>Inclusion/Exclusion of Leading/Trailing Slashes</h2>
	 * <p>
	 * <ul>
	 * <li>The {@link RequestMappings#APP_ROOT_PATH} must <b>include</b> the
	 * leading slash <b>and</b> the trailing slash</li>
	 * <ul>
	 * <li>This is the only component to <b>include</b> the leading slash</li>
	 * <li>Ex: <b>/Argiope/</b></li>
	 * </ul>
	 * <li>Domains must <b>exclude</b> the leading slash (managed by the parent)
	 * and <b>include</b> the trailing slash</li>
	 * <ul>
	 * <li>Ex: <b>Secure/</b>
	 * </ul>
	 * <li>Pages must <b>exclude</b> both the leading slash (managed by the
	 * parent) and the trailing slash</li>
	 * <ul>
	 * <li>Ex: <b>DeleteUser</b>
	 * </ul>
	 * </ul>
	 * </p>
	 */
	public static class RequestMappings {
		/** Can be used to quickly change an application's base URL */
		public static final String APP_ROOT_PATH 			= "/";

		/** 
		 * If updating this constant <b>the static resource links must be updated too.</b><br/>
		 * <br/>
		 * This is a special case that does not follow the rules of {@link RequestMappings}
		 * constant class. See the {@link RequestMappings} javadocs for more details.
		 * @author pbriggs
		 *
		 */
		public class Static {
			public static final String DOMAIN_PATH 	= "/Static/";
		}
		
		public class Annonymous {
			public static final String DOMAIN_PATH 	= APP_ROOT_PATH + "Public/";
			public class Pages {
				public static final String LOGIN 				= DOMAIN_PATH + "Login";	
				public static final String LOGIN_FAILURE		= LOGIN + "?" + QueryStringKeys.QRY_STR_LOGIN_ERROR;				
			}
		}
		
		public static class Secured {
			public static final String DOMAIN_PATH = APP_ROOT_PATH + "Secure/";
			public class Pages {
				public static final String HOME 			= DOMAIN_PATH + "Home";
				public static final String LOGOUT 			= DOMAIN_PATH + "Logout";
				public static final String LOGOUT_SUCCESS	= LOGOUT + "?" + QueryStringKeys.QRY_STR_LOGOUT_SUCCESS;
				public static final String UNATHORIZED		= DOMAIN_PATH + "403";				
			}

			public class Admin {
				public static final String DOMAIN_PATH = Secured.DOMAIN_PATH + "Admin/";
				public class Pages {
					public static final String HOME 			= DOMAIN_PATH + "Home";
				}
				public class EditUsers {
					public static final String DOMAIN_PATH 	= Admin.DOMAIN_PATH + "Users/";
					public class Pages {
						public static final String CREATE_USER 		= DOMAIN_PATH + "CreateUser";
						public static final String DISPLAY_USERS 	= DOMAIN_PATH + "DisplayUsers";
						public static final String DELETE_USER 		= DOMAIN_PATH + "DeleteUser";
					}
				}
			}
		}

		public static class Api {
			public static final String DOMAIN_PATH = APP_ROOT_PATH + "api/";
			public static class Dev {
				// TODO: Move IP Blocking to Dev and only configure in dev mode
			}
			public class EndPoints {
				public static final String RESET_IP_BLOCKING = DOMAIN_PATH + "reset-ip-blocking";
				
			}
			
		}
	}
	
	/** 
	 * A convenience location for accessing all security layers (URL 
	 * paths the determine access control).
	 * @author pbriggs
	 *
	 */
	public static class DomainSecurityLayers {
		public static final String API = RequestMappings.Api.DOMAIN_PATH;
		public static final String PUBLIC = RequestMappings.Annonymous.DOMAIN_PATH;
		public static final String STATIC_RESOURCES = Static.DOMAIN_PATH;
		public static final String SECURE = RequestMappings.Secured.DOMAIN_PATH;
		public static final String ADMIN = RequestMappings.Secured.Admin.DOMAIN_PATH;
		
	}

	public static class QueryStringKeys {
		public static final String QRY_STR_LOGIN_ERROR 		= "error";
		public static final String QRY_STR_LOGOUT_SUCCESS 	= "logout";
	}
}
