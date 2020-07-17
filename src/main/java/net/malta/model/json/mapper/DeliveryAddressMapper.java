package net.malta.model.json.mapper;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import net.malta.mapper.IMapper;
import net.malta.model.DeliveryAddress;
import net.malta.model.GiftCard;
import net.malta.model.Prefecture;

@Component
public class DeliveryAddressMapper implements IMapper<DeliveryAddress, net.malta.model.user.json.DeliveryAddress> {

	@Override
	public net.malta.model.user.json.DeliveryAddress map(DeliveryAddress deliveryAddress,
			net.malta.model.user.json.DeliveryAddress deliveryAddressJson) {
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
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return deliveryAddressJson;
	}

}
