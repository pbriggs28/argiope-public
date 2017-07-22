package com.preston.argiope.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.preston.argiope.app.constant.AppConstants;
import com.preston.argiope.app.constant.WebConstants;
import com.preston.argiope.model.dev.IpBlockingResetRequest;
import com.preston.argiope.model.dev.IpBlockingResetResponse;
import com.preston.argiope.model.dev.IpBlockingResetResponse.IpBlockingResetResult;
import com.preston.argiope.service.security.LoginAttemptService;

@RestController
public class AutomationTestingRestController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private LoginAttemptService loginAttemptService;
	
	@RequestMapping(path=WebConstants.RequestMappings.Api.EndPoints.RESET_IP_BLOCKING, method=RequestMethod.POST)
	public IpBlockingResetResponse resetIpBlockingEndPoint(
			@RequestBody IpBlockingResetRequest ipResetReq,
			HttpServletRequest req) {
		
		IpBlockingResetResponse ipResetResp = new IpBlockingResetResponse();
		
		/* Authenticate request */
		if(!(AppConstants.Users.AutomationTesting.USERNAME.equals(ipResetReq.getUsername())
				&& AppConstants.Users.AutomationTesting.PASSWORD.equals(ipResetReq.getPassword())) ) {
			ipResetResp.setResult(IpBlockingResetResult.INVALID_CREDENTIALS);
			return ipResetResp;
		}

		/* Use IP provided if available, otherwise get from the current request */
		String ip = ipResetReq.getIp();
		if(ip == null || ip.trim().isEmpty())
			ip = loginAttemptService.getIp(req);
		
		ipResetResp.setIp(ip);
		
		/* Check if blocked */
		if(!loginAttemptService.ipBlocked(ip)) {
			ipResetResp.setResult(IpBlockingResetResult.IP_NOT_BLOCKED);
			return ipResetResp;
		}
		
		/* Unblock */
		logger.warn("Unblocking IP [{}] from web service call authenticated with username [{}].", ip, ipResetReq.getUsername());
		loginAttemptService.loginSucceeded(ip);
		
		/* Verify unblocked successfully */
		if(loginAttemptService.ipBlocked(ip)) {
			logger.warn("Error: IP [{}] was determined to be blocked, initiated IP block release successfully but IP is still blocked...");
			ipResetResp.setResult(IpBlockingResetResult.FAIL);
			return ipResetResp;
		}
		
		logger.debug("Successfully unblocked IP [{}].", ip);
		ipResetResp.setResult(IpBlockingResetResult.SUCCESS);
		return ipResetResp;
	}
}
