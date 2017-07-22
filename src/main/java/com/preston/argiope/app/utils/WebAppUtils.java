package com.preston.argiope.app.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class WebAppUtils {
	
	public String resolveIp(HttpServletRequest req) {
		String ip = interrogateProxyRequestIp(req);
		
		if(ip == null)
			ip = req.getRemoteAddr();
		
		return ip;
	}


	// Private Helper Methods
	// ====================================================================================================

	/**
	 * Attempt to find the IP address behind a proxy. Returns null if
	 * this request is not behind a proxy or the IP address could not
	 * be resolved.<br/>
	 * <br/>
	 * "X-Forwarded-For" header is present for requests behind a proxy where 
	 *  request.getRemoteAddr() will return the proxy IP which is the same
	 *  for all users behind the proxy.<br/>
	 *  <br/>
	 *  Ex: <b>X-Forwarded-For header: clientIpAddress, proxy1, proxy2</b>
	 * 
	 * @param req
	 * @return
	 */
	private String interrogateProxyRequestIp(HttpServletRequest req) {
		String xfHeader = req.getHeader("X-Forwarded-For");
		if(xfHeader == null)
			return null;
		
		return xfHeader.split(",")[0];
	}
}
