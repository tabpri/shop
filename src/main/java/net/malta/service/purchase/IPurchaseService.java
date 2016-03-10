package net.malta.service.purchase;

import net.malta.model.PublicUser;
import net.malta.model.Purchase;

public interface IPurchaseService {

	public Purchase getPurchase(Integer id);

	public Purchase createPurchase(Purchase purchase);

	public Purchase updatePurchase(Purchase purchase);

	public Purchase getUserCurrentPurchase(Integer userId);

	public void updatePaymentMethod(Purchase purchase, Integer paymentMethod);

	public Purchase confirmPurchase(Integer id);

	public Purchase createTempPurchase(PublicUser user);

}