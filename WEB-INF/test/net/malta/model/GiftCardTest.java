
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
public class GiftCardTest {

    private GiftCard card;

    private GiftCard card2;

    private GiftCard card3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {

    	card = GiftCard.Factory.newInstance();

    	List deliveryAddresseList = new ArrayList();

    	DeliveryAddress deliveryAddress = DeliveryAddress.Factory.newInstance();

    	deliveryAddresseList.add(deliveryAddress);

    	List deliveryAddressChoiseList = new ArrayList();

    	DeliveryAddressChoise deliveryAddressChoise = DeliveryAddressChoise.Factory.newInstance();

    	deliveryAddressChoiseList.add(deliveryAddressChoise);

    	card.setId(new Integer(1));
    	card.setName("name");
    	card.setDeliveryAddresses(deliveryAddresseList);
    	card.setDeliveryAddressChoises(deliveryAddressChoiseList);

    	card.getId();
    	card.getName();
    	card.getDeliveryAddresses();
    	card.getDeliveryAddressChoises();

    	card2 = GiftCard.Factory.newInstance();
    	card2.setId(new Integer(2));

    	card3 = GiftCard.Factory.newInstance();
    	card3.setId(new Integer(1));

    	assertTrue(card.equals(card));
    	assertFalse(card.equals(Item.Factory.newInstance()));
    	assertFalse(card.equals(card2));
    	assertTrue(card.equals(card3));

    	card.hashCode();
    	GiftCard.Factory.newInstance().hashCode();

    	GiftCard.Factory.newInstance("name");
    	GiftCard.Factory.newInstance("name", deliveryAddresseList, deliveryAddressChoiseList);

    }

    @After
    public void checkExpectations() {
    }
}
