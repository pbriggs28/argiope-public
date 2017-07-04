package com.preston.argiope.listener.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.preston.argiope.event.security.AuthenticationFailureIpBlockedEvent;
import com.preston.argiope.service.security.LoginAttemptService;

@Component
public class AuthenticationFailureIpBlockedEventListener 
		implements ApplicationListener<AuthenticationFailureIpBlockedEvent>{
	
	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public void onApplicationEvent(AuthenticationFailureIpBlockedEvent event) {
		/* Currently not used. Instead, all authentication events are handled
		 * by AuthenticationFailureEventListener. Keeping this class here in
		 * case we need to implement some IP Blocking specific functionality here.
		 */
		
//		loginAttemptService.loginFailed();
	}
}