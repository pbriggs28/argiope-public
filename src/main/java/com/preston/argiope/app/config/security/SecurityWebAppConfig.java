package com.preston.argiope.app.config.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.constant.WebConstants;
import com.preston.argiope.app.constant.WebConstants.RequestMappings;
import com.preston.argiope.handler.security.CustomAuthenticationFailureHandler;
import com.preston.argiope.handler.security.CustomAuthenticationSuccessHandler;
import com.preston.argiope.model.user.UserLoginForm;

/**
 * Configures security settings such as which URLs require what roles,
 * login/logout, exception handling, etc..<br/>
 * <br/>
 * <h1>Extending this configuration</h1>
 * 
 * <h3>1) Override configuration</h3> To override configuration such as
 * login/logout, extend the {@link SecurityWebAppConfigExtender} class and
 * override the appropriate method. Only one class can do this at a time.<br/>
 * <br/>
 * 
 * <h3>2) Add configuration</h3><br/>
 * To process any additional/optional configuration (such as requiring HTTPS)
 * wire up a {@link WebSecurityAdditionalConfigurer} bean. These will be
 * processed after the initial configuration has processed.<br/>
 * <br/>
 * 
 * <h3>3) Add URL mappings (Must not conflict with other paths)</h3><br/>
 * To add URL mappings at the root path that will not interfere with other
 * mappings (Ex: <b>/static/**</b>), define {@link AdditionalDomainPathUrlMapper} beans that will be
 * called before any mapping has occurred. See
 * {@link #addAdditionalDomainPathUrlMappings(HttpSecurity)} for more info.<br/>
 * <br/>
 * If the path might interfere see the next section.<br/>
 * 
 * <h3>4) Add URL mappings at specific points in the chain</h3><br/>
 * To change the URL mapping scheme see {@link #addUrlMappings(HttpSecurity)}.<br/>
 * <br/>
 * Use this if the path might interfere with default paths configured.<br/>
 * <br/>
 * 
 * @see WebSecurityAdditionalConfigurer
 * @author pbriggs
 * 
 */

