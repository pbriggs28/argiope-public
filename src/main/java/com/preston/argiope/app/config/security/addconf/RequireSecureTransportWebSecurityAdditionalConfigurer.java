package com.preston.argiope.app.config.security.addconf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.config.security.WebSecurityAdditionalConfigurer;
import com.preston.argiope.app.props.SecurityProps;

/**
 * Force HTTPS.
 */
@Component
public class RequireSecureTransportWebSecurityAdditionalConfigurer implements WebSecurityAdditionalConfigurer {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private SecurityProps secProps;
	
	@Override
	public void postProcessConfig(HttpSecurity http) throws Exception {
		if(secProps.getRequireHttps() != null && secProps.getRequireHttps() == true) {
			logger.debug("Requiring HTTPS transport.");
			http.requiresChannel().anyRequest().requiresSecure();
		}
	}
}
