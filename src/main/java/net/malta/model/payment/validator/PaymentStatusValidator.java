package net.malta.model.payment.validator;

import org.springframework.stereotype.Component;

import net.malta.error.Errors;
import net.malta.error.PaymentError;
import net.malta.model.PaymentStatus;
import net.malta.model.payment.PaymentConstants;
import net.malta.model.payment.PaymentStatusEnum;
import net.malta.model.validator.IValidator;
import net.malta.model.validator.ValidationException;

@Component
public class PaymentStatusValidator implements IValidator<PaymentStatus> {

	@Override
	public void validate(PaymentStatus paymentStatus, Errors errors) throws ValidationException {

		if ( paymentStatus == null ) {
			return;
		}
		
		Integer purchaseId = paymentStatus.getPurchaseId();
		PaymentStatusEnum status = paymentStatus.getPaymentStatus();
		if ( status.equals(PaymentStatusEnum.COMPLETED) ){
			errors.add(new PaymentError(PaymentConstants.PURCHASE_PAYMENT_COMPLETED, purchaseId));
		}
		// no need of ACS_CONFIRM check
/*		else if ( status.equals(PaymentStatusEnum.ACS_CONFIRM) ){
			errors.add(new PaymentError(PaymentConstants.PURCHASE_PAYMENT_ACSCONFIRM, purchaseId));
		}
		
*/		if ( errors.hasErrors() ) {
			throw new ValidationException(errors);
		}
	}

}
