
package net.malta.model;

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
public class CarriageImplTest {

    private CarriageImpl impl;

    @Before
    public void setUp() {
    }

    @Test
    public void test() {
    	// 実装なし
    	impl = new CarriageImpl();
    }

    @After
    public void checkExpectations() {
    }
}
