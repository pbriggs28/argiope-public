package com.preston.argiope.handler.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.preston.argiope.app.constant.WebConstants;
import com.preston.argiope.app.constant.dev.DevWebConstants;
import com.preston.argiope.event.security.AuthenticationFailureIpBlockedEvent;
import com.preston.argiope.exception.service.security.IpBlockedException;
import com.preston.argiope.service.security.LoginAttemptService;
/**
 * Handles failed authentication attempts. Currently only checks if
 * the {@link AuthenticationException} is an instance of {@link IpBlockedException}
 * and adds a session attribute to be displayed on the login page. In future could
 * add other checks and session attributes.
 * @author pbriggs
 *
 */
@Component
// TODO: Create a subclass of this class to handle any IP blocking features and make it conditional
// on the dev profile.
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	private static final String LOGIN_FAILED_URL = WebConstants.RequestMappings.Annonymous.Pages.LOGIN_FAILURE;
	private static final String IP_BLOCKED_ATTR = DevWebConstants.ResponseAttributes.IpBlocked.ATTRIBUTE;
	private static final String FAILED_LOGIN_COUNT_ATTR = DevWebConstants.ResponseAttributes.FailedLoginCount.ATTRIBUTE;
	
	@Autowired private LoginAttemptService loginAttemptService;
	
	public CustomAuthenticationFailureHandler() {
		setDefaultFailureUrl(LOGIN_FAILED_URL);
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		setSessionAttr(request, response, exception);
		super.onAuthenticationFailure(request, response, exception);
	}

	/* Must set attributes on session. Login failure is a redirect, not a
	 *  forward and will thus lose all request attributes */
	private void setSessionAttr(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		setFailedAttemptsAttr(request, response, exception);
		setIpBlockedAttr(request, response, exception);
	}
	
	/**
	 * Sets the number of failed login attempts. This gets cleared by the
	 * {@link CustomAuthenticationSuccessHandler}.
	 * 
	 * @param session
	 * @param response
	 * @param exception
	 * @throws IOException
	 * @throws ServletException
	 */
	private void setFailedAttemptsAttr(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		int numFailedAttempts = loginAttemptService.numFailedAttempts(request);
		request.getSession().setAttribute(FAILED_LOGIN_COUNT_ATTR, numFailedAttempts);
	}
	
	/**
	 * Sets whether this IP address is blocked or not. This gets cleared by the
	 * {@link CustomAuthenticationSuccessHandler}.
	 * 
	 * @see IpBlockedException
	 * @see AuthenticationFailureIpBlockedEvent
	 * @see AuthenticationFailureEventListenerIpBlocked
	 * 
	 * @param session
	 * @param response
	 * @param exception
	 * @throws IOException
	 * @throws ServletException
	 */
	private void setIpBlockedAttr(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		boolean ipBlocked = false;
		if(exception instanceof IpBlockedException) {
			ipBlocked = true;
		}
		request.getSession().setAttribute(IP_BLOCKED_ATTR, ipBlocked);
	}
}
