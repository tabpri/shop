package net.malta.model.json.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.malta.mapper.IMapper;
import net.malta.model.Purchase;

@Component
public class PurchasesMapper implements IMapper<List<Purchase>, List<net.malta.model.purchase.json.Purchase>>{

	@Autowired
	PurchaseMapper purchaseMapper;
	
	@Override
	public List<net.malta.model.purchase.json.Purchase> map(List<Purchase> purchases,
			List<net.malta.model.purchase.json.Purchase> purchasesJSON) {

		for (Purchase purchase : purchases) {
			net.malta.model.purchase.json.Purchase purchaseJSON = new net.malta.model.purchase.json.Purchase();
			purchasesJSON.add(purchaseMapper.map(purchase, purchaseJSON));
		}
		return purchasesJSON;
	}

}
