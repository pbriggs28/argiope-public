package com.preston.argiope.app.constant;

import java.util.HashSet;
import java.util.Set;

import com.preston.argiope.app.constant.AppConstants.DataStore.Tables;
import com.preston.argiope.controller.AbstractController;
import com.preston.argiope.controller.AbstractController.Model;
import com.preston.argiope.service.user.RoleServiceImpl.RoleConstant;

/**
 * <h3>Class Responsibilities</h3>
 * <p>
 * Define constants within various 'groups' by creating inner classes. No
 * constants should be defined at the top level of this class.
 * </p>
 * <p>
 * If a constant 'belongs' to one class (Ex: the {@link AbstractController} truly
 * <b>owns</b> the {@link Model} constants etc.) then the constant <b>should be
 * defined in that class</b>. This constant container is only for constants that
 * are global in nature and are meant to be used by many classes without one
 * true 'owner'.
 * </p>
 * <h3>Why do we use nested classes?</h3>
 * <p>
 * We use nested classes to separate our constants into groups so they are both
 * physically organized next to each in the file as well as to help narrow down
 * results during content assist. Instead of content assist return every single
 * constant in this class, it just returns the top level classes. As soon as you
 * select one, it narrows down just to its inner classes or constants and so on.
 * </p>
 * <h3>Why are the class names plural?</h3>
 * <p>
 * These classes are not meant to be instantiated (in fact most of them cannot
 * be, see below for more info). They are meant to be <b>containers</b> of
 * constants. For instance, there will never be more than one {@link Tables}
 * class, it is not representative of one Table. The inner classes should
 * instead be thought of almost as 'members' of one object (the static
 * {@link AppConstants} class).
 * </p>
 * <h3>Restricting instantiation</h3>
 * <p>
 * Besides a couple classes needing some static initialization, none of these
 * classes are able to be instantiated. This is accomplished by defining a
 * private constructor for the top level {@link AppConstants} class and making
 * sure no inner class is static.
 * </p>
 * <h3>Constant Interface Anti-Pattern</h3>
 * <p>
 * Do not turn this class into an interface to "implement" in other classes so
 * you can access these constants without the class name. This is an anti
 * pattern according to <a href=
 * "https://books.google.com/books?id=ka2VUBqHiWkC&pg=PA98&lpg=PA98&dq=effective+java+constant+interface+antipattern&source=bl&ots=yZLiJjn0QZ&sig=-96ZGBjS6lfjoD_3_pzjGmyb5tY&hl=en&sa=X&ved=0ahUKEwjMutO9ssDTAhURHGMKHW43Aa8Q6AEIgQEwEQ#v=onepage&q=effective%20java%20constant%20interface%20antipattern&f=false">
 * Effective Java - Item 19</a>. If you want to get the same result use a static
 * import such as <b>import static com.ydg.p3.app.AppConstants.*</b>. Note that
 * organizing imports (ctrl + shift + o) will 'fix' these imports (replace them
 * with individual imports).
 * </p>
 * 
 * @author pbriggs
 *
 */
@SuppressWarnings(value="hiding")
public class AppConstants {
	private AppConstants() {
		// Disallow instantiation of AppConstants and ALL of its inner classes
		// (Unless they are static classes like AppConstants.Users.Dev since
		// we need to perform some static initialization.
	}

	// Environment Constants
	// ====================================================================================================
	public class Profiles {
		public static final String PROD = "prod";
		public static final String DEV = "dev";
		public static final String DEV_EMBEDDED_SERVER = "dev-embedded-server";
		public static final String DEV_EMBEDDED_DB = "dev-embedded-db";
		public static final String DEV_EMBEDDED_DB_CONSOLE = "dev-h2-console";
		public static final String DEV_PHYSICAL_DB = "dev-physical-db";
		
		public static final String NOT_PROD = "!" + Profiles.PROD;
		public static final String NOT_DEV = "!" + Profiles.DEV;
		public static final String NOT_DEV_EMBEDDED_SERVER = "!" + Profiles.DEV_EMBEDDED_SERVER;
		public static final String NOT_DEV_EMBEDDED_DB = "!" + Profiles.DEV_EMBEDDED_DB;
		public static final String NOT_DEV_EMBEDDED_DB_CONSOLE = "!" + Profiles.DEV_EMBEDDED_DB_CONSOLE;
		public static final String NOT_DEV_PHYSICAL_DB = "!" + Profiles.DEV_PHYSICAL_DB;
	}
	
	public class Order {
		public class InitializerOrders {
			public static final int PROPERTY_VALUE_LOGGER = 1;
			public static final int ROLE_VALIDATOR = 2;
			public static final int DEV_USER_INITIALIZER = 3;
		}
	}
	
	public class Directory {
		public static final String STATIC_RESOURCES = "/resources/";
	}
	
	public class PropertyKeys {
		public static final String PREFIX = "com.preston.argiope";

		public class App {
			public static final String PREFIX = PropertyKeys.PREFIX + ".app";
		}

		public class Servlet {
			public static final String PREFIX = PropertyKeys.PREFIX + ".servlet";
		}
		public class Security {
			public static final String PREFIX = PropertyKeys.PREFIX + ".sec";
			public static final String REQUIRE_HTTPS = PREFIX + ".require-https";
			public static final String DISABLE_CSRF = PREFIX + ".disable-csrf";
			public static final String IGNORE_USERNAME_CASE = PREFIX + ".ignore-username-case";
		}
			
