package com.preston.argiope.exception.service.user;

public class DeleteUserException extends Exception {
	private static final long serialVersionUID = 3544776538734927812L;

	public DeleteUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeleteUserException(String message) {
		super(message);
	}

	public DeleteUserException(Throwable cause) {
		super(cause);
	}
}
