package com.preston.argiope.app.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebAppUtils {
	@Autowired
	private HttpServletRequest request;
	
	public String getClientIp() {
		/*
		 * "X-Forwarded-For" header is present for requests behind a proxy where 
		 *  request.getRemoteAddr() will return the proxy IP which is the same
		 *  for all users behind the proxy.
		 *  
		 *  X-Forwarded-For header: clientIpAddress, proxy1, proxy2
		 */
		String xfHeader = request.getHeader("X-Forwarded-For");
		if(xfHeader == null) {
			return request.getRemoteAddr();
		}
		
		return xfHeader.split(",")[0];
	}

}
