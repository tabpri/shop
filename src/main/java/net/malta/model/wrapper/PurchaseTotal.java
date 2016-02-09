/**
 * @author SB
 */
package net.malta.model.wrapper;

import java.util.Collection;

import net.malta.model.Choise;
import net.malta.model.Purchase;

// calculates the purchase total from all the choises
public class PurchaseTotal implements ITotal<Purchase>{

	private Purchase purchase;
	
	
	public PurchaseTotal(Purchase purchase) {
		this.purchase = purchase;
	}

	@Override
	public void calcAndSetTotal() {
			Integer purchaseTotal = 0;
			@SuppressWarnings("unchecked")
			Collection<Choise> choises = purchase.getChoises();
			for (Choise choise : choises ){
				purchaseTotal +=choise.getPricewithtax();
			}
			purchase.setTotal(purchaseTotal);
	}
}
