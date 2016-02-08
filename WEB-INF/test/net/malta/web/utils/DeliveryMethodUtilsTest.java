
package net.malta.web.utils;

import javax.servlet.http.HttpServletRequest;

import mockit.Mocked;
import mockit.NonStrictExpectations;
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
public class DeliveryMethodUtilsTest {

    private DeliveryMethodUtils utils;

    @Mocked
    private HttpServletRequest httpServletRequest;

    @Before
    public void setUp() {
        utils = new DeliveryMethodUtils();
    }

    @Test
    public void testSetIntoSesssion() {

    	new NonStrictExpectations() {{
    		httpServletRequest.getParameter("deliverymethod"); result= "1"; result= "1";
    	}};

    	DeliveryMethodUtils.setIntoSesssion(httpServletRequest);

    }

    @After
    public void checkExpectations() {
    }
}
