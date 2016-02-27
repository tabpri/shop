
package net.malta;

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
public class CriteriaSearchConfigurationTest {

    private CriteriaSearchConfiguration configuration;

    @Before
    public void setUp() {
        configuration = new CriteriaSearchConfiguration();
    }

    @Test
    public void test1() {
    	configuration.isForceEagerLoading();
    	configuration.setForceEagerLoading(false);
    	configuration.setFirstResult(new Integer(1));
    	configuration.getFirstResult();
    	configuration.setFetchSize(new Integer(2));
    	configuration.getFetchSize();
    	configuration.setMaximumResultSize(new Integer(3));
    	configuration.getMaximumResultSize();
    }

    @After
    public void checkExpectations() {
    }
}
