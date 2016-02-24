package net.malta.service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.beans.ValidationError;
import net.malta.dao.purchase.ChoiseDAO;
import net.malta.model.Choise;
import net.malta.model.ChoiseImpl;
import net.malta.model.Item;
import net.malta.model.Purchase;
import net.malta.model.purchase.wrapper.ChoiseTotal;
import net.malta.model.purchase.wrapper.PurchaseTotal;
import net.malta.model.validator.ValidationException;
import net.malta.model.validator.constants.ChoiseConstants;
import net.malta.service.post.IWPPostsService;
import net.malta.service.product.IItemService;

@Service
public class ChoiseService implements IChoiseService {

	@Autowired
	private IPurchaseService purchaseService;
	
	@Autowired
	private IWPPostsService postsService;

	@Autowired
	private IItemService itemService;
	
	@Autowired
	private ChoiseDAO choiseDAO;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Choise getChoise(Integer id) {
		Choise choise = choiseDAO.find(id);
		initialize(choise);
		return choise;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Choise addChoise(Integer purchaseId,Choise choise) {
		
		Integer itemId = choise.getWp_posts_id();
		Item item = choise.getItem();
		choise.setItem(itemService.getItem(item.getId()));
		
		// post information
		choise.setImg(postsService.getImg(itemId));
		choise.setName(postsService.getName(itemId));
		item.setPricewithtax(postsService.getPrice(itemId));
		choiseUpdate(purchaseId, choise);
		return choise;		
	}

	@SuppressWarnings("unchecked")
	private void choiseUpdate(Integer purchaseId, Choise choise) {
		
		new ChoiseTotal(choise).calcAndSetTotal();
		
		Purchase purchase = purchaseService.getPurchase(purchaseId);
		choise.setPurchase(purchase);		
		choiseDAO.saveOrUpdate((ChoiseImpl) choise);
		
		//purchase
		purchase.getChoises().add(choise);
		new PurchaseTotal(purchase).calcAndSetTotal();		
		purchaseService.updatePurchase(purchase);		
		initialize(choise);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Choise updateChoise(Integer purchaseId,Choise choiseUpdated) {
		Integer choiseId = choiseUpdated.getId();
		Choise choise = choiseDAO.getChoise(purchaseId,choiseId);
		if ( choise != null ) {
			choise.setOrdernum(choiseUpdated.getOrdernum());
			choiseUpdate(purchaseId, choise);
		} else {
			throw new ValidationException(new ValidationError(ChoiseConstants.CHOISE_DOESNOTEXIST, choiseId, purchaseId));			
		}
		return choise;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteChoise(Integer purchaseId,Integer choiseId) {
		Choise choise = choiseDAO.getChoise(purchaseId,choiseId);
		if ( choise != null ) {
			choiseDAO.delete((ChoiseImpl) choise);
			Purchase purchase = purchaseService.getPurchase(purchaseId);
			new PurchaseTotal(purchase).calcAndSetTotal();
			purchaseService.updatePurchase(purchase);		
		} else {
			throw new ValidationException(new ValidationError(ChoiseConstants.CHOISE_DOESNOTEXIST, choiseId, purchaseId));
		}
	}

	private void initialize(Choise choise) {
		choise.getPurchase().getTotal();
		choise.getItem().getName();
	}
	
	public void setPurchaseService(IPurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	public void setPostsService(IWPPostsService postsService) {
		this.postsService = postsService;
	}

	public void setItemService(IItemService itemService) {
		this.itemService = itemService;
	}
	
	public void setChoiseDAO(ChoiseDAO choiseDAO) {
		this.choiseDAO = choiseDAO;
	}	
}