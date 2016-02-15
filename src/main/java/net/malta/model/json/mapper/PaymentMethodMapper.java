/**
 * @author SB
 */
package net.malta.model.json.mapper;

import net.malta.mapper.IMapper;
import net.malta.model.PaymentMethod;

public class PaymentMethodMapper implements IMapper<PaymentMethod, net.malta.model.payment.json.PaymentMethod> {

	@Override
	public net.malta.model.payment.json.PaymentMethod map(PaymentMethod paymentMethod,
			net.malta.model.payment.json.PaymentMethod paymentMethodJson) {
		paymentMethodJson.setId(paymentMethod.getId());
		paymentMethodJson.setName(paymentMethod.getName());
		paymentMethodJson.setNote(paymentMethod.getNote());
		return paymentMethodJson;
	}

}
