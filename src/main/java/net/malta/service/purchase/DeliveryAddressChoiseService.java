package net.malta.service.purchase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.meta.PrefectureCarriageDAO;
import net.malta.dao.purchase.ChoiseDAO;
import net.malta.dao.purchase.DeliveryAddressChoiseDAO;
import net.malta.dao.purchase.PurchaseDAO;
import net.malta.error.ValidationError;
import net.malta.model.Choise;
import net.malta.model.ChoiseImpl;
import net.malta.model.DeliveryAddress;
import net.malta.model.DeliveryAddressChoise;
import net.malta.model.DeliveryAddressChoiseImpl;
import net.malta.model.Purchase;
import net.malta.model.PurchaseImpl;
import net.malta.model.purchase.wrapper.ChoiseTotal;
import net.malta.model.purchase.wrapper.PurchaseTotal;
import net.malta.model.validator.ValidationException;
import net.malta.service.user.IDeliveryAddressService;

@Service
public class DeliveryAddressChoiseService implements IDeliveryAddressChoiseService {

	private static final String DELIVERYADDRESSCHOISES_DELIVERYADDRESSNOTVALID = "DELIVERYADDRESSCHOISES.DELIVERYADDRESSNOTVALID";

	@Autowired
	private IPurchaseService purchaseService;

	@Autowired
	private IDeliveryAddressService addressService;
	
	@Autowired
	private DeliveryAddressChoiseDAO addressChoiseDAO; 
	
	@Autowired
	private PrefectureCarriageDAO prefectureCarriageDAO;

	@Autowired
	private PurchaseDAO purchaseDAO;
	
	@Autowired
	private ChoiseDAO choiseDAO;
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public DeliveryAddress updateChoisesWithDeliveryAddress(Integer purchaseId,Integer userId,Integer deliveryAddressId) {
		
		Purchase purchase = purchaseService.getPurchase(purchaseId);
		
		DeliveryAddress deliveryAddress = addressService.getDeliveryAddress(userId, deliveryAddressId);
		
		if ( deliveryAddress == null ) {
			throw new ValidationException(new ValidationError(DELIVERYADDRESSCHOISES_DELIVERYADDRESSNOTVALID, deliveryAddressId,userId));
		}
		
		@SuppressWarnings("unchecked")
		Collection<Choise> choises = purchase.getChoises();
		Collection<ChoiseImpl> choisesUpdated = new ArrayList<ChoiseImpl>();
		
		List<DeliveryAddressChoise> deliveryAddressChoises = new ArrayList<DeliveryAddressChoise>();

		Integer prefectureId = deliveryAddress.getPrefecture().getId();
		Integer carriage = prefectureCarriageDAO.getCarriageValue(prefectureId);
		
		for (Choise choise : choises) {
			Collection<DeliveryAddressChoise> existingDeliveryAddressChoises = (Collection<DeliveryAddressChoise>) choise.getDeliveryAddressChoises();
			if ( existingDeliveryAddressChoises != null && existingDeliveryAddressChoises.size() > 0  ) { // delete existing deliveryaddresschoises
				addressChoiseDAO.deleteAll(existingDeliveryAddressChoises);
			}
			deliveryAddressChoises.add(mapDeliveryAddressChoise(deliveryAddress, choise));
			// set carriage and recalculate totals based on the prefecture carriage			
			ChoiseTotal choiseTotal = new ChoiseTotal(choise);
			choiseTotal.carriageChanged(carriage); 
			choisesUpdated.add((ChoiseImpl) choise);
		}
		//save the address choises
		addressChoiseDAO.saveOrUpdateAll(deliveryAddressChoises);
		//save choises
		choiseDAO.saveOrUpdateAll(choisesUpdated);
		//recalculate total and save
		PurchaseTotal purchaseTotal = new PurchaseTotal(purchase);
		purchaseTotal.calcAndSetTotal();
		purchaseDAO.saveOrUpdate((PurchaseImpl) purchase);
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