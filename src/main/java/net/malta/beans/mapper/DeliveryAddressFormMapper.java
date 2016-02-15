package net.malta.beans.mapper;

import net.enclosing.util.StringFullfiller;
import net.malta.beans.DeliveryAddressForm;
import net.malta.mapper.IMapper;
import net.malta.model.DeliveryAddress;
import net.malta.model.GiftCard;
import net.malta.model.GiftCardImpl;
import net.malta.model.Prefecture;
import net.malta.model.PrefectureImpl;

public class DeliveryAddressFormMapper implements IMapper<DeliveryAddressForm, DeliveryAddress>{

	@Override
	public DeliveryAddress map(DeliveryAddressForm form, DeliveryAddress deliveryAddress) {

		deliveryAddress.setId(form.getId());		
		deliveryAddress.setName(form.getName());
		deliveryAddress.setKana(form.getKana());
		deliveryAddress.setZipthree(form.getZip());
		deliveryAddress.setZipfour(form.getZipfour());
		deliveryAddress.setPref(form.getPref());
		deliveryAddress.setBuildingname(form.getBuildingname());
		deliveryAddress.setPhone(form.getPhone());
		deliveryAddress.setPreferreddate(form.getPreferreddate());
		deliveryAddress.setPreferredtime(form.getPreferredtime());
		deliveryAddress.setHasgiftcard(form.isHasgiftcard());
		deliveryAddress.setAddress(form.getAddress());

		if ( form.getGiftCard() != null ) {
			GiftCard giftCard = new GiftCardImpl();
			giftCard.setId(form.getGiftCard());
			deliveryAddress.setGiftCard(giftCard);			
		}
	
		if ( form.getPrefecture() != null ) {
			Prefecture prefecture = new PrefectureImpl();
			prefecture.setId(form.getPrefecture());
			deliveryAddress.setPrefecture(prefecture);			
		}
		StringFullfiller.fullfil(deliveryAddress);
		return deliveryAddress;
	}
	

}
