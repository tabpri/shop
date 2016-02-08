
package net.malta.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class PurchaseDaoBaseTest {

    private PurchaseDaoBase base;

    private Purchase purchase;

    private List purchaseList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new PurchaseDaoImpl();

        purchase = Purchase.Factory.newInstance();

        purchaseList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	Purchase purchase = Purchase.Factory.newInstance();
        	purchase.setId(new Integer(i + 1));
        	purchaseList.add(purchase);
        }

    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PurchaseImpl.class, anyInt); result= purchase;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((Purchase) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PurchaseImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((Purchase) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PurchaseImpl.class, anyInt); result= purchase;
    	}};

    	try {
    		base.load(0, null);
    	} catch (Exception e) {
    		return;
    	}
    	fail();
    }

    @Test
    public void testLoad4() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PurchaseImpl.class, anyInt); result= purchase;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PurchaseImpl.class, anyInt); result= purchase;
    	}};

    	try {
    		base.load(null);
    	} catch (Exception e) {
    		return;
    	}
    	fail();
    }

    @Test
    public void testLoadALL1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.loadAll(net.malta.model.PurchaseImpl.class); result= purchaseList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(purchase); result= purchase;
    	}};

    	Purchase obj = Purchase.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(purchase); result= purchase;
    	}};

    	Purchase obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(purchaseList);
    }

    @Test
    public void testCreate4() {

    	try {
    		purchaseList = null;
			base.create(purchaseList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(purchase); result= purchase;
    	}};

    	int total = 100;
        int carriage = 100;
        int totalordernum = 100;
        boolean shipped = false;
        Date date = new Date();
        boolean temp = true;
        boolean cancelled = false;
        boolean removed = false;

    	base.create(total, carriage, totalordernum, shipped, date, temp, cancelled, removed);
    }

    @Test
    public void testUpdate1() {

    	base.update(purchase);
    }

    @Test
    public void testUpdate2() {

    	try {
    		purchase = null;
			base.update(purchase);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(purchaseList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		purchaseList = null;
			base.update(purchaseList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(purchase);
    }

    @Test
    public void testRemove2() {

    	try {
    		purchase = null;
			base.remove(purchase);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(purchaseList);
    }

    @Test
    public void testRemove4() {

    	try {
    		purchaseList = null;
			base.remove(purchaseList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PurchaseImpl.class, anyInt); result= purchase;
    	}};

    	Integer id = new Integer(1);

    	base.remove(id);
    }

    @Test
    public void testRemove6() {

    	Integer id = null;

    	try {
			base.remove(id);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @After
    public void checkExpectations() {
    }
}
