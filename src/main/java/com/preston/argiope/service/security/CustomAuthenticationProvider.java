package com.preston.argiope.service.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.preston.argiope.exception.service.security.IpBlockedException;

/**
 * <p>
 * Intercepts the authentication process to add pre-RetrieveUser checks such as
 * is the IP Blocked etc. After the pre-RetrieveUser checks, the super class'
 * implementation is called.
 * </p>
 * <p>
 * See {@link #authenticate(Authentication)} for more info.
 * </p>
 * 
 * @see {@link #authenticate(Authentication)}
 */
@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private HttpServletRequest req;
	@Autowired private LoginAttemptService loginAttemptService;
	
	public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		/* We are responsible for setting the UserDetailsService and
		 * PasswordEncoder when extending DaoAuthenticationProvider */
		
		setUserDetailsService(userDetailsService);
		setPasswordEncoder(passwordEncoder);
	}

	/**
	 * <p>
	 * Intercepts the authentication process to add pre-RetrieveUser checks such
	 * as is the IP Blocked etc. After the pre-RetrieveUser checks, the super
	 * class' implementation is called.
	 * </p>
	 * 
	 * <p>
	 * <i>Normally</i> any pre-RetrieveUser exceptions would stop the
	 * authentication process without even retrieving the user from the data
	 * store. If an {@link AuthenticationException} is thrown during
	 * {@link #preRetrieveUserChecks()} we could stop there and throw it
	 * immediately without requesting the user from the datastore however, in
	 * order to mitigate the security vulnerability found in SEC-2056 (See
	 * below), we will still make a call to
	 * {@link DaoAuthenticationProvider#authenticate(Authentication)} and simply
	 * ignore the exception in preference of our pre-RetrieveUser check
	 * exception.
	 * </p>
	 * 
	 * <h4>Information on <a href=
	 * "https://github.com/spring-projects/spring-security/issues/2280">SEC-2056:</a>
	 * </h4>
	 * 
	 * <blockquote> Spring Security's DaoAuthenticationProvider authenticates
	 * users by utilizing the PasswordEncoder interface to compare the submitted
	 * password with the actual password. If a user is not found, the comparison
	 * is skipped which, depending on the PasswordEncoder implementation, can
	 * result in a significant difference in the amount of time required to
	 * attempt to authenticate an actual user versus a user that does not exist.
	 * This opens up the possibility of a side channel attack that would enable
	 * a malicious user to determine if a username is valid. </blockquote>
	 * 
	 * 
	 * 
	 * @param authentication
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		AuthenticationException preRetrieveException = null;
		AuthenticationException retrieveException = null;
		Authentication auth = null;
		
		try {
			preRetrieveUserChecks();
		} catch (AuthenticationException e) {
			preRetrieveException = e;
		}
		
		try {
			/* See method javadocs for why we still attempt authentication if IP is blocked. */
			auth = super.authenticate(authentication); 
		} catch (AuthenticationException e) {
			retrieveException = e;
		}
		
		/* Prefer throwing our own preRetrieveException over a retrieveException */
		if(preRetrieveException != null) {
			throw preRetrieveException;
		} else if(retrieveException != null) {
			throw retrieveException;
		}
		
		return auth;
	}

	// Private Methods
	// ====================================================================================================
	/**
	 * Perform checks <b>prior</b> to retrieving user from datastore.
	 */
	private void preRetrieveUserChecks() {
		checkIpBlocked();
	}
	
	private void checkIpBlocked() {
		if (loginAttemptService.ipBlocked(req)) {
			String message = String.format("The IP address [%s] has exceeded the maximum failed login attempts. Failed: [%s]. Max: [%s].", 
					loginAttemptService.getIp(req), loginAttemptService.numFailedAttempts(req), loginAttemptService.getMaxAttempts());
			logger.debug(message);
			throw new IpBlockedException(message);
		}
	}
}
