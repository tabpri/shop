/**
 * @author SB
 */

package net.malta.service.purchase;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.purchase.PurchaseDAO;
import net.malta.model.Choise;
import net.malta.model.Item;
import net.malta.model.Purchase;
import net.malta.model.PurchaseImpl;
import net.malta.model.purchase.wrapper.PurchaseTotal;

@Service
public class PurchaseService implements IPurchaseService {

	@Autowired
	private PurchaseDAO purchaseDAO;
	
	public void setPurchaseDAO(PurchaseDAO purchaseDAO) {
		this.purchaseDAO = purchaseDAO;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public Purchase getPurchase(Integer id) {
		Purchase purchase = purchaseDAO.find(id);
		initialize(purchase);
		return purchase;
	}

	// call this method for the associations data
	private void initialize(Purchase purchase) {
		purchase.getPublicUser().getName();		
		Iterator iterator = purchase.getChoises().iterator();
		while(iterator.hasNext()) {
			Choise choise = (Choise) iterator.next();
			Item item = choise.getItem();
			item.getId();
			item.getCarriage().getValue();
		}
		if ( purchase.getPaymentMethod() != null ) {
			purchase.getPaymentMethod().getName();
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public Purchase createPurchase(Purchase purchase) {
		PurchaseTotal purchaseTotal = new PurchaseTotal(purchase);
		purchaseTotal.calcAndSetTotal();
		purchaseDAO.saveOrUpdate((PurchaseImpl) purchase);
		return purchase;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public Purchase updatePurchase(Purchase purchase) {
		PurchaseTotal purchaseTotal = new PurchaseTotal(purchase);
		purchaseTotal.calcAndSetTotal();
		purchaseDAO.saveOrUpdate((PurchaseImpl) purchase);
		return purchase;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public Purchase getUserCurrentPurchase(Integer userId) {
		return purchaseDAO.getPurchase(userId, true);
	}
}