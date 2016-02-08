
package net.malta.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mockit.integration.junit4.JMockit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class PurchaseTest {

    private Purchase purchase;

    private Purchase purchase2;

    private Purchase purchase3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
    	purchase = Purchase.Factory.newInstance();

    	List choiseList = new ArrayList();
    	Choise choise = Choise.Factory.newInstance();
    	choiseList.add(choise);

    	purchase.setId(new Integer(1));
    	purchase.setTotal(100);
    	purchase.setCarriage(10);
    	purchase.setTotalordernum(100);
    	purchase.setShipped(false);
    	purchase.setDate(new Date());
    	purchase.setTemp(false);
    	purchase.setCancelled(false);
    	purchase.setRemoved(false);
    	purchase.setPublicUser(PublicUser.Factory.newInstance());
    	purchase.setChoises(choiseList);
    	purchase.setPaymentMethod(PaymentMethod.Factory.newInstance());

    	purchase.getId();
    	purchase.getTotal();
    	purchase.getCarriage();
    	purchase.getTotalordernum();
    	purchase.isShipped();
    	purchase.getDate();
    	purchase.isTemp();
    	purchase.isCancelled();
    	purchase.isRemoved();
    	purchase.getPublicUser();
    	purchase.getChoises();
    	purchase.getPaymentMethod();

    	purchase2 = Purchase.Factory.newInstance();
    	purchase2.setId(new Integer(2));

    	purchase3 = Purchase.Factory.newInstance();
    	purchase3.setId(new Integer(1));

    	assertTrue(purchase.equals(purchase));
    	assertFalse(purchase.equals(Item.Factory.newInstance()));
    	assertFalse(purchase.equals(purchase2));
    	assertTrue(purchase.equals(purchase3));

    	purchase.hashCode();
    	Purchase.Factory.newInstance().hashCode();

    	Purchase.Factory.newInstance(100, 10, 1, false, new Date(), false, false, false);
    	Purchase.Factory.newInstance(100, 10, 1, false, new Date(), false, false, false,
    			PublicUser.Factory.newInstance(), choiseList, PaymentMethod.Factory.newInstance());

    }

    @After
    public void checkExpectations() {
    }
}
