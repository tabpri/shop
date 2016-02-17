package net.malta.service.purchase;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.model.DeliveryAddress;

public interface IDeliveryAddressChoiseService {

	DeliveryAddress updateChoisesWithDeliveryAddress(Integer purchaseId, Integer userId, Integer deliveryAddressId);

}