/**
 * @author SB
 */
package net.malta.model.json.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.malta.mapper.IMapper;
import net.malta.model.DeliveryAddress;

@Component
public class DeliveryAddressesMapper
		implements IMapper<List<DeliveryAddress>, List<net.malta.model.user.json.DeliveryAddress>> {

	@Autowired
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
