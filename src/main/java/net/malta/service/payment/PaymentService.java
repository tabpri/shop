package net.malta.service.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.payment.PaymentMethodDAO;
import net.malta.model.PaymentMethod;

@Service
public class PaymentService implements IPaymentService {

	@Autowired
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
