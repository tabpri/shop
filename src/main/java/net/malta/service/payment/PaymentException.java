package net.malta.service.payment;

import net.malta.error.Errors;
import net.malta.error.PaymentError;
import net.malta.model.payment.PaymentConstants;

public class PaymentException extends Exception {

	private static final long serialVersionUID = -6011975455096796068L;

	private Errors errors;
	
	public PaymentException(Errors errors) {
		super();
		this.errors = errors;
	}

	public PaymentException() {
		super();		
		createDefaultError();
	}

	private void createDefaultError() {
		this.errors = new Errors();
		this.errors.add(new PaymentError(PaymentConstants.PURCHASE_PAYMENT_PAYMENTEXECUTION_FAILED, new Object[0]));
	}

	public PaymentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PaymentException(String message, Throwable cause) {
		super(message, cause);
	}

	public PaymentException(String message) {
		super(message);
	}

	public PaymentException(Throwable cause) {
		super(cause);
		createDefaultError();		
	}

	public Errors getErrors() {
		return errors;
	}	
}
