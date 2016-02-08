
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
public class StaticDataDaoBaseTest {

    private StaticDataDaoBase base;

    private StaticData staticData;

    private List staticDataList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new StaticDataDaoImpl();

        staticData = StaticData.Factory.newInstance();

        staticDataList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	StaticData staticData = StaticData.Factory.newInstance();
        	staticData.setId(new Integer(i + 1));
        	staticDataList.add(staticData);
        }
    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.StaticDataImpl.class, anyInt); result= staticData;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((StaticData) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.StaticDataImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((StaticData) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.StaticDataImpl.class, anyInt); result= staticData;
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
    		hibernateTemplate.get(net.malta.model.StaticDataImpl.class, anyInt); result= staticData;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.StaticDataImpl.class, anyInt); result= staticData;
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
    		hibernateTemplate.loadAll(net.malta.model.StaticDataImpl.class); result= staticDataList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(staticData); result= staticData;
    	}};

    	StaticData obj = StaticData.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(staticData); result= staticData;
    	}};

    	StaticData obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(staticDataList);
    }

    @Test
    public void testCreate4() {

    	try {
    		staticDataList = null;
			base.create(staticDataList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(staticData); result= staticData;
    	}};

    	String fromaddress = "fromaddress";
        String sitename = "sitename";
        String basepath = "basepath";
        boolean unix = true;
        int mapeventspan = 1;
        byte[] noimage = "noimage".getBytes();
        int carriage = 1;
        String creditcardprocesurl = "creditcardprocesurl";
        String contract_code = "contract_code";

    	base.create(fromaddress, sitename, basepath, unix, mapeventspan, noimage, carriage, creditcardprocesurl, contract_code);
    }

    @Test
    public void testUpdate1() {

    	base.update(staticData);
    }

    @Test
    public void testUpdate2() {

    	try {
    		staticData = null;
			base.update(staticData);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(staticDataList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		staticDataList = null;
			base.update(staticDataList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(staticData);
    }

    @Test
    public void testRemove2() {

    	try {
    		staticData = null;
			base.remove(staticData);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(staticDataList);
    }

    @Test
    public void testRemove4() {

    	try {
    		staticDataList = null;
			base.remove(staticDataList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.StaticDataImpl.class, anyInt); result= staticData;
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
