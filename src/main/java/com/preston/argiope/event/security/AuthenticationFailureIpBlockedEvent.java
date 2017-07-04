package com.preston.argiope.event.security;

import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.preston.argiope.service.security.CustomAuthenticationProvider;

/**
 * Event thrown by
 * {@link CustomAuthenticationProvider#authenticate(Authentication)} if an IP
 * address has been blocked.
 */
public class AuthenticationFailureIpBlockedEvent extends AbstractAuthenticationFailureEvent {
	private static final long serialVersionUID = 2961302483857623115L;

	public AuthenticationFailureIpBlockedEvent(Authentication authentication, AuthenticationException exception) {
		super(authentication, exception);
	}

}
