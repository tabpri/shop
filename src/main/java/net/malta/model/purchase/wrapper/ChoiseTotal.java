/**
 * @author SB
 */
package net.malta.model.purchase.wrapper;

import net.malta.model.Choise;
import net.malta.model.Item;
import net.malta.model.wrapper.ITotal;

// this class is wrapper class calculates the choise total
public class ChoiseTotal implements ITotal<Choise> {

	private Choise choise;
	
	public ChoiseTotal(Choise choise) {
		this.choise = choise;
	}
	
	@Override
	public void calcAndSetTotal() {
		Item item = choise.getItem();
		Integer shippingCost = item.getCarriage().getValue();
		Integer priceWithTax = item.getPricewithtax();
		Integer quantity = choise.getOrdernum();
		choise.setPricewithtax( ( priceWithTax + shippingCost) * quantity );
		choise.setCarriage((shippingCost * quantity));
	}
}
