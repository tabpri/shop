package net.malta.service.payment;

import java.util.List;

import net.malta.model.PaymentMethod;
import net.malta.model.PaymentStatus;
import net.malta.model.payment.PaymentInfo;
import net.malta.model.payment.RequestInfo;

public interface IPaymentService {

	List<PaymentMethod> getPaymentMethods();

	String paymentRequest(Integer purchaseId, PaymentInfo paymentCardDetails, RequestInfo requestDetails)
			throws PaymentException;

	void executePayment(Integer purchaseId,ACSResponse parameterObject) throws PaymentException;
	
	PaymentStatus getPaymentStatus(Integer purchaseId);

}