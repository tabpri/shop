package net.malta.model.json.mapper;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import net.malta.model.DeliveryAddress;
import net.malta.model.GiftCard;
import net.malta.model.Prefecture;

public class DeliveryAddressMapper implements IMapper<DeliveryAddress, net.malta.model.json.DeliveryAddress> {

	@Override
	public net.malta.model.json.DeliveryAddress map(DeliveryAddress deliveryAddress,
			net.malta.model.json.DeliveryAddress deliveryAddressJson) {
		try {
			BeanUtils.copyProperties(deliveryAddressJson, deliveryAddress);
			deliveryAddressJson.setZip(deliveryAddress.getZipthree());
			if ( deliveryAddress.isHasgiftcard() ) {
				GiftCard giftCard = deliveryAddress.getGiftCard();
				deliveryAddressJson.setGiftcardid(giftCard.getId());
				deliveryAddressJson.setGiftcardname(giftCard.getName());				
			}
			Prefecture prefecture = deliveryAddress.getPrefecture();
			if ( prefecture != null ) {
				deliveryAddressJson.setPrefectureid(prefecture.getId());
				deliveryAddressJson.setPrefecturename(prefecture.getName());				
			}
			deliveryAddressJson.setPublicuserid(deliveryAddress.getPublicUser().getId());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return deliveryAddressJson;
	}

}
