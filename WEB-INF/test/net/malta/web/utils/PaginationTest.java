
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
public class PaginationTest {

    private Pagination pagination;

    @Before
    public void setUp() {
        pagination = new Pagination();
    }

    @Test
    public void testGetMax() {
    	pagination.getMax(10, 3);
    }

    @After
    public void checkExpectations() {
    }
}
