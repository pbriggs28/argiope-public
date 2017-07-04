package com.preston.argiope.exception.service;

public class ServiceLayerException extends Exception {
	private static final long serialVersionUID = 3544776538734927812L;

	public ServiceLayerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceLayerException(String message) {
		super(message);
	}

	public ServiceLayerException(Throwable cause) {
		super(cause);
	}
}
