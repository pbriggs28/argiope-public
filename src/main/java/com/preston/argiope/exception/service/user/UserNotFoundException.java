package com.preston.argiope.exception.service.user;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 3544776538734927812L;

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}
}
