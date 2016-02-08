
package net.malta.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
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
public class ProductTest {

    private Product product;

    private Product product2;

    private Product product3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
    	product = Product.Factory.newInstance();

    	List itemList = new ArrayList();
    	Item item = Item.Factory.newInstance();
    	itemList.add(item);

    	product.setId(new Integer(1));
    	product.setNo("no");
    	product.setName("name");
    	product.setDate(new Date());
    	product.setPub(true);
    	product.setPricewithtax(100);
    	product.setSize("10");
    	product.setMaterial("material");
    	product.setCatchcopy("catchcopy");
    	product.setDescription("description");
    	product.setNote("note");
    	product.setStocknum(1);
    	product.setRemoved(false);
    	product.setMainitemname("mainitemname");
    	product.setEnglish("english");
    	product.setItems(itemList);
    	product.setCategory(Category.Factory.newInstance());
    	product.setThumnail(Attachment.Factory.newInstance());
    	product.setSlideshow(Attachment.Factory.newInstance());

    	product.getId();
    	product.getNo();
    	product.getName();
    	product.getDate();
    	product.isPub();
    	product.getPricewithtax();
    	product.getSize();
    	product.getMaterial();
    	product.getCatchcopy();
    	product.getDescription();
    	product.getNo();
    	product.getStocknum();
    	product.isRemoved();
    	product.getMainitemname();
    	product.getEnglish();
    	product.getItems();
    	product.getCategory();
    	product.getThumnail();
    	product.getSlideshow();

    	product2 = Product.Factory.newInstance();
    	product2.setId(new Integer(2));

    	product3 = Product.Factory.newInstance();
    	product3.setId(new Integer(1));

    	assertTrue(product.equals(product));
    	assertFalse(product.equals(Item.Factory.newInstance()));
    	assertFalse(product.equals(product2));
    	assertTrue(product.equals(product3));

    	product.hashCode();
    	Product.Factory.newInstance().hashCode();

    	Product.Factory.newInstance("no", "name", new Date(), true, 100, "size", "material", "catchcopy",
    			"description", "note", 3, false, "mainitemname", "english");
    	Product.Factory.newInstance("no", "name", new Date(), true, 100, "size", "material", "catchcopy",
    			"description", "note", 3, false, "mainitemname", "english", itemList, Category.Factory.newInstance(),
    			Attachment.Factory.newInstance(), Attachment.Factory.newInstance());
    }

    @After
    public void checkExpectations() {
    }
}
