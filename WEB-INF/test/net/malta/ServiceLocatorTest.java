
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
public class ServiceLocatorTest {

    private ServiceLocator locator;

    private String beanFactoryLocation = "beanRefFactory.xml";

    private String beanFactoryId = "beanRefFactory";

    @Before
    public void setUp() {
    }

    @Test
    public void testInit1() {
    	locator = ServiceLocator.instance();
    	locator.init(beanFactoryLocation);
    }

    @Test
    public void testInit2() {
    	locator = ServiceLocator.instance();
    	locator.init(beanFactoryLocation, beanFactoryId);
    }

    @Test
    public void testGetContext1() {
    	locator = ServiceLocator.instance();
    	locator.init(beanFactoryLocation);

    	locator.getContext();

    	locator.shutdown();
    }

    @Test
    public void testGetContext2() {
    	locator = ServiceLocator.instance();
    	locator.init(null);

    	locator.getContext();

    	locator.shutdown();
    }

    @Test
    public void testGetService() {
    	locator = ServiceLocator.instance();
    	locator.init(null);

    	try {
			locator.getDbFileManageableService();
			locator.getAttachmentManageableService();
			locator.getPublicUserManageableService();
			locator.getItemManageableService();
			locator.getPurchaseManageableService();
			locator.getProductManageableService();
			locator.getChoiseManageableService();
			locator.getCategoryManageableService();
			locator.getDeliveryAddressManageableService();
			locator.getDeliveryAddressChoiseManageableService();
			locator.getGiftCardManageableService();
			locator.getStaticDataManageableService();
			locator.getCarriageManageableService();
			locator.getPaymentMethodManageableService();
			locator.getPrefectureManageableService();

			locator.getService("DbFileManageableService");
		} catch (Exception e) {
			fail(e.getMessage());
		}

    	locator.shutdown();
    }

    @After
    public void checkExpectations() {
    }
}
