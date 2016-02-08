
package net.malta.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import mockit.integration.junit4.JMockit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class ItemTest {

    private Item item;

    private Item item2;

    private Item item3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
    	item = Item.Factory.newInstance();

    	List choiseList = new ArrayList();
    	Choise choise = Choise.Factory.newInstance();
    	choiseList.add(choise);

    	item.setId(new Integer(1));
    	item.setNo("no");
    	item.setName("name");
    	item.setPricewithtax(0);
    	item.setSize("size");
    	item.setMaterial("material");
    	item.setCatchcopy("catchcopy");
    	item.setDescription("description");
    	item.setNote("note");
    	item.setStocknum(1);
    	item.setMain(true);
    	item.setProduct(Product.Factory.newInstance());
    	item.setChoises(choiseList);
    	item.setCarriage(Carriage.Factory.newInstance());
    	item.setZoom(Attachment.Factory.newInstance());
    	item.setDetailed(new ArrayList());
    	item.setDefaultattachment(Attachment.Factory.newInstance());

    	item.getId();
    	item.getNo();
    	item.getName();
    	item.getPricewithtax();
    	item.getSize();
    	item.getMaterial();
    	item.getCatchcopy();
    	item.getDescription();
    	item.getNote();
    	item.getStocknum();
    	item.isMain();
    	item.getProduct();
    	item.getChoises();
    	item.getCarriage();
    	item.getZoom();
    	item.getDetailed();
    	item.getDefaultattachment();

    	item2 = Item.Factory.newInstance();
    	item2.setId(new Integer(2));

    	item3 = Item.Factory.newInstance();
    	item3.setId(new Integer(1));

    	assertTrue(item.equals(item));
    	assertFalse(item.equals(Product.Factory.newInstance()));
    	assertFalse(item.equals(item2));
    	assertTrue(item.equals(item3));

    	item.hashCode();
    	Item.Factory.newInstance().hashCode();

    	Item.Factory.newInstance("no", "name", 1, "size", "material",
    			"catchcopy", "description", "note", 1, true, Product.Factory.newInstance());
    	Item.Factory.newInstance("no", "name", 1, "size", "material",
    			"catchcopy", "description", "note", 1, true, Product.Factory.newInstance(), choiseList,
    			Carriage.Factory.newInstance(), Attachment.Factory.newInstance(), new ArrayList(),
    			Attachment.Factory.newInstance());

    }

    @After
    public void checkExpectations() {
    }
}
