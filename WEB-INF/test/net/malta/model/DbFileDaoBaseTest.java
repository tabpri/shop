
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
public class DbFileDaoBaseTest {

    private DbFileDaoBase base;

    private DbFile dbFile;

    private List dbFileList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new DbFileDaoImpl();

        dbFile = DbFile.Factory.newInstance();

        dbFileList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	DbFile dbFile = DbFile.Factory.newInstance();
        	dbFile.setId(new Integer(i + 1));
        	dbFileList.add(dbFile);
        }
    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DbFileImpl.class, anyInt); result= dbFile;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((DbFile) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DbFileImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((DbFile) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DbFileImpl.class, anyInt); result= dbFile;
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
    		hibernateTemplate.get(net.malta.model.DbFileImpl.class, anyInt); result= dbFile;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DbFileImpl.class, anyInt); result= dbFile;
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
    		hibernateTemplate.loadAll(net.malta.model.DbFileImpl.class); result= dbFileList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(dbFile); result= dbFile;
    	}};

    	DbFile obj = DbFile.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(dbFile); result= dbFile;
    	}};

    	DbFile obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(dbFileList);
    }

    @Test
    public void testCreate4() {

    	try {
    		dbFileList = null;
			base.create(dbFileList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(dbFile); result= dbFile;
    	}};

    	byte[] data = "data".getBytes();

    	base.create(data);
    }

    @Test
    public void testCreate6() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(dbFile); result= dbFile;
    	}};

    	int transform = 0;
    	byte[] data = "data".getBytes();

    	base.create(transform, data);
    }

    @Test
    public void testUpdate1() {

    	base.update(dbFile);
    }

    @Test
    public void testUpdate2() {

    	try {
    		dbFile = null;
			base.update(dbFile);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(dbFileList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		dbFileList = null;
			base.update(dbFileList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(dbFile);
    }

    @Test
    public void testRemove2() {

    	try {
    		dbFile = null;
			base.remove(dbFile);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(dbFileList);
    }

    @Test
    public void testRemove4() {

    	try {
    		dbFileList = null;
			base.remove(dbFileList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.DbFileImpl.class, anyInt); result= dbFile;
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
