package net.malta.service.purchase;

import net.malta.model.Purchase;

public interface IPurchaseService {

	Purchase getPurchase(Integer id);

	Purchase createPurchase(Purchase purchase);

	Purchase updatePurchase(Purchase purchase);

	Purchase getUserCurrentPurchase(Integer userId);

}