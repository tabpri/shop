/**
 * @author SB
 */
package net.malta.model.json.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.malta.mapper.IMapper;
import net.malta.model.Choise;
import net.malta.model.Purchase;

@Component
public class PurchaseChoiseMapper implements IMapper<Choise, net.malta.model.purchase.json.Choise>{

	@Autowired
	private ChoiseMapper choiseMapper; 
	
	@Override
	public net.malta.model.purchase.json.Choise map(Choise choise, net.malta.model.purchase.json.Choise choiseJSON) {

		choiseMapper.map(choise, choiseJSON);

		//purchase
		Purchase purchase = choise.getPurchase();		
		net.malta.model.purchase.json.Purchase purchaseJSON = new net.malta.model.purchase.json.Purchase();
		purchaseJSON.setPurchaseId(purchase.getId());
		purchaseJSON.setPurchasetotal(purchase.getTotal());
		
		// choise
		choiseJSON.setPurchase(purchaseJSON);
		return choiseJSON;
	}

	public void setChoiseMapper(ChoiseMapper choiseMapper) {
		this.choiseMapper = choiseMapper;
	}
}
