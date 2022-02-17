package com.daniel.record.exception;

public class HttpUnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = 5099577033055625642L;

	public HttpUnauthorizedException(String message) {
		super(message);
	}
}
