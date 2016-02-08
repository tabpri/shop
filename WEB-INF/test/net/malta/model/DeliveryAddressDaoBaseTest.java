
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
public class DeliveryAddressDaoBaseTest {

    private DeliveryAddressDaoBase base;

    private DeliveryAddress deliveryAddress;

    private List deliveryAddressList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new DeliveryAddressDaoImpl();

        deliveryAddress = DeliveryAddress.Factory.newInstance();

        deliveryAddressList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	DeliveryAddress deliveryAddress = DeliveryAddress.Factory.newInstance();
        	deliveryAddress.setId(new Integer(i + 1));
        	deliveryAddressList.add(deliveryAddress);
        }

    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DeliveryAddressImpl.class, anyInt); result= deliveryAddress;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((DeliveryAddress) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DeliveryAddressImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((DeliveryAddress) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DeliveryAddressImpl.class, anyInt); result= deliveryAddress;
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
    		hibernateTemplate.get(net.malta.model.DeliveryAddressImpl.class, anyInt); result= deliveryAddress;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DeliveryAddressImpl.class, anyInt); result= deliveryAddress;
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
    		hibernateTemplate.loadAll(net.malta.model.DeliveryAddressImpl.class); result= deliveryAddressList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(deliveryAddress); result= deliveryAddress;
    	}};

    	DeliveryAddress obj = DeliveryAddress.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(deliveryAddress); result= deliveryAddress;
    	}};

    	DeliveryAddress obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(deliveryAddressList);
    }

    @Test
    public void testCreate4() {

    	try {
    		deliveryAddressList = null;
			base.create(deliveryAddressList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(deliveryAddress); result= deliveryAddress;
    	}};

        String name = "";
        String kana = "";
        int zipthree = 111;
        int zipfour = 2222;
        String phone = "";
        String address = "";
        String buildingname = "";
        String pref = "";
        String preferreddate = "";
        String preferredtime = "";
        boolean hasgiftcard = true;

    	base.create(name, kana, zipthree, zipfour, phone, address, buildingname, pref, preferreddate, preferredtime, hasgiftcard);
    }

    @Test
    public void testCreate6() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(deliveryAddress); result= deliveryAddress;
    	}};

    	PublicUser publicUser = PublicUser.Factory.newInstance();
        String name = "";
        String kana = "";
        int zipthree = 111;
        int zipfour = 2222;
        String phone = "";
        String address = "";
        String buildingname = "";
        String pref = "";
        String preferreddate = "";
        String preferredtime = "";
        boolean hasgiftcard = true;

    	base.create(address, buildingname, hasgiftcard, kana, name, phone, pref, preferreddate, preferredtime, publicUser, zipfour, zipthree);
    }

    @Test
    public void testUpdate1() {

    	base.update(deliveryAddress);
    }

    @Test
    public void testUpdate2() {

    	try {
    		deliveryAddress = null;
			base.update(deliveryAddress);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(deliveryAddressList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		deliveryAddressList = null;
			base.update(deliveryAddressList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(deliveryAddress);
    }

    @Test
    public void testRemove2() {

    	try {
    		deliveryAddress = null;
			base.remove(deliveryAddress);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(deliveryAddressList);
    }

    @Test
    public void testRemove4() {

    	try {
    		deliveryAddressList = null;
			base.remove(deliveryAddressList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DeliveryAddressImpl.class, anyInt); result= deliveryAddress;
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
