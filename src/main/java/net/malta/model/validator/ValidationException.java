package net.malta.model.validator;

import net.malta.error.Errors;
import net.malta.error.IError;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -4181163837889301743L;
	
	private Errors errors;

	public ValidationException() {
	}

	public ValidationException(Errors errors) {
		this.errors = errors;
	}

	public ValidationException(IError error) {
		this.errors = new Errors();
		this.errors.add(error);
	}

	public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}
	
	public Errors getErrors() {
		return errors;
	}
}
