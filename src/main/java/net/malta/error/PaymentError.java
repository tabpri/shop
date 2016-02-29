package net.malta.error;

public class PaymentError extends Error{

	public PaymentError(String errorCode, Object... values) {
		this.errorCode = errorCode;
		this.values = values;
	}

	public PaymentError(String errorCode, String errorMessage,Object... values) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.values = values;
	}
	
}