/* Allow subclassing this bean to override methods. */
@ConditionalOnMissingBean(type="com.preston.argiope.app.config.security.SecurityWebAppConfigExtender")
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityWebAppConfig extends WebSecurityConfigurerAdapter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	@Autowired private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	@Autowired(required=false) private List<AdditionalDomainPathUrlMapper> urlMapperList;
	@Autowired(required=false) private List<WebSecurityAdditionalConfigurer> additionalConfigurerList;

	private static final String ADMIN_DOMAIN_PATH = WebConstants.DomainSecurityLayers.ADMIN + "**";
	private static final String SECURE_DOMAIN_PATH = WebConstants.DomainSecurityLayers.SECURE + "**";
	private static final String API_DOMAIN_PATH = WebConstants.DomainSecurityLayers.API + "**";
	private static final String STATIC_RESOURCE_DOMAIN_PATH = WebConstants.DomainSecurityLayers.STATIC_RESOURCES + "**";
	private static final String PUBLIC_DOMAIN_PATH = WebConstants.DomainSecurityLayers.PUBLIC + "**";
	
	private static final String ADMIN_ROLE = AppConstants.DataStore.StaticData.Role.Admin.KEY;

	private static final String LOGIN_URL = WebConstants.RequestMappings.Annonymous.Pages.LOGIN;
	private static final String HOME_URL = WebConstants.RequestMappings.Secured.Pages.HOME;
	private static final String LOGOUT_URL = WebConstants.RequestMappings.Secured.Pages.LOGOUT;
	private static final String LOGOUT_SUCCESS_URL = LOGIN_URL + "?" + WebConstants.QueryStringKeys.QRY_STR_LOGOUT_SUCCESS;
	private static final String UNAUTHORIZED_URL = WebConstants.RequestMappings.Secured.Pages.UNATHORIZED;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		addUrlMappings(http);
		configureLogin(http);
		configureLogout(http);
		configureExceptionHandling(http);
		processAdditionalConfigurers(http);
	}
	
	/**
	 * Secure URL paths to require specific roles, authentication, etc.<br/>
	 * <br/>
	 * The first URL pattern defined will win over any URL patterns defined
	 * later. For example if <b>/Admin/**</b> is defined and then
	 * <b>/Admin/SuperAdmin/**</b> is defined, the <b>/Admin/**</b> pattern
	 * would catch every URL that would normally be processed by the
	 * <b>/Admin/SuperAdmin**</b> pattern.<br/>
	 * <br/>
	 * Internally, this method calls three individual methods. If extending this class,
	 * any of these can be overridden to add mappings before/after or replace
	 * completely.
	 * <h3>Ordering</h3>
	 * <ul>
	 * <li>{@link #addAdditionalDomainPathUrlMappings(HttpSecurity)}</li>
	 * <ul>
	 * <li>This method is not meant to be overridden like the other methods
	 * here.</li>
	 * <li>Instead, define {@link AdditionalDomainPathUrlMapper} beans that will
	 * be called before any mapping.</li>
	 * <li>These should <b>only</b> add path domains that will not interfere
	 * with the apps top level domain paths.</li>
	 * <li>Ex: Currently the only top level paths are
	 * {@link RequestMappings.Annonymous#DOMAIN_PATH} and
	 * {@link RequestMappings.Secured#DOMAIN_PATH} so this could be used to add
	 * <b>/h2-console/**</b> as it does not interfere with either of those.</li>
	 * </ul>
	 * <li>{@link #addHighPriorityUrlMappings(HttpSecurity)}</li>
	 * <ul>
	 * <li>High priority mappings (Admin, etc.) or very 'narrow' mappings and
	 * therefore subject to be overridden by a 'wider' definition.</li>
	 * <li>Ex: <b>/Admin/**</b> will override <b>/Admin/SuperAdmin/**</b></li>
	 * </ul>
	 * <li>{@link #addLowPriorityUrlMappings(HttpSecurity)}</li>
	 * <ul>
	 * <li>Mappings that could possibly override other URLs but also have the
	 * potential to be overridden.</li>
	 * </ul>
	 * <li>{@link #addFinalUrlMappings(HttpSecurity)}</li>
	 * <ul>
	 * <li>Very wide URLs mappings that have no chance of being overridden (Ex:
	 * <b>/</b> or <b>.anyRequest()</b>) that should be processed last to avoid
	 * overriding other patterns.</li>
	 * </ul>
	 * </ul>
	 * 
	 * 
	 * @param http
	 * @throws Exception
	 */
	protected void addUrlMappings(HttpSecurity http) throws Exception {
		// TODO: Change these to mvcMatchers??
		addAdditionalDomainPathUrlMappings(http);
		addHighPriorityUrlMappings(http);
		addLowPriorityUrlMappings(http);
		addFinalUrlMappings(http);
	}

	protected void addAdditionalDomainPathUrlMappings(HttpSecurity http) throws Exception {
		if(urlMapperList == null || urlMapperList.isEmpty()) {
			logger.debug("No additional domain path URL mappers found of type [{}]. Skipping...", AdditionalDomainPathUrlMapper.class.getSimpleName());
			return;
		}
		logger.debug("Processing additional domain path URL mappers...");
		for(AdditionalDomainPathUrlMapper mapper : urlMapperList) {
			logger.debug("Processing additional domain path URL mapper [{}].", mapper.getClass().getSimpleName());
			mapper.addAccessControlRules(http.authorizeRequests());
		}
	}

	/**
	 * Refer to {@link #addUrlMappings(HttpSecurity)} for info.
	 * @param http
	 * @throws Exception
	 */
	protected void addHighPriorityUrlMappings(HttpSecurity http) throws Exception {
		logger.debug("Adding high priority URL mappings...");
		http.authorizeRequests().antMatchers(ADMIN_DOMAIN_PATH).hasAuthority(ADMIN_ROLE);
		http.authorizeRequests().antMatchers(SECURE_DOMAIN_PATH).authenticated();
	}

	/**
	 * Refer to {@link #addUrlMappings(HttpSecurity)} for info.
	 * @param http
	 * @throws Exception
	 */
	protected void addLowPriorityUrlMappings(HttpSecurity http) throws Exception {
		logger.debug("Adding low priority URL mappings...");
		// TODO: Add security to web services
		http.authorizeRequests().antMatchers(API_DOMAIN_PATH).permitAll();
		http.authorizeRequests().antMatchers(STATIC_RESOURCE_DOMAIN_PATH).permitAll();
		http.authorizeRequests().antMatchers(PUBLIC_DOMAIN_PATH).anonymous();
	}

	/**
	 * Refer to {@link #addUrlMappings(HttpSecurity)} for info.
	 * @param http
	 * @throws Exception
	 */
	protected void addFinalUrlMappings(HttpSecurity http) throws Exception {
		logger.debug("Adding final URL mappings...");
		http.authorizeRequests().anyRequest().authenticated();
		http.authorizeRequests().anyRequest().hasRole("USER");
	}
	
	protected void configureLogin(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage(LOGIN_URL)
			.defaultSuccessUrl(HOME_URL) /* Only used when you navigate to the login page directly */
			.usernameParameter(UserLoginForm.USERNAME_VARIABLE)
			.passwordParameter(UserLoginForm.PASSWORD_VARIABLE)
			.failureHandler(customAuthenticationFailureHandler)
			.successHandler(customAuthenticationSuccessHandler)
			.permitAll()
		;
	}

	protected void configureLogout(HttpSecurity http) throws Exception {
		http
			.logout()
			.logoutUrl(LOGOUT_URL)
			.logoutSuccessUrl(LOGOUT_SUCCESS_URL)
			.permitAll()
		;
	}

	protected void configureExceptionHandling(HttpSecurity http) throws Exception {
		http.exceptionHandling().accessDeniedPage(UNAUTHORIZED_URL);
	}

	protected void processAdditionalConfigurers(HttpSecurity http) throws Exception {
		if(additionalConfigurerList == null || additionalConfigurerList.isEmpty()) {
			logger.debug("No additional security configurers found of type [{}]. Skipping additional security configuration...", WebSecurityAdditionalConfigurer.class.getSimpleName());
			return;
		}
		logger.debug("Processing additional security configurers...");
		for(WebSecurityAdditionalConfigurer cfgr: additionalConfigurerList) {
			logger.debug("Processing security configurer [{}].", cfgr.getClass().getSimpleName());
			cfgr.postProcessConfig(http);
		}
	}
}
