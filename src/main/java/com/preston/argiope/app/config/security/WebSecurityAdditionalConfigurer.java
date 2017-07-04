package com.preston.argiope.app.config.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * See {@link SecurityWebAppConfig} javadocs for info.
 */
public interface WebSecurityAdditionalConfigurer {

	void postProcessConfig(HttpSecurity http) throws Exception;
	
}
