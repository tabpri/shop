
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
public class DeliveryAddressChoiseDaoBaseTest {

    private DeliveryAddressChoiseDaoBase base;

    private DeliveryAddressChoise deliveryAddressChoise;

    private List deliveryAddressChoiseList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new DeliveryAddressChoiseDaoImpl();

        deliveryAddressChoise = DeliveryAddressChoise.Factory.newInstance();

        deliveryAddressChoiseList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	DeliveryAddressChoise deliveryAddressChoise = DeliveryAddressChoise.Factory.newInstance();
        	deliveryAddressChoise.setId(new Integer(i + 1));
        	deliveryAddressChoiseList.add(deliveryAddressChoise);
        }
    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DeliveryAddressChoiseImpl.class, anyInt); result= deliveryAddressChoise;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((DeliveryAddressChoise) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DeliveryAddressChoiseImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((DeliveryAddressChoise) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DeliveryAddressChoiseImpl.class, anyInt); result= deliveryAddressChoise;
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
    		hibernateTemplate.get(net.malta.model.DeliveryAddressChoiseImpl.class, anyInt); result= deliveryAddressChoise;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DeliveryAddressChoiseImpl.class, anyInt); result= deliveryAddressChoise;
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
    		hibernateTemplate.loadAll(net.malta.model.DeliveryAddressChoiseImpl.class); result= deliveryAddressChoiseList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(deliveryAddressChoise); result= deliveryAddressChoise;
    	}};

    	DeliveryAddressChoise obj = DeliveryAddressChoise.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(deliveryAddressChoise); result= deliveryAddressChoise;
    	}};

    	DeliveryAddressChoise obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(deliveryAddressChoiseList);
    }

    @Test
    public void testCreate4() {

    	try {
    		deliveryAddressChoiseList = null;
			base.create(deliveryAddressChoiseList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(deliveryAddressChoise); result= deliveryAddressChoise;
    	}};

    	int ordernum = 0;
    	String preferreddate = "preferreddate";
    	String preferredtime = "preferredtime";

    	base.create(ordernum, preferreddate, preferredtime);
    }

    @Test
    public void testCreate6() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(deliveryAddressChoise); result= deliveryAddressChoise;
    	}};

    	Choise choise = Choise.Factory.newInstance();
    	DeliveryAddress deliveryAddress = DeliveryAddress.Factory.newInstance();
    	int ordernum = 0;
    	String preferreddate = "preferreddate";
    	String preferredtime = "preferredtime";

    	base.create(choise, deliveryAddress, ordernum, preferreddate, preferredtime);
    }

    @Test
    public void testUpdate1() {

    	base.update(deliveryAddressChoise);
    }

    @Test
    public void testUpdate2() {

    	try {
    		deliveryAddressChoise = null;
			base.update(deliveryAddressChoise);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(deliveryAddressChoiseList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		deliveryAddressChoiseList = null;
			base.update(deliveryAddressChoiseList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(deliveryAddressChoise);
    }

    @Test
    public void testRemove2() {

    	try {
    		deliveryAddressChoise = null;
			base.remove(deliveryAddressChoise);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(deliveryAddressChoiseList);
    }

    @Test
    public void testRemove4() {

    	try {
    		deliveryAddressChoiseList = null;
			base.remove(deliveryAddressChoiseList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DeliveryAddressChoiseImpl.class, anyInt); result= deliveryAddressChoise;
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
