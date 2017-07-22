package com.preston.argiope.listener.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;

import com.preston.argiope.service.security.LoginAttemptService;

@Component
public class AuthenticationFailureEventListener 
		implements ApplicationListener<AbstractAuthenticationFailureEvent>{
	
	@Autowired private HttpServletRequest req;
	@Autowired private LoginAttemptService loginAttemptService;

	@Override
	public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
		loginAttemptService.loginFailed(req);
	}
}
