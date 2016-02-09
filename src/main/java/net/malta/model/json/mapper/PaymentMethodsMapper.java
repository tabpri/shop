/**
 * @author SB
 */
package net.malta.model.json.mapper;

import java.util.List;

import net.malta.model.PaymentMethod;

public class PaymentMethodsMapper implements IMapper<List<PaymentMethod>, List<net.malta.model.json.PaymentMethod>>{

	private PaymentMethodMapper paymentMethodMapper;
	
	@Override
	public List<net.malta.model.json.PaymentMethod> map(List<PaymentMethod> paymentMethods,
			List<net.malta.model.json.PaymentMethod> paymentMethodsJson) {
		for (PaymentMethod paymentMethod : paymentMethods) {
			net.malta.model.json.PaymentMethod paymentMethodJson = paymentMethodMapper.map(paymentMethod, new net.malta.model.json.PaymentMethod());
			paymentMethodsJson.add(paymentMethodJson);
		}
		return paymentMethodsJson;		
	}

	public void setPaymentMethodMapper(PaymentMethodMapper paymentMethodMapper) {
		this.paymentMethodMapper = paymentMethodMapper;
	}	
}
