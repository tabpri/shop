package net.malta.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.enclosing.util.StringFullfiller;
import net.malta.dao.user.DeliveryAddressDAO;
import net.malta.error.Errors;
import net.malta.model.DeliveryAddress;
import net.malta.model.GiftCard;
import net.malta.model.Prefecture;
import net.malta.model.PublicUser;
import net.malta.model.user.validator.DeliveryAddressValidator;
import net.malta.service.meta.IGiftCardService;
import net.malta.service.meta.IPrefectureService;

@Service
public class DeliveryAddressService implements IDeliveryAddressService {

	@Autowired
	private IPublicUserService publicUserService;
	
	@Autowired
	private IPrefectureService prefectureService;
	
	@Autowired
	private IGiftCardService giftCardService;

	@Autowired
	private DeliveryAddressDAO deliveryAddressDAO;
	
	@Autowired
	private DeliveryAddressValidator deliveryAddressValidator;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public DeliveryAddress addDeliveryAddress(Integer userId,DeliveryAddress deliveryAddress) {
		return deliveryAddressUpdate(userId, deliveryAddress);
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public DeliveryAddress updateDeliveryAddress(Integer userId, DeliveryAddress deliveryAddress) {
		return deliveryAddressUpdate(userId, deliveryAddress);
	}	

	private DeliveryAddress deliveryAddressUpdate(Integer userId, DeliveryAddress deliveryAddress) {
		
		//validate
		deliveryAddressValidator.validate(deliveryAddress, new Errors());

		StringFullfiller.fullfil(deliveryAddress);
		
		//save
		PublicUser user = publicUserService.getUser(userId);
		deliveryAddress.setPublicUser(user);
		Prefecture prefecture = prefectureService.getPrefecture(deliveryAddress.getPrefecture().getId());
		deliveryAddress.setPrefecture(prefecture);
		if ( deliveryAddress.getGiftCard() != null ) {
			GiftCard giftCard = giftCardService.getGiftCard(deliveryAddress.getGiftCard().getId());
			deliveryAddress.setGiftCard(giftCard);
			deliveryAddress.setHasgiftcard(true);
		}		
		deliveryAddressDAO.saveOrUpdate(deliveryAddress);
		initialize(deliveryAddress);
		return deliveryAddress;
	}

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public List<DeliveryAddress> getDeliveryAddresses(Integer userId) {
		List<DeliveryAddress> deliveryAddresses = deliveryAddressDAO.getDeliveryAddresses(userId);
		for (DeliveryAddress deliveryAddress : deliveryAddresses) {
			initialize(deliveryAddress);
		}
		return deliveryAddresses;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)		
	public DeliveryAddress getDeliveryAddress(Integer userId, Integer id) {
		DeliveryAddress deliveryAddress = deliveryAddressDAO.getDeliveryAddress(userId, id);
		initialize(deliveryAddress);
		return deliveryAddress;
	}	

	private void initialize(DeliveryAddress deliveryAddress) {
		deliveryAddress.getPrefecture().getName();
		if ( deliveryAddress.getGiftCard() != null ) {
			deliveryAddress.getGiftCard().getName();			
		}
		deliveryAddress.getPublicUser().getName();
	}
	
	public void setPublicUserService(IPublicUserService publicUserService) {
		this.publicUserService = publicUserService;
	}

	public void setPrefectureService(IPrefectureService prefectureService) {
		this.prefectureService = prefectureService;
	}

	public void setGiftCardService(IGiftCardService giftCardService) {
		this.giftCardService = giftCardService;
	}

	public void setDeliveryAddressDAO(DeliveryAddressDAO deliveryAddressDAO) {
		this.deliveryAddressDAO = deliveryAddressDAO;
	}

	public void setDeliveryAddressValidator(DeliveryAddressValidator deliveryAddressValidator) {
		this.deliveryAddressValidator = deliveryAddressValidator;
	}

}