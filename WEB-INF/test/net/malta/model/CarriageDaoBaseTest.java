
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
public class CarriageDaoBaseTest {

    private CarriageDaoBase base;

    private Carriage carriage;

    private List carriageList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new CarriageDaoImpl();

        carriage = Carriage.Factory.newInstance();

        carriageList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	Carriage carriage = Carriage.Factory.newInstance();
        	carriage.setId(new Integer(i + 1));
        	carriageList.add(carriage);
        }
    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.CarriageImpl.class, anyInt); result= carriage;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((Carriage) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.CarriageImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((Carriage) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.CarriageImpl.class, anyInt); result= carriage;
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
    		hibernateTemplate.get(net.malta.model.CarriageImpl.class, anyInt); result= carriage;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.CarriageImpl.class, anyInt); result= carriage;
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
    		hibernateTemplate.loadAll(net.malta.model.CarriageImpl.class); result= carriageList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(carriage); result= carriage;
    	}};

    	Carriage obj = Carriage.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(carriage); result= carriage;
    	}};

    	Carriage obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(carriageList);
    }

    @Test
    public void testCreate4() {

    	try {
    		carriageList = null;
			base.create(carriageList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(carriage); result= carriage;
    	}};

    	String name = "Carriage";
    	int value = 100;

    	base.create(name, value);
    }

    @Test
    public void testUpdate1() {

    	base.update(carriage);
    }

    @Test
    public void testUpdate2() {

    	try {
    		carriage = null;
			base.update(carriage);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(carriageList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		carriageList = null;
			base.update(carriageList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(carriage);
    }

    @Test
    public void testRemove2() {

    	try {
    		carriage = null;
			base.remove(carriage);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(carriageList);
    }

    @Test
    public void testRemove4() {

    	try {
    		carriageList = null;
			base.remove(carriageList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.CarriageImpl.class, anyInt); result= carriage;
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
