package com.getsecual.auth.client.exception;

public class AuthenticationException extends Exception {

	private static final long serialVersionUID = -3340714999865539635L;
	
	private int statusCode;

	public AuthenticationException(int statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public AuthenticationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}
	
	public AuthenticationException() {
		super();
	}

	public int getStatusCode() {
		return statusCode;
	}
	
}
