package com.preston.argiope.handler.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.preston.argiope.app.constant.dev.DevWebConstants;

@Service
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	// TODO: Create a subclass of this to handle any IP blocking features and make it conditional
	// on the dev profile.
	private final String ipBlockedAttr = DevWebConstants.ResponseAttributes.IpBlocked.ATTRIBUTE;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute(ipBlockedAttr, false);
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
