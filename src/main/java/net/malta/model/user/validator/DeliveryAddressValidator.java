package net.malta.model.user.validator;

import org.apache.commons.lang.StringUtils;

import net.malta.beans.ValidationError;
import net.malta.error.Errors;
import net.malta.model.DeliveryAddress;
import net.malta.model.validator.IValidator;
import net.malta.model.validator.ValidationException;
import net.malta.model.validator.constants.DeliveryAddressConstants;

public class DeliveryAddressValidator implements IValidator<DeliveryAddress>{

	@Override
	public void validate(DeliveryAddress deliveryAddress, Errors errors) {
		
		Object[] blank = new Object[0];
		
		if( StringUtils.isBlank(deliveryAddress.getName())){			
			errors.add(new ValidationError(DeliveryAddressConstants.NAMEISBLANK, blank));
		}
		
		if(StringUtils.isBlank(deliveryAddress.getKana())){			
			errors.add(new ValidationError(DeliveryAddressConstants.KANAISBLANK, blank));			
		}
		
		if(deliveryAddress.getZipthree() == 0 ){
			errors.add(new ValidationError(DeliveryAddressConstants.ZIPISBLANK, blank));			
		}

		if(StringUtils.isBlank(deliveryAddress.getAddress())){
			errors.add(new ValidationError(DeliveryAddressConstants.ADDRESSISBLANK, blank));			
		}
		
		if(StringUtils.isBlank(deliveryAddress.getPhone())){
			errors.add(new ValidationError(DeliveryAddressConstants.PHONEISBLANK, blank));			
		}
		
		if(!StringUtils.isNumeric(deliveryAddress.getPhone())){
			errors.add(new ValidationError(DeliveryAddressConstants.PHONEISNOTNUMERIC, blank));			
		}
		if( deliveryAddress.getPrefecture().getId() == 0){
			errors.add(new ValidationError(DeliveryAddressConstants.PREFECTUREISBLANK, blank));			
		}

		if ( errors.hasErrors() ) {
			throw new ValidationException(errors);
		}
	}

}