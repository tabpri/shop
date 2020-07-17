package net.malta.model.purchase.wrapper;

import java.io.Serializable;
import java.util.Collection;

import net.malta.model.Choise;
import net.malta.model.DeliveryAddress;
import net.malta.model.DeliveryAddressChoise;
import net.malta.model.Purchase;

public class PurchaseDeliveryAddress implements Serializable {

	private static final long serialVersionUID = 2388849040933323563L;

	private Purchase purchase;
	
	public PurchaseDeliveryAddress(Purchase purchase) {
		this.purchase = purchase;
	}
	
	@SuppressWarnings("rawtypes")
	public DeliveryAddress getDeliveryAddress() {
		Collection choises = this.purchase.getChoises();
		if ( choises != null &&  !choises.isEmpty() ) {
			Choise choiseOne = (Choise) choises.iterator().next();
			Collection deliveryAddressChoises = choiseOne.getDeliveryAddressChoises();
			if ( deliveryAddressChoises != null && !deliveryAddressChoises.isEmpty() ) {
				DeliveryAddressChoise deliveryAddressChoise = (DeliveryAddressChoise) 
						deliveryAddressChoises.iterator().next();
				DeliveryAddress deliveryAddress = deliveryAddressChoise.getDeliveryAddress();
				if ( deliveryAddress != null ) {
					deliveryAddress.getName();
					if ( deliveryAddress.getPrefecture() != null ) {
						deliveryAddress.getPrefecture().getName();		
					}					
				}
				return deliveryAddress;
			}
		}
		return null;
	}
}
