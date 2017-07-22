package com.preston.argiope.app.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.props.sub.MethodTraceLoggingSettings;

@Component
@ConfigurationProperties(prefix=AppConstants.PropertyKeys.App.PREFIX)
public class AppProps {
	// TODO: Move this to Dev props
	private MethodTraceLoggingSettings methodTraceLoggingSettings = new MethodTraceLoggingSettings();

	public MethodTraceLoggingSettings getMethodTraceLoggingSettings() {
		return methodTraceLoggingSettings;
	}

	public void setMethodTraceLoggingSettings(MethodTraceLoggingSettings methodTraceLoggingSettings) {
		this.methodTraceLoggingSettings = methodTraceLoggingSettings;
	}
}