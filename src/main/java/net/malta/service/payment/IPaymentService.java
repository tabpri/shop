package net.malta.service.payment;

import java.util.List;

import net.malta.model.PaymentMethod;

public interface IPaymentService {

	List<PaymentMethod> getPaymentMethods();

}