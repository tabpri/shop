
package net.malta.web.utils;

import static org.junit.Assert.*;

import javax.servlet.ServletContext;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import net.enclosing.util.HibernateSession;
import net.malta.model.DeliveryAddress;
import net.malta.model.Purchase;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class DeliveryAddressItemTest {

    private DeliveryAddressItem item;

    @Mocked
	private Session session;

	@Mocked
	private ServletContext servletContext;

	@Mocked
    private Session hibernateSession;

    @Before
    public void setUp() {
        item = new DeliveryAddressItem();
    }

    @Test
    public void testOf1() {

    	new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

		Purchase purchase = Purchase.Factory.newInstance();

		DeliveryAddress deliveryAddress = DeliveryAddress.Factory.newInstance();

		assertNotNull(DeliveryAddressItem.thisdeliveryAddressItem(deliveryAddress, purchase, servletContext));

    }

    @After
    public void checkExpectations() {
    }
}
