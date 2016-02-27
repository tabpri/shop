
package net.malta.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
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
public class ProductDaoBaseTest {

    private ProductDaoBase base;

    private Product product;

    private List productList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new ProductDaoImpl();

        product = Product.Factory.newInstance();

        productList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	Product product = Product.Factory.newInstance();
        	product.setId(new Integer(i + 1));
        	productList.add(product);
        }
    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ProductImpl.class, anyInt); result= product;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((Product) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ProductImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((Product) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ProductImpl.class, anyInt); result= product;
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
    		hibernateTemplate.get(net.malta.model.ProductImpl.class, anyInt); result= product;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ProductImpl.class, anyInt); result= product;
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
    		hibernateTemplate.loadAll(net.malta.model.ProductImpl.class); result= productList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(product); result= product;
    	}};

    	Product obj = Product.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(product); result= product;
    	}};

    	Product obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(productList);
    }

    @Test
    public void testCreate4() {

    	try {
    		productList = null;
			base.create(productList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(product); result= product;
    	}};

    	String no = "no";
        String name = "name";
        Date date = new Date();
        boolean pub = true;
        int pricewithtax = 100;
        String size = "size";
        String material = "material";
        String catchcopy = "catchcopy";
        String description = "description";
        String note = "note";
        int stocknum = 2;
        boolean removed = false;
        String mainitemname = "mainitemname";
        String english = "english";

    	base.create(no, name, date, pub, pricewithtax, size, material, catchcopy, description, note, stocknum, removed, mainitemname, english);
    }

    @Test
    public void testUpdate1() {

    	base.update(product);
    }

    @Test
    public void testUpdate2() {

    	try {
    		product = null;
			base.update(product);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(productList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		productList = null;
			base.update(productList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(product);
    }

    @Test
    public void testRemove2() {

    	try {
    		product = null;
			base.remove(product);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(productList);
    }

    @Test
    public void testRemove4() {

    	try {
    		productList = null;
			base.remove(productList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ProductImpl.class, anyInt); result= product;
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
