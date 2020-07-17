package net.malta.service.purchase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import net.malta.model.Purchase;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration({"classpath:applicationContext-localDataSource.xml","classpath:applicationContext.xml","classpath:applicationContext-mail.xml"})
@Transactional
public class PurchaseEmailServiceTest {

	@Autowired
	IPurchaseEmailService purchaseEmailService;
	
	@Autowired
	IPurchaseService purchaseService;
	
	@Test
	public void testPurchaseConfirmationEmail() {
		Purchase purchase = purchaseService.getPurchase(335);
		purchaseEmailService.sendConfirmationEmail(purchase);
	}
}
