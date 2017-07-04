package com.preston.argiope.app.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.constant.AppConstants;

@ConfigurationProperties(prefix=AppConstants.PropertyKeys.Servlet.PREFIX)
@Component
public class ServletProps {
	private Integer httpPort;
	private Integer httpsPort;
	
	public Integer getHttpPort() {
		return httpPort;
	}
	public void setHttpPort(Integer httpPort) {
		this.httpPort = httpPort;
	}
	public Integer getHttpsPort() {
		return httpsPort;
	}
	public void setHttpsPort(Integer httpsPort) {
		this.httpsPort = httpsPort;
	}
}
