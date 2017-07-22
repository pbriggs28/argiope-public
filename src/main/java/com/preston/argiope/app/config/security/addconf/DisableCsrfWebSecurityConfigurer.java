package com.preston.argiope.app.config.security.addconf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.config.security.WebSecurityAdditionalConfigurer;
import com.preston.argiope.app.constant.AppConstants;

@Component
@ConditionalOnProperty(name=AppConstants.PropertyKeys.Security.DISABLE_CSRF, havingValue="true")
public class DisableCsrfWebSecurityConfigurer implements WebSecurityAdditionalConfigurer {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void postProcessConfig(HttpSecurity http) throws Exception {
		logger.debug("Disabling CSRF protection.");
		http.csrf().disable();
	}

}
