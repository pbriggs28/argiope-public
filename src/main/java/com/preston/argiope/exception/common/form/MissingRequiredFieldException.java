package com.preston.argiope.exception.common.form;

public class MissingRequiredFieldException extends Exception {
	private static final long serialVersionUID = -5324813967896851778L;

	public MissingRequiredFieldException(String message, Throwable cause) {
		super(message, cause);
	}

	public MissingRequiredFieldException(String message) {
		super(message);
	}

	public MissingRequiredFieldException(Throwable cause) {
		super(cause);
	}
}
