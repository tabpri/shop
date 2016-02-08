
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
public class ItemDaoBaseTest {

    private ItemDaoBase base;

    private Item item;

    private List itemList;

    @Mocked
    private Session session;

    @Mocked
    private HibernateTemplate hibernateTemplate;

    @Mocked
    private HibernateDaoSupport hibernateDaoSupport;

    @Before
    public void setUp() {
        base = new ItemDaoImpl();

        item = Item.Factory.newInstance();

        itemList = new ArrayList();

        for (int i=0; i < 2; i++) {
        	Item item = Item.Factory.newInstance();
        	item.setId(new Integer(i + 1));
        	itemList.add(item);
        }
    }

    @Test
    public void testLoad1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ItemImpl.class, anyInt); result= item;
    	}};

    	Integer id = new Integer(1);
    	assertNotNull((Item) base.load(0, id));
    }

    @Test
    public void testLoad2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ItemImpl.class, anyInt); result= null;
    	}};

    	Integer id = new Integer(1);
    	assertNull((Item) base.load(0, id));
    }

    @Test
    public void testLoad3() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ItemImpl.class, anyInt); result= item;
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
    		hibernateTemplate.get(net.malta.model.ItemImpl.class, anyInt); result= item;
    	}};

    	Integer id = new Integer(1);
    	base.load(id);
    }

    @Test
    public void testLoad5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ItemImpl.class, anyInt); result= item;
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
    		hibernateTemplate.loadAll(net.malta.model.ItemImpl.class); result= itemList;
    	}};

    	base.loadAll();
    }

    @Test
    public void testCreate1() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(item); result= item;
    	}};

    	Item obj = Item.Factory.newInstance();

    	base.create(obj);
    }

    @Test
    public void testCreate2() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(item); result= item;
    	}};

    	Item obj = null;

    	try {
			base.create(obj);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate3() {

    	base.create(itemList);
    }

    @Test
    public void testCreate4() {

    	try {
    		itemList = null;
			base.create(itemList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testCreate5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(item); result= item;
    	}};

    	String no = "no";
        String name = "name";
        int pricewithtax = 1;
        String size = "size";
        String material = "material";
        String catchcopy = "catchcopy";
        String description = "description";
        String note = "note";
        int stocknum = 1;
        boolean main = true;

    	base.create(no, name, pricewithtax, size, material, catchcopy, description, note, stocknum, main);
    }

    @Test
    public void testCreate6() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.save(item); result= item;
    	}};

    	Product product = Product.Factory.newInstance();
    	String no = "no";
        String name = "name";
        int pricewithtax = 1;
        String size = "size";
        String material = "material";
        String catchcopy = "catchcopy";
        String description = "description";
        String note = "note";
        int stocknum = 1;
        boolean main = true;

    	base.create(catchcopy, description, main, material, name, no, note, pricewithtax, product, size, stocknum);
    }

    @Test
    public void testUpdate1() {

    	base.update(item);
    }

    @Test
    public void testUpdate2() {

    	try {
    		item = null;
			base.update(item);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testUpdate3() {

    	base.update(itemList);
    }

    @Test
    public void testUpdate4() {

    	try {
    		itemList = null;
			base.update(itemList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove1() {

    	base.remove(item);
    }

    @Test
    public void testRemove2() {

    	try {
    		item = null;
			base.remove(item);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove3() {

    	base.remove(itemList);
    }

    @Test
    public void testRemove4() {

    	try {
    		itemList = null;
			base.remove(itemList);
		} catch (Exception e) {
			return;
    	}
    	fail();
    }

    @Test
    public void testRemove5() {

    	new NonStrictExpectations() {{
    		hibernateTemplate.get(net.malta.model.ItemImpl.class, anyInt); result= item;
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
