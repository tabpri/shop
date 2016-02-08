
package net.malta.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
public class GiftCardDaoBaseTest {

    private GiftCardDaoBase base;

    private GiftCard giftCard;

    private List giftCardList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new GiftCardDaoImpl();

        giftCard = GiftCard.Factory.newInstance();

        giftCardList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	GiftCard giftCard = GiftCard.Factory.newInstance();
        	giftCard.setId(new Integer(i + 1));
        	giftCardList.add(giftCard);
        }
    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.GiftCardImpl.class, anyInt); result= giftCard;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((GiftCard) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.GiftCardImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((GiftCard) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.GiftCardImpl.class, anyInt); result= giftCard;
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
    		hibernateTemplate.get(net.malta.model.GiftCardImpl.class, anyInt); result= giftCard;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.GiftCardImpl.class, anyInt); result= giftCard;
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
    		hibernateTemplate.loadAll(net.malta.model.GiftCardImpl.class); result= giftCardList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(giftCard); result= giftCard;
    	}};

    	GiftCard obj = GiftCard.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(giftCard); result= giftCard;
    	}};

    	GiftCard obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(giftCardList);
    }

    @Test
    public void testCreate4() {

    	try {
    		giftCardList = null;
			base.create(giftCardList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(giftCard); result= giftCard;
    	}};

    	String name = "name";

    	base.create(name);
    }

    @Test
    public void testUpdate1() {

    	base.update(giftCard);
    }

    @Test
    public void testUpdate2() {

    	try {
    		giftCard = null;
			base.update(giftCard);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(giftCardList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		giftCardList = null;
			base.update(giftCardList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(giftCard);
    }

    @Test
    public void testRemove2() {

    	try {
    		giftCard = null;
			base.remove(giftCard);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(giftCardList);
    }

    @Test
    public void testRemove4() {

    	try {
    		giftCardList = null;
			base.remove(giftCardList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.GiftCardImpl.class, anyInt); result= giftCard;
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
