
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
public class PrefectureDaoBaseTest {

    private PrefectureDaoBase base;

    private Prefecture prefecture;

    private List prefectureList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new PrefectureDaoImpl();

        prefecture = Prefecture.Factory.newInstance();

        prefectureList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	Prefecture prefecture = Prefecture.Factory.newInstance();
        	prefecture.setId(new Integer(i + 1));
        	prefectureList.add(prefecture);
        }

    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PrefectureImpl.class, anyInt); result= prefecture;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((Prefecture) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PrefectureImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((Prefecture) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PrefectureImpl.class, anyInt); result= prefecture;
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
    		hibernateTemplate.get(net.malta.model.PrefectureImpl.class, anyInt); result= prefecture;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PrefectureImpl.class, anyInt); result= prefecture;
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
    		hibernateTemplate.loadAll(net.malta.model.PrefectureImpl.class); result= prefectureList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(prefecture); result= prefecture;
    	}};

    	Prefecture obj = Prefecture.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(prefecture); result= prefecture;
    	}};

    	Prefecture obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(prefectureList);
    }

    @Test
    public void testCreate4() {

    	try {
    		prefectureList = null;
			base.create(prefectureList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(prefecture); result= prefecture;
    	}};

    	String name = "name";

    	base.create(name);
    }

    @Test
    public void testUpdate1() {

    	base.update(prefecture);
    }

    @Test
    public void testUpdate2() {

    	try {
    		prefecture = null;
			base.update(prefecture);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(prefectureList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		prefectureList = null;
			base.update(prefectureList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(prefecture);
    }

    @Test
    public void testRemove2() {

    	try {
    		prefecture = null;
			base.remove(prefecture);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(prefectureList);
    }

    @Test
    public void testRemove4() {

    	try {
    		prefectureList = null;
			base.remove(prefectureList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.PrefectureImpl.class, anyInt); result= prefecture;
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
