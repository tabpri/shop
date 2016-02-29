package net.malta.model.payment.validator;

import org.springframework.stereotype.Component;

import net.malta.error.Errors;
import net.malta.error.ValidationError;
import net.malta.model.payment.PaymentConstants;
import net.malta.model.payment.PaymentInfo;
import net.malta.model.validator.IValidator;
import net.malta.model.validator.ValidationException;

@Component
public class PaymentInfoValidator implements IValidator<PaymentInfo>{

	@Override
	public void validate(PaymentInfo paymentInfo, Errors errors) throws ValidationException {
		
		Integer paymentMethod = paymentInfo.getPaymentMethod();
		if(paymentMethod==null || paymentMethod.intValue() ==0){			
			errors.add(new ValidationError(PaymentConstants.PURCHASE_PAYMENTMETHODISBLANK, new Object[0]));
		}
		
		// other validations
		if ( errors.hasErrors() ) {
			throw new ValidationException(errors);
		}
	}

}
