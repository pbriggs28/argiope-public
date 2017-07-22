package com.preston.argiope.app.config.security.dev;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.config.security.SecurityWebAppConfigExtender;
import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.constant.dev.DevWebConstants;

/**
 * Allows access to /h2-console to query the embedded database. Use a blank
 * username and password.<br/>
 * <br/>
 * Ex: <b>localhost:8080/h2-console<b/>
 * 
 * @author pbriggs
 *
 */
@Component
@Profile(AppConstants.Profiles.DEV_EMBEDDED_DB_CONSOLE)
public class H2ConsoleSecurityWebAppConfig extends SecurityWebAppConfigExtender {
	private static final String H2_CONSOLE_PATH = DevWebConstants.RequestMappings.H2Console.DOMAIN_PATH + "**";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * By default you can access /h2-console but as soon as you try to login
		 * Spring Security will intercept due to csrf and header frame issues.
		 * These settings allow access past the h2 web console login screen
		 */
		http.headers().frameOptions().disable();
		http.csrf().disable();
		http.authorizeRequests().antMatchers(H2_CONSOLE_PATH).permitAll();
		super.configure(http);
	}

}
