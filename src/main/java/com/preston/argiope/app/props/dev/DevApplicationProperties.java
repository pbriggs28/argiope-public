package com.preston.argiope.app.props.dev;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.props.sub.MethodPerformanceLoggingSettings;

@Component
@ConfigurationProperties(prefix=AppConstants.PropertyKeys.Dev.PREFIX)
public class DevApplicationProperties {
	private boolean logPropertySources;
	private boolean logSpringResolvedProperties;
	private MethodPerformanceLoggingSettings methodPerformanceLoggingSettings = new MethodPerformanceLoggingSettings();
	
	// TODO: Extract these out into a PropertySourceLogging class
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
	public MethodPerformanceLoggingSettings getMethodPerformanceLoggingSettings() {
		return methodPerformanceLoggingSettings;
	}
	public void setMethodPerformanceLoggingSettings(MethodPerformanceLoggingSettings methodPerformanceLoggingSettings) {
		this.methodPerformanceLoggingSettings = methodPerformanceLoggingSettings;
	}
}
