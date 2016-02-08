
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
public class PaymentMethodDaoBaseTest {

    private PaymentMethodDaoBase base;

    private PaymentMethod paymentMethod;

    private List paymentMethodList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new PaymentMethodDaoImpl();

        paymentMethod = PaymentMethod.Factory.newInstance();

        paymentMethodList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	PaymentMethod paymentMethod = PaymentMethod.Factory.newInstance();
        	paymentMethod.setId(new Integer(i + 1));
        	paymentMethodList.add(paymentMethod);
        }
    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PaymentMethodImpl.class, anyInt); result= paymentMethod;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((PaymentMethod) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PaymentMethodImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((PaymentMethod) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PaymentMethodImpl.class, anyInt); result= paymentMethod;
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
    		hibernateTemplate.get(net.malta.model.PaymentMethodImpl.class, anyInt); result= paymentMethod;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PaymentMethodImpl.class, anyInt); result= paymentMethod;
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
    		hibernateTemplate.loadAll(net.malta.model.PaymentMethodImpl.class); result= paymentMethodList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(paymentMethod); result= paymentMethod;
    	}};

    	PaymentMethod obj = PaymentMethod.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(paymentMethod); result= paymentMethod;
    	}};

    	PaymentMethod obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(paymentMethodList);
    }

    @Test
    public void testCreate4() {

    	try {
    		paymentMethodList = null;
			base.create(paymentMethodList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(paymentMethod); result= paymentMethod;
    	}};

    	String name = "name";
    	String note = "note";

    	base.create(name, note);
    }

    @Test
    public void testUpdate1() {

    	base.update(paymentMethod);
    }

    @Test
    public void testUpdate2() {

    	try {
    		paymentMethod = null;
			base.update(paymentMethod);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(paymentMethodList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		paymentMethodList = null;
			base.update(paymentMethodList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(paymentMethod);
    }

    @Test
    public void testRemove2() {

    	try {
    		paymentMethod = null;
			base.remove(paymentMethod);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(paymentMethodList);
    }

    @Test
    public void testRemove4() {

    	try {
    		paymentMethodList = null;
			base.remove(paymentMethodList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PaymentMethodImpl.class, anyInt); result= paymentMethod;
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
