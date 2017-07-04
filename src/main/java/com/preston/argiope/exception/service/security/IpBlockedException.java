package com.preston.argiope.exception.service.security;

import org.springframework.security.authentication.LockedException;

import com.preston.argiope.service.security.LoginAttemptService;

/**
 * Thrown if an IP address is blocked. This is determined by calling 
 * {@link LoginAttemptService#isBlocked()}.
 * 
 * @author pbriggs
 */
@SuppressWarnings("serial")
public class IpBlockedException extends LockedException{
	
	public IpBlockedException(String msg) {
		super(msg);
	}

	public IpBlockedException(String msg, Throwable t) {
		super(msg, t);
	}

}
