
package net.malta.web.utils;

import java.util.ArrayList;
import java.util.List;

import mockit.integration.junit4.JMockit;
import net.malta.model.Choise;
import net.malta.model.DeliveryAddressChoise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class ChoisesTest {

    private Choises choises;

    @Before
    public void setUp() {
        choises = new Choises();
    }

    @Test
    public void testRemaining1() {

    	Choise choise = Choise.Factory.newInstance();

    	List deliveryAddressChoisesList = new ArrayList();

    	for (int i =0; i < 3; i++) {
    		DeliveryAddressChoise deliveryAddressChoise = DeliveryAddressChoise.Factory.newInstance();
    		deliveryAddressChoise.setOrdernum(10 + i);
    		deliveryAddressChoisesList.add(deliveryAddressChoise);
    	}

    	choise.setDeliveryAddressChoises(deliveryAddressChoisesList);
    	choises.remaining(choise);

    }

    @After
    public void checkExpectations() {
    }
}
