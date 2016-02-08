
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
public class CarriageTest {

    private Carriage carriage;

    private Carriage carriage2;

    private Carriage carriage3;

    @Before
    public void setUp() {

    }

    @Test
    public void test() {

    	carriage = Carriage.Factory.newInstance();

    	List items = new ArrayList();
    	Item item = Item.Factory.newInstance();
    	items.add(item);

    	carriage.setId(new Integer(1));
    	carriage.setName("name");
    	carriage.setValue(0);
    	carriage.setItems(items);

    	carriage.getId();
    	carriage.getName();
    	carriage.getValue();
    	carriage.getItems();

    	carriage2 = Carriage.Factory.newInstance();
    	carriage2.setId(new Integer(2));

    	carriage3 = Carriage.Factory.newInstance();
    	carriage3.setId(new Integer(1));

    	assertTrue(carriage.equals(carriage));
    	assertFalse(carriage.equals(Item.Factory.newInstance()));
    	assertFalse(carriage.equals(carriage2));
    	assertTrue(carriage.equals(carriage3));

    	carriage.hashCode();
    	Carriage.Factory.newInstance().hashCode();

    	Carriage.Factory.newInstance("name", 0);
    	Carriage.Factory.newInstance("name", 0, items);

    }

    @After
    public void checkExpectations() {
    }
}
