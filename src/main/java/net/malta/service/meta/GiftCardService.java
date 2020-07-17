package net.malta.service.meta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.meta.GiftCardDAO;
import net.malta.model.GiftCard;

@Service
public class GiftCardService implements IGiftCardService {

	@Autowired
	private GiftCardDAO giftCardDAO; 

	@Transactional(propagation=Propagation.REQUIRED)	
	public GiftCard getGiftCard(Integer id) {
		return giftCardDAO.find(id);
	}
	
	public void setGiftCardDAO(GiftCardDAO giftCardDAO) {
		this.giftCardDAO = giftCardDAO;
	}	
}
