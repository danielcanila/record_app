package com.daniel.record.exception;

public class HttpBadRequestException extends RuntimeException {
	private static final long serialVersionUID = 6558486532224154843L;

	public HttpBadRequestException(String message) {
		super(message);
	}
}
