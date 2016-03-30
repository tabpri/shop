package net.malta.service.purchase;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import net.malta.model.Purchase;
import net.malta.model.json.mapper.PurchasesMapper;
import net.malta.web.utils.JSONUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false)
@ContextConfiguration({"classpath:applicationContext-localDataSource.xml","classpath:applicationContext.xml","classpath:applicationContext-mail.xml"})
@Transactional
public class PurchaseServiceTest {

	@Autowired
	IPurchaseService purchaseService;
	
	@Autowired
	PurchasesMapper purchasesMapper;
	
	@Test
	@Ignore
	public void testGetPurchases() {
		List<Purchase> purchases = purchaseService.getPurchases(263);
		for (Purchase purchase : purchases) {
			System.out.println("purchase " + purchase.getId());
		}
		List<net.malta.model.purchase.json.Purchase> purchaseJsons = purchasesMapper.map(purchases, 
				new ArrayList<net.malta.model.purchase.json.Purchase>());

		String json = JSONUtil.serialize(purchaseJsons);
		System.out.println("json:"+json);
	}
	
	@Test
	public void testConfirmPurchase() {
		purchaseService.confirmPurchase(335);
		try {
			Thread.sleep(1000*60); // wait for a minute
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
