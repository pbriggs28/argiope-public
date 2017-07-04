package com.preston.argiope.app.config.security.dev;

import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.config.security.AdditionalDomainPathUrlMapper;
import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.constant.legacy.ArgiopeConstantUrl;

@Profile(AppConstants.Profiles.DEV)
@Component
public class AutomationTestingDomainPathUrlMapper implements AdditionalDomainPathUrlMapper {

	@Override
	public void addAccessControlRules(ExpressionUrlAuthorizationConfigurer<HttpSecurity>
			.ExpressionInterceptUrlRegistry authRequests) throws Exception {
		
		// TODO: Change this to use an automation testing role
		authRequests.mvcMatchers(ArgiopeConstantUrl.REQ_MAP_RESET_IP_BLOCKING).permitAll();
	}
}
