package net.malta.service.meta;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.meta.GiftCardDAO;
import net.malta.model.GiftCard;

public class GiftCardService implements IGiftCardService {

	private GiftCardDAO giftCardDAO; 

	@Transactional(propagation=Propagation.REQUIRED)	
	public GiftCard getGiftCard(Integer id) {
		return giftCardDAO.find(id);
	}
	
	public void setGiftCardDAO(GiftCardDAO giftCardDAO) {
		this.giftCardDAO = giftCardDAO;
	}	
}
