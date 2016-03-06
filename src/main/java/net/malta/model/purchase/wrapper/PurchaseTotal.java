/**
 * @author SB
 */
package net.malta.model.purchase.wrapper;

import java.util.Collection;

import net.malta.model.Choise;
import net.malta.model.Purchase;
import net.malta.model.wrapper.ITotal;

// calculates the purchase total from all the choises
public class PurchaseTotal implements ITotal<Purchase>{

	private Purchase purchase;
	
	
	public PurchaseTotal(Purchase purchase) {
		this.purchase = purchase;
	}

	@Override
	public void calcAndSetTotal() {
			Integer purchaseTotal = 0;
			Integer carriageTotal = 0;
			Integer totalOrderNum = 0;
			@SuppressWarnings("unchecked")
			Collection<Choise> choises = purchase.getChoises();
			for (Choise choise : choises ){
				purchaseTotal +=choise.getPricewithtax();
				carriageTotal +=choise.getCarriage();
				totalOrderNum += choise.getOrdernum();
			}
			purchase.setTotal(purchaseTotal);
			purchase.setCarriage(carriageTotal);
			purchase.setTotalordernum(totalOrderNum);
	}
}
