package net.malta.service.purchase;

import net.malta.model.Purchase;

public interface IPurchaseEmailService {

	void sendConfirmationEmail(Purchase purchase);

}