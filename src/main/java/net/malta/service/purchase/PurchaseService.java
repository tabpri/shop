/**
 * @author SB
 */

package net.malta.service.purchase;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.enclosing.util.StringFullfiller;
import net.malta.dao.payment.PaymentMethodDAO;
import net.malta.dao.payment.PaymentStatusDAO;
import net.malta.dao.purchase.PurchaseDAO;
import net.malta.model.Choise;
import net.malta.model.Item;
import net.malta.model.PaymentMethod;
import net.malta.model.PaymentStatus;
import net.malta.model.PublicUser;
import net.malta.model.Purchase;
import net.malta.model.PurchaseImpl;
import net.malta.model.purchase.wrapper.PurchaseDeliveryAddress;
import net.malta.model.purchase.wrapper.PurchaseTotal;

@Service
public class PurchaseService implements IPurchaseService {

	@Autowired
	private PurchaseDAO purchaseDAO;
	
	@Autowired
	private PaymentMethodDAO paymentMethodDAO; 
	
	@Autowired
	private IPurchaseEmailService emailService;
	
	@Autowired
	private PaymentStatusDAO paymentStatusDAO; 
	
	private static final Logger logger = LoggerFactory.getLogger(PurchaseService.class);

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
		// load the delivery address if available
		new PurchaseDeliveryAddress(purchase).getDeliveryAddress();		
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
	public Purchase confirmPurchase(Integer id) {		
		Purchase purchase = getPurchase(id);
		purchase.setTemp(false);
		purchase.setDate(new Date());
		PaymentStatus paymentStatus = purchase.getPayment();
		//paymentStatus.setPaymentStatus(PaymentStatusEnum.COMPLETED);
		purchase.setPayment(paymentStatus);
		paymentStatusDAO.saveOrUpdate(paymentStatus);		
		purchaseDAO.saveOrUpdate((PurchaseImpl) purchase);
		sendEmail(purchase);
		return purchase;
	}	
	
	private void sendEmail(Purchase purchase) {
		PurchaseEmailRunnable emailRun = new PurchaseEmailRunnable(purchase);
		new PurchaseDeliveryAddress(purchase).getDeliveryAddress();
		emailRun.setEmailService(emailService);
		logger.info("purchase email confirmation start - purchase: " + purchase.getId());
		new Thread(emailRun).start();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public Purchase getUserCurrentPurchase(Integer userId) {
		return purchaseDAO.getPurchase(userId, true);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public void updatePaymentMethod(Purchase purchase,Integer paymentMethodId) {
		PaymentMethod paymentMethod = paymentMethodDAO.find(paymentMethodId);
		purchase.setPaymentMethod(paymentMethod);
		updatePurchase(purchase);
	}	

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public Purchase createTempPurchase(PublicUser user) {		
		Purchase purchase = new PurchaseImpl();
		StringFullfiller.fullfil(purchase);
		purchase.setTemp(true);
		purchase.setPublicUser(user);		
		purchaseDAO.saveOrUpdate((PurchaseImpl) purchase);		
		return purchase;
	}	
	
	public void setPurchaseDAO(PurchaseDAO purchaseDAO) {
		this.purchaseDAO = purchaseDAO;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public List<Purchase> getPurchases(Integer userId) {
		List<Purchase> purchases = purchaseDAO.getPurchases(userId);
		for (Purchase purchase : purchases) {
			initialize(purchase);
		}
		return purchases;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public void updatePaymentStatus(Integer id,PaymentStatus paymentStatus) {
		Purchase purchase = purchaseDAO.find(id);
		if ( purchase != null ) {
			PaymentStatus payment = purchase.getPayment();
			payment.setPaymentStatus(paymentStatus.getPaymentStatus());
			payment.setTransactionReference(paymentStatus.getTransactionReference());
			payment.setTransactionDate(paymentStatus.getTransactionDate());
			paymentStatusDAO.saveOrUpdate(payment);
		}
	}
}