
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
public class CategoryTest {

    private Category category;

    private Category category2;

    private Category category3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
        category = Category.Factory.newInstance();

        List productList = new ArrayList();
        Product product = Product.Factory.newInstance();
        productList.add(product);

        category.setId(new Integer(1));
        category.setName("name");
        category.setProducts(productList);

        category.getId();
        category.getName();
        category.getProducts();

        category2 = Category.Factory.newInstance();
        category2.setId(new Integer(2));

        category3 = Category.Factory.newInstance();
        category3.setId(new Integer(1));

    	assertTrue(category.equals(category));
    	assertFalse(category.equals(Item.Factory.newInstance()));
    	assertFalse(category.equals(category2));
    	assertTrue(category.equals(category3));

    	category.hashCode();
    	Category.Factory.newInstance().hashCode();

    	Category.Factory.newInstance("name");
    	Category.Factory.newInstance("name", productList);

    }

    @After
    public void checkExpectations() {
    }
}
