package com.preston.argiope.app.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.constant.AppConstants;

@ConfigurationProperties(prefix=AppConstants.PropertyKeys.Security.PREFIX)
@Component
public class SecurityProps {
	private Boolean requireHttps;
	private Boolean disableCsrf;
	
	/** Ignore the case of the username when attempting authentication. 
	 * When true: ("MyUsername" == "myusername") When false: ("MyUsername" != "myusername").
	 * Defaults to true. */
	private Boolean ignoreUsernameCase = true;
	
	public Boolean getRequireHttps() {
		return requireHttps;
	}
	public void setRequireHttps(Boolean requireHttps) {
		this.requireHttps = requireHttps;
	}
	public Boolean getDisableCsrf() {
		return disableCsrf;
	}
	public void setDisableCsrf(Boolean disableCsrf) {
		this.disableCsrf = disableCsrf;
	}
	public Boolean getIgnoreUsernameCase() {
		return ignoreUsernameCase;
	}
	public void setIgnoreUsernameCase(Boolean ignoreUsernameCase) {
		this.ignoreUsernameCase = ignoreUsernameCase;
	}
}