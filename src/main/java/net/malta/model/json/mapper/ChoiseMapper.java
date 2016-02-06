/**
 * @author SB
 */
package net.malta.model.json.mapper;

import net.malta.model.Choise;
import net.malta.model.Purchase;

public class ChoiseMapper implements IMapper<Choise, net.malta.model.json.Choise>{

	@Override
	public void map(Choise choise, net.malta.model.json.Choise choiseJSON) {
		
		//purchase
		Purchase purchase = choise.getPurchase();		
		net.malta.model.json.Purchase purchaseJSON = new net.malta.model.json.Purchase();
		purchaseJSON.setPurchaseId(purchase.getId());
		purchaseJSON.setPurchasetotal(purchase.getTotal());

		// choise
		choiseJSON.setPurchase(purchaseJSON);
		choiseJSON.setId(choise.getId());
		choiseJSON.setItem(choise.getItem().getId());
		choiseJSON.setImg(choise.getImg());
		choiseJSON.setOrdernum(choise.getOrdernum());
		choiseJSON.setPricewithtax(choise.getPricewithtax());
		choiseJSON.setWrapping(choise.isWrapping());
		choiseJSON.setVarietychoise(choise.getVarietychoise());
	}

}
