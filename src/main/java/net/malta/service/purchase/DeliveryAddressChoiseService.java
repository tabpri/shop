package net.malta.service.purchase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.purchase.DeliveryAddressChoiseDAO;
import net.malta.model.Choise;
import net.malta.model.DeliveryAddress;
import net.malta.model.DeliveryAddressChoise;
import net.malta.model.DeliveryAddressChoiseImpl;
import net.malta.model.Purchase;
import net.malta.service.user.IDeliveryAddressService;

@Service
public class DeliveryAddressChoiseService implements IDeliveryAddressChoiseService {

	@Autowired
	private IPurchaseService purchaseService;
	
	@Autowired
	private IDeliveryAddressService addressService;
	
	@Autowired
	private DeliveryAddressChoiseDAO addressChoiseDAO; 
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public DeliveryAddress updateChoisesWithDeliveryAddress(Integer purchaseId,Integer userId,Integer deliveryAddressId) {
		
		Purchase purchase = purchaseService.getPurchase(purchaseId);
		
		DeliveryAddress deliveryAddress = addressService.getDeliveryAddress(userId, deliveryAddressId);
		
		@SuppressWarnings("unchecked")
		Collection<Choise> choises = purchase.getChoises();
		
		List<DeliveryAddressChoise> deliveryAddressChoises = new ArrayList<DeliveryAddressChoise>();
		
		for (Choise choise : choises) {
			Collection<DeliveryAddressChoise> existingDeliveryAddressChoises = (Collection<DeliveryAddressChoise>) choise.getDeliveryAddressChoises();
			if ( existingDeliveryAddressChoises != null && existingDeliveryAddressChoises.size() > 0  ) { // delete existing deliveryaddresschoises
				addressChoiseDAO.deleteAll(existingDeliveryAddressChoises);
			}
			deliveryAddressChoises.add(mapDeliveryAddressChoise(deliveryAddress, choise));
		}
		addressChoiseDAO.saveOrUpdateAll(deliveryAddressChoises);
		return deliveryAddress;
	}

	private DeliveryAddressChoise mapDeliveryAddressChoise(DeliveryAddress deliveryAddress, Choise choise) {
		DeliveryAddressChoise addressChoise = new DeliveryAddressChoiseImpl();
		addressChoise.setDeliveryAddress(deliveryAddress);
		addressChoise.setGiftCard(deliveryAddress.getGiftCard());
		addressChoise.setPreferreddate(deliveryAddress.getPreferreddate());
		addressChoise.setPreferredtime(deliveryAddress.getPreferredtime());		
		addressChoise.setChoise(choise);
		addressChoise.setOrdernum(choise.getOrdernum());
		return addressChoise;
	}
	
	public void setPurchaseService(IPurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	public void setAddressService(IDeliveryAddressService addressService) {
		this.addressService = addressService;
	}

	public void setAddressChoiseDAO(DeliveryAddressChoiseDAO addressChoiseDAO) {
		this.addressChoiseDAO = addressChoiseDAO;
	}	
}