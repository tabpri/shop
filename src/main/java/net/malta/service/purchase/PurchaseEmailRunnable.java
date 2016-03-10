package net.malta.service.purchase;

import org.springframework.beans.factory.annotation.Autowired;

import net.malta.model.Purchase;

public class PurchaseEmailRunnable implements Runnable {

	@Autowired
	IPurchaseEmailService emailService;
	
	private Purchase purchase;
	
	public PurchaseEmailRunnable(Purchase purchase) {
		this.purchase = purchase;
	}
	
	@Override
	public void run() {
		emailService.sendConfirmationEmail(purchase);
	}

}
