package net.malta.beans;

public class ValidationError implements IError {

	private String errorCode;
	private Object[] values;

	
	public ValidationError(String errorCode, Object... values) {
		this.errorCode = errorCode;
		this.values = values;
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

}
