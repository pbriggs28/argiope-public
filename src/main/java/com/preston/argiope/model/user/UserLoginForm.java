package com.preston.argiope.model.user;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_REQUEST, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserLoginForm {

	enum AuthError {
		INCORRECT_USERNAME, INCORRECT_PASSWORD
	}

	enum LoginError {
		USERNAME_LENGTH, PASSWORD_LENGTH
	}

	/** If changing the {@link UserLoginForm#username} variable name, must
	 * also change this constant. Spring Security uses this. */
	public static final String USERNAME_VARIABLE = "username";
	private String username;
	
	/** See {@link UserLoginForm#username} javadocs for info. */
	public static final String PASSWORD_VARIABLE = "password";
	private String password;
	
	private LoginError loginError;
	private String loginErrorMessage;
	private AuthError authError;
	private String authErrorMessage;
	
	public AuthError getAuthError() {
		return authError;
	}

	public String getAuthErrorMessage() {
		return authErrorMessage;
	}

	public LoginError getLoginError() {
		return loginError;
	}

	public String getLoginErrorMessage() {
		return loginErrorMessage;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setAuthError(AuthError authError) {
		this.authError = authError;
	}

	public void setAuthErrorMessage(String authErrorMessage) {
		this.authErrorMessage = authErrorMessage;
	}

	public void setLoginError(LoginError loginError) {
		this.loginError = loginError;
	}

	public void setLoginErrorMessage(String errorMessage) {
		this.loginErrorMessage = errorMessage;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}