
package net.malta.model;

import static org.junit.Assert.*;
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
public class DeliveryAddressChoiseTest {

    private DeliveryAddressChoise choise;

    private DeliveryAddressChoise choise2;

    private DeliveryAddressChoise choise3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
        choise = DeliveryAddressChoise.Factory.newInstance();

        choise.setId(new Integer(1));
        choise.setOrdernum(0);
        choise.setPreferreddate("preferreddate");
        choise.setPreferredtime("preferredtime");
        choise.setDeliveryAddress(DeliveryAddress.Factory.newInstance());
        choise.setChoise(Choise.Factory.newInstance());
        choise.setGiftCard(GiftCard.Factory.newInstance());

        choise.getId();
        choise.getOrdernum();
        choise.getPreferreddate();
        choise.getPreferredtime();
        choise.getDeliveryAddress();
        choise.getChoise();
        choise.getGiftCard();

        choise2 = DeliveryAddressChoise.Factory.newInstance();
    	choise2.setId(new Integer(2));

    	choise3 = DeliveryAddressChoise.Factory.newInstance();
    	choise3.setId(new Integer(1));

    	assertTrue(choise.equals(choise));
    	assertFalse(choise.equals(Item.Factory.newInstance()));
    	assertFalse(choise.equals(choise2));
    	assertTrue(choise.equals(choise3));

    	choise.hashCode();
    	DeliveryAddressChoise.Factory.newInstance().hashCode();

    	DeliveryAddressChoise.Factory.newInstance(0, "prefeffeddate", "preferredtime",
    			DeliveryAddress.Factory.newInstance(), Choise.Factory.newInstance());
    	DeliveryAddressChoise.Factory.newInstance(0, "prefeffeddate", "preferredtime",
    			DeliveryAddress.Factory.newInstance(), Choise.Factory.newInstance(), GiftCard.Factory.newInstance());

    }

    @After
    public void checkExpectations() {
    }
}
