
package net.malta;

import mockit.integration.junit4.JMockit;
import net.malta.model.PurchaseImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class CriteriaSearchPropertiesTest {

    private CriteriaSearchProperties properties;

    @Before
    public void setUp() {

    	properties = new CriteriaSearchProperties();
    }

    @Test
    public void testGetEmbeddedValues() {

    	CriteriaSearchProperties.getEmbeddedValues(PurchaseImpl.class);
    }

    @Test
    public void testGgetNavigableAssociationEndType1() {

    	CriteriaSearchProperties.getNavigableAssociationEndType(PurchaseImpl.class, "choises");

    	System.out.println("test exist[PurchaseImpl, choises]=" + CriteriaSearchProperties.getNavigableAssociationEndType(PurchaseImpl.class, "choises"));
    }

    @Test
    public void testGgetNavigableAssociationEndType2() {

    	CriteriaSearchProperties.getNavigableAssociationEndType(PurchaseImpl.class, "test");
    	System.out.println("test not exist[PurchaseImpl, test]=" + CriteriaSearchProperties.getNavigableAssociationEndType(PurchaseImpl.class, "test"));
    }

    @After
    public void checkExpectations() {
    }
}
