
package net.malta.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
public class PaymentMethodTest {

    private PaymentMethod method;

    private PaymentMethod method2;

    private PaymentMethod method3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
    	method = PaymentMethod.Factory.newInstance();

    	List purchaseList = new ArrayList();
    	Purchase purchase = Purchase.Factory.newInstance();
    	purchaseList.add(purchase);

    	method.setId(new Integer(1));
    	method.setName("name");
    	method.setNote("note");
    	method.setPurchases(purchaseList);

    	method.getId();
    	method.getName();
    	method.getNote();
    	method.getPurchases();

    	method2 = PaymentMethod.Factory.newInstance();
    	method2.setId(new Integer(2));

    	method3 = PaymentMethod.Factory.newInstance();
    	method3.setId(new Integer(1));

    	assertTrue(method.equals(method));
    	assertFalse(method.equals(Item.Factory.newInstance()));
    	assertFalse(method.equals(method2));
    	assertTrue(method.equals(method3));

    	method.hashCode();
    	PaymentMethod.Factory.newInstance().hashCode();

    	PaymentMethod.Factory.newInstance("name", "note");
    	PaymentMethod.Factory.newInstance("name", "note", purchaseList);
    }

    @After
    public void checkExpectations() {
    }
}
