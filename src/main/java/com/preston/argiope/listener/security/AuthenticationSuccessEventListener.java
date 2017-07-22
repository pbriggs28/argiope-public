package com.preston.argiope.listener.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.preston.argiope.service.security.LoginAttemptService;

@Component
public class AuthenticationSuccessEventListener 
		implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired private HttpServletRequest req;
	@Autowired private LoginAttemptService loginAttemptService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		loginAttemptService.loginSucceeded(req);
	}
}
