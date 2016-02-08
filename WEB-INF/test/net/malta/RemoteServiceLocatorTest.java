
package net.malta;

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
public class RemoteServiceLocatorTest {

    private RemoteServiceLocator locator;

    private String beanFactoryLocation = "beanRefFactory.xml";

    @Before
    public void setUp() {
    }

    @Test
    public void testInit() {
    	locator = RemoteServiceLocator.instance();
    	locator.init(beanFactoryLocation);
    }

    @Test
    public void testGetContext1() {
    	locator = RemoteServiceLocator.instance();
    	locator.init(beanFactoryLocation);

    	locator.getContext();
    }

    @Test
    public void testGetContext2() {
    	locator = RemoteServiceLocator.instance();
    	locator.init(null);

    	locator.getContext();
    }

    @Test
    public void testGetService() {
    	locator = RemoteServiceLocator.instance();
    	locator.init(null);

    	try {
			locator.getService("DbFileManageableService");
//			locator.getService("AttachmentManageableService");
//			locator.getService("PublicUserManageableService");
//			locator.getService("ItemManageableService");
//			locator.getService("PurchaseManageableService");
//			locator.getService("ProductManageableService");
//			locator.getService("ChoiseManageableService");
//			locator.getService("CategoryManageableService");
//			locator.getService("DeliveryAddressManageableService");
//			locator.getService("DeliveryAddressChoiseManageableService");
//			locator.getService("GiftCardManageableService");
//			locator.getService("StaticDataManageableService");
//			locator.getService("CarriageManageableService");
//			locator.getService("PaymentMethodManageableService");
//			locator.getService("PrefectureManageableService");
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @After
    public void checkExpectations() {
    }
}
