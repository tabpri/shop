package net.malta.service.user;

import java.util.List;

import net.malta.model.DeliveryAddress;

public interface IDeliveryAddressService {

	public DeliveryAddress addDeliveryAddress(Integer userId, DeliveryAddress deliveryAddress);

	public DeliveryAddress updateDeliveryAddress(Integer userId, DeliveryAddress deliveryAddress);

	public List<DeliveryAddress> getDeliveryAddresses(Integer userId);

	public DeliveryAddress getDeliveryAddress(Integer userId,Integer id);

}