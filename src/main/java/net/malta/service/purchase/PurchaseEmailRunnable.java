package net.malta.service.purchase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.malta.model.Purchase;

public class PurchaseEmailRunnable implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseEmailRunnable.class);

	private IPurchaseEmailService emailService;
	
	private Purchase purchase;
	
	public PurchaseEmailRunnable(Purchase purchase) {
		this.purchase = purchase;
	}
	
	@Override
	public void run() {
		logger.info("purchase email confirmation start - purchase: " + purchase.getId());		
		emailService.sendConfirmationEmail(purchase);
		logger.info("purchase email confirmation end - purchase: " + purchase.getId());
	}

	public void setEmailService(IPurchaseEmailService emailService) {
		this.emailService = emailService;
	}
}
