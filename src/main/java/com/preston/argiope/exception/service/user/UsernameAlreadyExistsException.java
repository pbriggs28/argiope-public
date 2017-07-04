package com.preston.argiope.exception.service.user;

public class UsernameAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 3544776538734927812L;

	public UsernameAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameAlreadyExistsException(String message) {
		super(message);
	}

	public UsernameAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}
