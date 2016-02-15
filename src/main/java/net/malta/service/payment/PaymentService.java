package net.malta.service.payment;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.payment.PaymentMethodDAO;
import net.malta.model.PaymentMethod;

public class PaymentService implements IPaymentService {

	private PaymentMethodDAO paymentMethodDAO;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethodDAO.getPaymentMethods();
	}

	public void setPaymentMethodDAO(PaymentMethodDAO paymentMethodDAO) {
		this.paymentMethodDAO = paymentMethodDAO;
	}
	
}
