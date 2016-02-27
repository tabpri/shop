
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
public class PrefectureTest {

    private Prefecture prefecture;

    private Prefecture prefecture2;

    private Prefecture prefecture3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
    	prefecture = Prefecture.Factory.newInstance();

    	List publicUserList = new ArrayList();
    	PublicUser publicUser = PublicUser.Factory.newInstance();
    	publicUserList.add(publicUser);

    	List deliveryAddressList = new ArrayList();
    	DeliveryAddress deliveryAddress = DeliveryAddress.Factory.newInstance();
    	deliveryAddressList.add(deliveryAddress);

    	prefecture.setId(new Integer(1));
    	prefecture.setName("name");
    	prefecture.setPublicUsers(publicUserList);
    	prefecture.setDeliveryAddresses(deliveryAddressList);

    	prefecture.getId();
    	prefecture.getName();
    	prefecture.getPublicUsers();
    	prefecture.getDeliveryAddresses();

    	prefecture2 = Prefecture.Factory.newInstance();
    	prefecture2.setId(new Integer(2));

    	prefecture3 = Prefecture.Factory.newInstance();
    	prefecture3.setId(new Integer(1));

    	assertTrue(prefecture.equals(prefecture));
    	assertFalse(prefecture.equals(Item.Factory.newInstance()));
    	assertFalse(prefecture.equals(prefecture2));
    	assertTrue(prefecture.equals(prefecture3));

    	prefecture.hashCode();
    	Prefecture.Factory.newInstance().hashCode();

    	Prefecture.Factory.newInstance("name", publicUserList, deliveryAddressList);
    	Prefecture.Factory.newInstance("name");

    }

    @After
    public void checkExpectations() {
    }
}
