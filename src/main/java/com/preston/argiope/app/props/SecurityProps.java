package com.preston.argiope.app.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.constant.AppConstants;

@ConfigurationProperties(prefix=AppConstants.PropertyKeys.Security.PREFIX)
@Component
public class SecurityProps {
	private Boolean requireHttps;
	
	public Boolean getRequireHttps() {
		return requireHttps;
	}
	public void setRequireHttps(Boolean requireHttps) {
		this.requireHttps = requireHttps;
	}
}