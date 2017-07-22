package com.preston.argiope.app.config.security.addconf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.config.security.WebSecurityAdditionalConfigurer;
import com.preston.argiope.app.constant.AppConstants;

/**
 * Force HTTPS.
 */
@ConditionalOnProperty(name=AppConstants.PropertyKeys.Security.REQUIRE_HTTPS)
@Component
public class RequireSecureTransportWebSecurityAdditionalConfigurer implements WebSecurityAdditionalConfigurer {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void postProcessConfig(HttpSecurity http) throws Exception {
		logger.debug("Requiring HTTPS transport.");
		http.requiresChannel().antMatchers("/api/**").requiresInsecure();
		http.requiresChannel().anyRequest().requiresSecure();
	}
}
