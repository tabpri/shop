package net.malta.error;

import net.malta.error.IError;

public abstract class Error implements IError{

	protected String errorCode;
	protected String errorMessage;
	protected Object[] values;

	public Error() {
		super();
	}

	@Override
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		
	}

	@Override
	public String getErrorCode() {
		return this.errorCode;
	}

	@Override
	public void setValues(Object... values) {
		this.values = values;
	}

	@Override
	public Object[] getValues() {
		return this.values;
	}

	@Override
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String getErrorMessage() {
		return this.errorMessage;
	}

}