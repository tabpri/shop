package net.malta.model.payment.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.malta.dao.payment.PaymentMethodDAO;
import net.malta.error.Errors;
import net.malta.error.ValidationError;
import net.malta.model.PaymentMethod;
import net.malta.model.PaymentMethodImpl;
import net.malta.model.payment.PaymentConstants;
import net.malta.model.payment.PaymentInfo;
import net.malta.model.validator.IValidator;
import net.malta.model.validator.ValidationException;

@Component
public class PaymentInfoValidator implements IValidator<PaymentInfo>{

	@Autowired
	private PaymentMethodDAO paymentMethodDAO;
	
	@Override
	public void validate(PaymentInfo paymentInfo, Errors errors) throws ValidationException {
		
		Integer paymentMethod = paymentInfo.getPaymentMethod();
		if(paymentMethod==null || paymentMethod.intValue() ==0){			
			errors.add(new ValidationError(PaymentConstants.PURCHASE_PAYMENTMETHODISBLANK, new Object[0]));
		} else {
			PaymentMethod paymentMethodReturned = paymentMethodDAO.find(paymentMethod);
			if ( paymentMethodReturned == null ) {				
				errors.add(new ValidationError(PaymentConstants.PURCHASE_PAYMENTMETHODNOTVALID, paymentMethod));
			}
		}
		
		// other validations
		if ( errors.hasErrors() ) {
			throw new ValidationException(errors);
		}
	}

}
