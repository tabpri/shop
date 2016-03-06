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
		Integer carriage = item.getCarriage().getValue();
		Integer priceWithTax = item.getPricewithtax();
		Integer quantity = choise.getOrdernum();
		choisetotal(carriage, priceWithTax, quantity);
	}

	public void carriageChanged(Integer carriage) {
		Item item = choise.getItem();
		Integer priceWithTax = item.getPricewithtax();
		Integer quantity = choise.getOrdernum();
		choisetotal(carriage, priceWithTax, quantity);
	}

	private void choisetotal(Integer carriage, Integer priceWithTax, Integer quantity) {
		choise.setPricewithtax( ( priceWithTax + carriage) * quantity );
		choise.setCarriage((carriage * quantity));
	}
		
}
