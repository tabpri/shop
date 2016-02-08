
package net.malta.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import mockit.integration.junit4.JMockit;
import net.malta.web.utils.DeliveryAddressChoises;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class ChoiseTest {

    private Choise choise;

    private Choise choise2;

    private Choise choise3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
    	choise = Choise.Factory.newInstance();

    	List deliveryAddressChoisesList = new ArrayList();
    	DeliveryAddressChoises deliveryAddressChoises = new DeliveryAddressChoises();
    	deliveryAddressChoisesList.add(deliveryAddressChoises);

    	choise.setId(new Integer(1));
    	choise.setVarietychoise("varietychoise");
    	choise.setCarriage(0);
    	choise.setImg("img");
    	choise.setName("name");
    	choise.setOrdernum(0);
    	choise.setPricewithtax(0);
    	choise.setWrapping(true);
    	choise.setWp_posts_id(0);
    	choise.setPurchase(Purchase.Factory.newInstance());
    	choise.setItem(Item.Factory.newInstance());
    	choise.setDeliveryAddressChoises(deliveryAddressChoisesList);

    	choise.getId();
    	choise.getVarietychoise();
    	choise.getCarriage();
    	choise.getImg();
    	choise.getName();
    	choise.getOrdernum();
    	choise.getPricewithtax();
    	choise.isWrapping();
    	choise.getWp_posts_id();
    	choise.getPurchase();
    	choise.getItem();
    	choise.getDeliveryAddressChoises();

    	choise2 = Choise.Factory.newInstance();
    	choise2.setId(new Integer(2));

    	choise3 = Choise.Factory.newInstance();
    	choise3.setId(new Integer(1));

    	assertTrue(choise.equals(choise));
    	assertFalse(choise.equals(Item.Factory.newInstance()));
    	assertFalse(choise.equals(choise2));
    	assertTrue(choise.equals(choise3));

    	choise.hashCode();
    	Choise.Factory.newInstance().hashCode();

    	Choise.Factory.newInstance(0, 0, true, Purchase.Factory.newInstance(), Item.Factory.newInstance());
    	Choise.Factory.newInstance(0, 0, true, Purchase.Factory.newInstance(), Item.Factory.newInstance(), deliveryAddressChoisesList);

    }

    @After
    public void checkExpectations() {
    }
}
