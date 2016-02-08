
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
public class ManageableServiceLocatorTest {

    private ManageableServiceLocator locator;

    private String beanFactoryLocation = "beanRefFactory.xml";

    @Before
    public void setUp() {
//    	単体テストの観点から外れますが
//    	ローカル環境でテストするには、beanRefFactory.xmlの
//    	データソースの設定をローカル用に書き換えが必要です。
    }

    @Test
    public void testInstance() {

    }

    @Test
    public void testInit() {
    	locator = ManageableServiceLocator.instance();
    	locator.init(beanFactoryLocation);
    }

    @Test
    public void testGetContext1() {
    	locator = ManageableServiceLocator.instance();
    	locator.init(beanFactoryLocation);

    	locator.getContext();

    	locator.shutdown();
    }

    @Test
    public void testGetContext2() {
    	locator = ManageableServiceLocator.instance();
    	locator.init(null);

    	locator.getContext();

    	locator.shutdown();
    }

    @Test
    public void testGetService() {
    	locator = ManageableServiceLocator.instance();
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
		} catch (Exception e) {
			fail(e.getMessage());
		}

    	locator.shutdown();
    }

    @After
    public void checkExpectations() {
    }
}
