package net.malta.model.json.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.malta.mapper.IMapper;
import net.malta.model.Choise;
import net.malta.model.DeliveryAddress;
import net.malta.model.PaymentStatus;
import net.malta.model.Purchase;
import net.malta.model.purchase.json.Payment;
import net.malta.model.purchase.wrapper.PurchaseDeliveryAddress;

@Component
public class PurchaseMapper implements IMapper<Purchase, net.malta.model.purchase.json.Purchase>{

	@Autowired
	private ChoisesMapper choisesMapper;
	
	@Autowired
	private DeliveryAddressMapper deliveryAddressMapper;
	
	@Override
	public net.malta.model.purchase.json.Purchase map(Purchase purchase, net.malta.model.purchase.json.Purchase purchaseJSON) {
		
		// purchase info
		purchaseJSON.setPurchaseId(purchase.getId());
		purchaseJSON.setPurchasetotal(purchase.getTotal());
		purchaseJSON.setTotalordernum(purchase.getTotalordernum());
		purchaseJSON.setCarriage(purchase.getCarriage());
		purchaseJSON.setPurchaseDate(purchase.getDate());
		
		// delivery address
		DeliveryAddress deliveryAddress = new PurchaseDeliveryAddress(purchase).getDeliveryAddress();
		if ( deliveryAddress != null ) {
			net.malta.model.user.json.DeliveryAddress deliveryAddressJSON = deliveryAddressMapper.map(deliveryAddress, new net.malta.model.user.json.DeliveryAddress());
			purchaseJSON.setDeliveryaddress(deliveryAddressJSON);
		}
		PaymentStatus payment = purchase.getPayment();
		if ( payment != null ) {
			Payment paymentJSON = new Payment();
			paymentJSON.setStatus(payment.getPaymentStatusString());
			paymentJSON.setOrderId(payment.getOrderId());
			paymentJSON.setTransactionReference(payment.getTransactionReference());
			paymentJSON.setTransactionDate(payment.getTransactionDate());
			purchaseJSON.setPayment(paymentJSON);
		}
				
		// purchase.choises
		Collection<Choise> choises = ((Collection<Choise>) purchase.getChoises());
		List<net.malta.model.purchase.json.Choise> choisesJSON = new ArrayList<net.malta.model.purchase.json.Choise>();
		purchaseJSON.setChoises(choisesJSON);
		choisesMapper.map(choises, choisesJSON);
		return purchaseJSON;
	}

	public void setChoisesMapper(ChoisesMapper choisesMapper) {
		this.choisesMapper = choisesMapper;
	}
}