		public class DataSource {
			public static final String PREFIX =  PropertyKeys.PREFIX + ".datasource";
		}

		public class Dev {
			public static final String PREFIX =  PropertyKeys.PREFIX + ".dev";
		}
		
		// TODO: Move these to a "Logging" subclass
		public static final String LOG_PROPERTY_SOURCES = PREFIX + ".log.prop.sources";
		public static final String LOG_SPRING_RESOLVED_PROPERTIES = PREFIX + ".log.spring.resolved.props";
	}

	// Development Constants
	// TODO: Move this to the dev package
	// ====================================================================================================
	public static class Users {
		/**
		 * A user created only when the dev profile is active.
		 */
		public static class Dev {
			public static final String USERNAME = "sa";
			public static final String PASSWORD = "sa";
			public static final String FIRST_NAME = "PasswordIs";
			public static final String LAST_NAME = "sa";
			public static final Set<RoleConstant> ROLE_LIST;
			static {
				ROLE_LIST = new HashSet<>();
				ROLE_LIST.add(RoleConstant.USER);
				ROLE_LIST.add(RoleConstant.ADMIN);
			}
		}
		/**
		 * A user created only when the dev profile is active.
		 */
		public static class AutomationTesting {
			// TODO: Get from other constant class
			public static final String USERNAME = "AutomationTesting01";
			public static final String PASSWORD = "testpassword01";
			public static final String FIRST_NAME = "Automation";
			public static final String LAST_NAME = "Testing";
			public static final Set<RoleConstant> ROLE_LIST;
			static {
				ROLE_LIST = new HashSet<>();
				ROLE_LIST.add(RoleConstant.USER);
				ROLE_LIST.add(RoleConstant.ADMIN);
			}
		}
	}

	// DAO Contants
	// ====================================================================================================
	public static class DataStore {
		public class Schemas {

			/**
			 * IMPORTANT: If one table defines a schema (static.role) and others do not,
			 * the order in which the tables/keys are dropped by hibernate gets messed up.
			 * So since we are defining a schema for role (static schema) we MUST define
			 * a schema for every model class.
			 */
			private static final String DEFAULT = "dbo";
			private static final String AUDIT = "audit";
			private static final String STATIC = "static";
			
		}
		// TODO: Remove 'Tables' class and add 'Table' suffix. Ex: UserTable, RoleTable, etc.
		// This way when we organize imports for model classes it won't conflict with this constant.
		public class Tables {
			public class User {
				public static final String TABLE_SCHEMA = Schemas.DEFAULT;
				public static final String TABLE_NAME = "account";
				public static final String TABLE_SEQUENCE = "dbo.seq_test1";
				public static final String SEQUENCE_GENERATOR_NAME = "account-sequence-generator";
				public static final String SEQUENCE_ALLOCATION_SIZE = DefaultSequenceSizes.SMALL;
				public class Columns {
					public static final String USER_ID = "account_id";
					public static final String USERNAME = "username";
					public static final String PASSWORD = "password";
					public static final String FIRST_NAME = "first_name";
					public static final String LAST_NAME = "last_name";
					public static final String ENABLED = "enabled";
					public static final String NOT_EXPIRED = "not_expired";
					public static final String NOT_LOCKED = "not_locked";
					public static final String CREDENTIALS_NOT_EXPIRED = "credentials_not_expired";
				}
			}

			public class Role {
				public static final String TABLE_SCHEMA = Schemas.STATIC;
				public static final String TABLE_NAME = "role";
				public class Columns {
					public static final String ROLE_ID = "role_id";
					public static final String ROLE_NAME = "role_name";
					public static final String DESCRIPTION = "description";
				}
			}

			public class UserRoleMap {
				public static final String TABLE_SCHEMA = Schemas.DEFAULT;
				public static final String TABLE_NAME = "account_role_map";
				public class Columns {
					public static final String USER_ID = "account_id";
					public static final String ROLE_ID = "role_id";
				}
			}
			
			public class MethodPerformanceTestingResultTable {
				public static final String TABLE_SCHEMA = Schemas.AUDIT;
				public static final String TABLE_NAME = "performance_monitoring_result";
				public class Columns {
					public static final String PERFORMANCE_MONITORING_RESULT_ID = "performance_monitoring_result_id";
					public static final String RECORDED_BY = "recorded_by";
					public static final String PACKAGE_NAME = "package_name";
					public static final String CLASS_NAME = "class_name";
					public static final String METHOD_NAME = "method_name";
					public static final String RUNTIME_MILLIS = "runtime_millis";
					public static final String RUNTIME_DATE = "runtime_date";
				}
			}
		}
		
		public  class DefaultSequenceSizes {
			/** Used for infrequent inserts such as creating a new user. */
			public static final String SMALL = "5";
			
			/** Used for semi-frequent inserts such as a user creating new tasks. */
			public static final String MEDIUM = "50";
			
			// Uncomment if needed
//			public static final int LARGE = 200;
//			/** Used for large batch-type items */
//			public static final int XL = 1000;
		}
		
		public static class StaticData {
			public static final Set<RoleConstant> defaultRoleList = new HashSet<>();
			static { defaultRoleList.add(RoleConstant.USER); }
			
			public class Role {
				public class User {
					public static final String KEY = "ROLE_USER";
					public static final int ID = 1;
				}
				public class Admin {
					public static final String KEY = "ROLE_ADMIN";
					public static final int ID = 2;
				}
			}
		}
	}
}
