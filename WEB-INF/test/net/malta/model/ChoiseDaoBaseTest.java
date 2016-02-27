
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
public class ChoiseDaoBaseTest {

    private ChoiseDaoBase base;

    private Choise choise;

    private List choiseList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new ChoiseDaoImpl();

        choise = Choise.Factory.newInstance();

        choiseList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	Choise choise = Choise.Factory.newInstance();
        	choise.setId(new Integer(i + 1));
        	choiseList.add(choise);
        }

    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ChoiseImpl.class, anyInt); result= choise;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((Choise) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ChoiseImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((Choise) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ChoiseImpl.class, anyInt); result= choise;
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
    		hibernateTemplate.get(net.malta.model.ChoiseImpl.class, anyInt); result= choise;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ChoiseImpl.class, anyInt); result= choise;
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
    		hibernateTemplate.loadAll(net.malta.model.ChoiseImpl.class); result= choiseList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(choise); result= choise;
    	}};

    	Choise obj = Choise.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(choise); result= choise;
    	}};

    	Choise obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(choiseList);
    }

    @Test
    public void testCreate4() {

    	try {
    		choiseList = null;
			base.create(choiseList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(choise); result= choise;
    	}};

    	int ordernum = 100;
    	int pricewithtax = 100;
    	boolean wrapping = true;

    	base.create(ordernum, pricewithtax, wrapping);
    }

    @Test
    public void testCreate6() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(choise); result= choise;
    	}};

    	int transform = 1;
    	int ordernum = 100;
    	int pricewithtax = 100;
    	boolean wrapping = true;

    	base.create(transform, ordernum, pricewithtax, wrapping);
    }

    @Test
    public void testCreate7() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(choise); result= choise;
    	}};

    	Item item = Item.Factory.newInstance();
    	int ordernum = 100;
    	int pricewithtax = 100;
    	Purchase purchase = Purchase.Factory.newInstance();
    	boolean wrapping = true;

    	base.create(item, ordernum, pricewithtax, purchase, wrapping);
    }

    @Test
    public void testUpdate1() {

    	base.update(choise);
    }

    @Test
    public void testUpdate2() {

    	try {
    		choise = null;
			base.update(choise);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(choiseList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		choiseList = null;
			base.update(choiseList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(choise);
    }

    @Test
    public void testRemove2() {

    	try {
    		choise = null;
			base.remove(choise);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(choiseList);
    }

    @Test
    public void testRemove4() {

    	try {
    		choiseList = null;
			base.remove(choiseList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ChoiseImpl.class, anyInt); result= choise;
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
