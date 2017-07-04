package com.preston.argiope.app.config.bean.dev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.preston.argiope.model.dev.ResetIpBlockingForm;

@Configuration
public class AutomationTestingBeanDefinitionContainer {
	
	@Bean
	public ResetIpBlockingForm resetIpBlockingFormBean() {
		return new ResetIpBlockingForm();
	}

}
