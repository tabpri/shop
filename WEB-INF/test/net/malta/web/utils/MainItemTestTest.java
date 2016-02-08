
package net.malta.web.utils;

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
public class MainItemTestTest {

    private MainItemTest test;

    @Before
    public void setUp() {
        test = new MainItemTest();
    }

    @Test
    public void test() {
    	// MainItem.java はMainItemTest.javaでテスト実施。
    }

    @After
    public void checkExpectations() {
    }
}
