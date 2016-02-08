package net.malta.model.json.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.malta.model.Choise;
import net.malta.model.Purchase;

public class PurchaseMapper implements IMapper<Purchase, net.malta.model.json.Purchase>{

	private ChoisesMapper choisesMapper;
	
	@Override
	public net.malta.model.json.Purchase map(Purchase purchase, net.malta.model.json.Purchase purchaseJSON) {
		
		// purchase info
		purchaseJSON.setPurchaseId(purchase.getId());
		purchaseJSON.setPurchasetotal(purchase.getTotal());
		
		// purchase.choises
		Collection<Choise> choises = ((Collection<Choise>) purchase.getChoises());
		List<net.malta.model.json.Choise> choisesJSON = new ArrayList<net.malta.model.json.Choise>();
		purchaseJSON.setChoises(choisesJSON);
		choisesMapper.map(choises, choisesJSON);
		return purchaseJSON;
	}

	public void setChoisesMapper(ChoisesMapper choisesMapper) {
		this.choisesMapper = choisesMapper;
	}
}
