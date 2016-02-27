
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
public class StaticDataTest {

    private StaticData data;

    private StaticData data2;

    private StaticData data3;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
    	data = StaticData.Factory.newInstance();

    	data.setId(new Integer(1));
    	data.setFromaddress("fromaddress");
    	data.setSitename("sitename");
    	data.setBasepath("basepath");
    	data.setUnix(true);
    	data.setMapeventspan(1);
    	data.setNoimage("noimage".getBytes());
    	data.setCarriage(1);
    	data.setCreditcardprocesurl("creditcardprocesurl");
    	data.setContract_code("contract_code");

    	data.getId();
    	data.getFromaddress();
    	data.getSitename();
    	data.getBasepath();
    	data.isUnix();
    	data.getMapeventspan();
    	data.getNoimage();
    	data.getCarriage();
    	data.getCreditcardprocesurl();
    	data.getContract_code();

    	data2 = StaticData.Factory.newInstance();
    	data2.setId(new Integer(2));

    	data3 = StaticData.Factory.newInstance();
    	data3.setId(new Integer(1));

    	assertTrue(data.equals(data));
    	assertFalse(data.equals(Item.Factory.newInstance()));
    	assertFalse(data.equals(data2));
    	assertTrue(data.equals(data3));

    	data.hashCode();
    	StaticData.Factory.newInstance().hashCode();

    	StaticData.Factory.newInstance("fromdaddress", "sitename", "basepath", true, 1,
    			"noimage".getBytes(), 1, "creditcardprocessurl", "contract_code");

    }

    @After
    public void checkExpectations() {
    }
}
