
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
public class PublicUserDaoBaseTest {

    private PublicUserDaoBase base;

    private PublicUser publicUser;

    private List publicUserList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new PublicUserDaoImpl();

        publicUser = PublicUser.Factory.newInstance();

        publicUserList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	PublicUser publicUser = PublicUser.Factory.newInstance();
        	publicUser.setId(new Integer(i + 1));
        	publicUserList.add(publicUser);
        }
    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PublicUserImpl.class, anyInt); result= publicUser;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((PublicUser) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PublicUserImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((PublicUser) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PublicUserImpl.class, anyInt); result= publicUser;
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
    		hibernateTemplate.get(net.malta.model.PublicUserImpl.class, anyInt); result= publicUser;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PublicUserImpl.class, anyInt); result= publicUser;
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
    		hibernateTemplate.loadAll(net.malta.model.PublicUserImpl.class); result= publicUserList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(publicUser); result= publicUser;
    	}};

    	PublicUser obj = PublicUser.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(publicUser); result= publicUser;
    	}};

    	PublicUser obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(publicUserList);
    }

    @Test
    public void testCreate4() {

    	try {
    		publicUserList = null;
			base.create(publicUserList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(publicUser); result= publicUser;
    	}};

    	String name = "name";
        String kana = "kana";
        int zipthree = 111;
        int zipfour = 2222;
        String pref = "pref";
        String address = "address";
        String buildingname = "buildingname";
        String mail = "mail";
        String mailforconfirm = "mailforconfirm";
        String phone = "phone";
        int devliveryhour = 18;
        int deliverydate = 20;
        int deliveryphone = 9000;
        String deliverykana = "deliverykana";
        String deliveryblocknumber = "deliveryblocknumber";
        String deliveryaddress = "deliveryaddress";
        String deliveryname = "deliveryname";
        int deliveryzipfour = 111;
        int deliveryzipthree = 2222;
        String deliverypref = "deliverypref";
        boolean delivertodifferentaddress = false;
        boolean male = false;
        int brithday = 11;
        int birthmonth = 12;
        String municipality = "municipality";
        int birthyear = 1900;
        String keitai = "keitai";
        boolean removed = false;
        String password = "password";
        String fax = "fax";
        boolean registed = false;
        boolean temp = false;
        String passwordonetimehash = "passwordonetimehash";

    	base.create(name, kana, zipthree, zipfour, pref, address, buildingname, mail, mailforconfirm,
    			phone, devliveryhour, deliverydate, deliveryphone, deliverykana, deliveryblocknumber,
    			deliveryaddress, deliveryname, deliveryzipfour, deliveryzipthree, deliverypref,
    			delivertodifferentaddress, male, brithday, birthmonth, municipality, birthyear,
    			keitai, removed, password, fax, registed, temp, passwordonetimehash);
    }

    @Test
    public void testUpdate1() {

    	base.update(publicUser);
    }

    @Test
    public void testUpdate2() {

    	try {
    		publicUser = null;
			base.update(publicUser);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(publicUserList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		publicUserList = null;
			base.update(publicUserList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(publicUser);
    }

    @Test
    public void testRemove2() {

    	try {
    		publicUser = null;
			base.remove(publicUser);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(publicUserList);
    }

    @Test
    public void testRemove4() {

    	try {
    		publicUserList = null;
			base.remove(publicUserList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PublicUserImpl.class, anyInt); result= publicUser;
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
