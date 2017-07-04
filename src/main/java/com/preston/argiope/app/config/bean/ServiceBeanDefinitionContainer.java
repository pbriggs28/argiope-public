package com.preston.argiope.app.config.bean;

import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.preston.argiope.event.security.AuthenticationFailureIpBlockedEvent;
import com.preston.argiope.exception.service.security.IpBlockedException;

@Configuration
public class ServiceBeanDefinitionContainer {

	@Configuration
	public class SecurityBeanDefinitionContainer {
		
		@Bean
		public PasswordEncoder passwordEncoderBean() {
			return new BCryptPasswordEncoder();
		}

		@Bean
		public AuthenticationEventPublisher authenticationEventPublisherBean(ApplicationContext appCtx) {
			DefaultAuthenticationEventPublisher daep = new DefaultAuthenticationEventPublisher(appCtx);
			Properties mappings = new Properties();
			mappings.setProperty(IpBlockedException.class.getName(), AuthenticationFailureIpBlockedEvent.class.getName());
			daep.setAdditionalExceptionMappings(mappings);
			
			return daep;
		}
	}
}
