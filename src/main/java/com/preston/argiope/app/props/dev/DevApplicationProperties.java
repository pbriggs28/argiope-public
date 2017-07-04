package com.preston.argiope.app.props.dev;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.constant.AppConstants;

@Component
@ConfigurationProperties(prefix=AppConstants.PropertyKeys.PREFIX)
public class DevApplicationProperties {
	private boolean logPropertySources;
	private boolean logSpringResolvedProperties;
	
	public boolean isLogPropertySources() {
		return logPropertySources;
	}
	public void setLogPropertySources(boolean logPropertySources) {
		this.logPropertySources = logPropertySources;
	}
	public boolean isLogSpringResolvedProperties() {
		return logSpringResolvedProperties;
	}
	public void setLogSpringResolvedProperties(boolean logSpringResolvedProperties) {
		this.logSpringResolvedProperties = logSpringResolvedProperties;
	}
}
