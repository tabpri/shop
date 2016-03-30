package com.getsecual.email.client.exception;

public class EmailException extends Exception {

	private static final long serialVersionUID = -7626051037339870007L;
	
	private Integer statusCode;

	public EmailException() {
		super();
	}

	public EmailException(Integer emailStatusCode) {
		super();
		this.statusCode = emailStatusCode;
	}

	public EmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailException(String message) {
		super(message);
	}

	public EmailException(Throwable cause) {
		super(cause);
	}
	
	public Integer getStatusCode() {
		return this.statusCode;
	}
	
}