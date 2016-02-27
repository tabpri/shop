
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
public class DeliveryAddressTest {

    private DeliveryAddress address;

    private DeliveryAddress address2;

    private DeliveryAddress address3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
    	address = DeliveryAddress.Factory.newInstance();

    	List deliveryAddrList = new ArrayList();
    	DeliveryAddressChoise addressChoise = DeliveryAddressChoise.Factory.newInstance();
    	deliveryAddrList.add(addressChoise);

    	address.setId(new Integer(1));
    	address.setName("name");
    	address.setKana("kana");
    	address.setZipthree(111);
    	address.setZipfour(1111);
    	address.setPhone("09809090909");
    	address.setAddress("address");
    	address.setBuildingname("builingname");
    	address.setPref("pref");
    	address.setPreferreddate("preferreddate");
    	address.setPreferredtime("preferredtime");
    	address.setHasgiftcard(true);
    	address.setDeliveryAddressChoises(deliveryAddrList);
    	address.setPublicUser(PublicUser.Factory.newInstance());
    	address.setGiftCard(GiftCard.Factory.newInstance());
    	address.setPrefecture(Prefecture.Factory.newInstance());

    	address.getId();
    	address.getName();
    	address.getKana();
    	address.getZipthree();
    	address.getZipfour();
    	address.getPhone();
    	address.getAddress();
    	address.getBuildingname();
    	address.getPref();
    	address.getPreferreddate();
    	address.getPreferredtime();
    	address.isHasgiftcard();
    	address.getDeliveryAddressChoises();
    	address.getPublicUser();
    	address.getGiftCard();
    	address.getPrefecture();

    	address2 = DeliveryAddress.Factory.newInstance();
    	address2.setId(new Integer(2));

    	address3 = DeliveryAddress.Factory.newInstance();
    	address3.setId(new Integer(1));

    	assertTrue(address.equals(address));
    	assertFalse(address.equals(Item.Factory.newInstance()));
    	assertFalse(address.equals(address2));
    	assertTrue(address.equals(address3));

    	address.hashCode();
    	DeliveryAddress.Factory.newInstance().hashCode();

    	DeliveryAddress.Factory.newInstance("name", "kana", 111, 2222, "09809090909", "address", "buildingname",
    			"pref", "preferreddate", "preferredtime", true, PublicUser.Factory.newInstance());
    	DeliveryAddress.Factory.newInstance("name", "kana", 111, 2222, "09809090909", "address", "buildingname",
    			"pref", "preferreddate", "preferredtime", true, deliveryAddrList, PublicUser.Factory.newInstance(),
    			GiftCard.Factory.newInstance(), Prefecture.Factory.newInstance());

    }

    @After
    public void checkExpectations() {
    }
}
