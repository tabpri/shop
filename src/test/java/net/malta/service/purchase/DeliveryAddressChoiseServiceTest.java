package net.malta.service.purchase;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.purchase.PurchaseDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration({"classpath:applicationContext-localDataSource.xml","classpath:applicationContext.xml"})
@Transactional
@Ignore
public class DeliveryAddressChoiseServiceTest {
	
	@Autowired
	IDeliveryAddressChoiseService service;
	
	@Autowired
	PurchaseDAO purchaseDAO;
	
	@Test
	public void testUpdateChoisesWithDeliveryAddress() {
		Integer purchaseId = 335;
		Integer userId = 200;
		Integer deliveryAddressId = 22;
		service.updateChoisesWithDeliveryAddress(purchaseId, userId, deliveryAddressId);
		// test the values manually
	}
}
