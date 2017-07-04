package com.preston.argiope.app.config.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Used to dynamically add access rules based on environment.
 * For example only mapping dev URLs in a dev environment.
 * @author pbriggs
 *
 */
public interface AdditionalDomainPathUrlMapper {
	
	void addAccessControlRules(ExpressionUrlAuthorizationConfigurer<HttpSecurity>
			.ExpressionInterceptUrlRegistry authRequests) throws Exception;
	
}
