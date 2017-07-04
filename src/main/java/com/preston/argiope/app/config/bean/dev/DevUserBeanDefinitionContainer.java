package com.preston.argiope.app.config.bean.dev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.init.dev.DevUserInitializer;
import com.preston.argiope.model.user.dev.DevUser;

/**
 * Contains the @Bean definitions of {@link DevUser}s to create in a dev
 * environment. Examples include an automation testing user, an admin user to
 * login for the first time, etc. These users will be picked up by
 * {@link DevUserInitializer}.
 * 
 * @author pbriggs
 *
 */
@Profile(AppConstants.Profiles.DEV)
@Configuration
public class DevUserBeanDefinitionContainer {
	
	@Bean
	public DevUser devAdminUserBean() {
		DevUser devAdminUser = new DevUser();
		devAdminUser.setUsername(AppConstants.Users.Dev.USERNAME);
		devAdminUser.setPassword(AppConstants.Users.Dev.PASSWORD);
		devAdminUser.setFirstName(AppConstants.Users.Dev.FIRST_NAME);
		devAdminUser.setLastName(AppConstants.Users.Dev.LAST_NAME);
		devAdminUser.setRoles(AppConstants.Users.Dev.ROLE_LIST);
		
		return devAdminUser;
	}

	@Bean
	public DevUser automationTestingUserBean() {
		DevUser automationTestingUser = new DevUser();
		automationTestingUser.setUsername(AppConstants.Users.AutomationTesting.USERNAME);
		automationTestingUser.setPassword(AppConstants.Users.AutomationTesting.PASSWORD);
		automationTestingUser.setFirstName(AppConstants.Users.AutomationTesting.FIRST_NAME);
		automationTestingUser.setLastName(AppConstants.Users.AutomationTesting.LAST_NAME);
		automationTestingUser.setRoles(AppConstants.Users.AutomationTesting.ROLE_LIST);
		
		return automationTestingUser;
	}

}
