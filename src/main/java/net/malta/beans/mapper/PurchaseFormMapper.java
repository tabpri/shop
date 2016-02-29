package net.malta.beans.mapper;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import net.malta.beans.PurchaseForm;
import net.malta.mapper.IMapper;
import net.malta.model.payment.PaymentInfo;

@Component
public class PurchaseFormMapper implements IMapper<PurchaseForm, PaymentInfo>{

	@Override
	public PaymentInfo map(PurchaseForm purchaseForm, PaymentInfo paymentCardInfo) {
		try {
			BeanUtils.copyProperties(paymentCardInfo, purchaseForm);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
		return paymentCardInfo;
	}

}
