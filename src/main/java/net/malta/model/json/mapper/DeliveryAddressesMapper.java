/**
 * @author SB
 */
package net.malta.model.json.mapper;

import java.util.List;

import net.malta.mapper.IMapper;
import net.malta.model.DeliveryAddress;

public class DeliveryAddressesMapper
		implements IMapper<List<DeliveryAddress>, List<net.malta.model.user.json.DeliveryAddress>> {

	private DeliveryAddressMapper deliveryAddressMapper;

	@Override
	public List<net.malta.model.user.json.DeliveryAddress> map(List<DeliveryAddress> deliveryAddresses,
			List<net.malta.model.user.json.DeliveryAddress> deliveryAddressesJson) {
		for (DeliveryAddress deliveryAddress : deliveryAddresses) {
			net.malta.model.user.json.DeliveryAddress deliveryAddressJson = deliveryAddressMapper.map(deliveryAddress,
					new net.malta.model.user.json.DeliveryAddress());
			deliveryAddressesJson.add(deliveryAddressJson);
		}
		return deliveryAddressesJson;
	}

	public void setDeliveryAddressMapper(DeliveryAddressMapper deliveryAddressMapper) {
		this.deliveryAddressMapper = deliveryAddressMapper;
	}
}
