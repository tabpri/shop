
package net.malta;

import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.hibernate.criterion.MatchMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class CriteriaSearchParameterTest {

    private CriteriaSearchParameter parameter;

    @Mocked
    private MatchMode matchMode;

    @Before
    public void setUp() {
    }

    @Test
    public void test1() {
    	parameter = new CriteriaSearchParameter(new Object(), "String");
    	parameter = new CriteriaSearchParameter("String", "String");
    	parameter = new CriteriaSearchParameter(new String[] {"String","String"}, "String/String");
    	parameter = new CriteriaSearchParameter(new String[] {"String","String"}, "String/String");
    	parameter = new CriteriaSearchParameter(new Object(), "String", 1);
    	parameter = new CriteriaSearchParameter(new Object(), "String", true);
    	parameter = new CriteriaSearchParameter("String", "String", true);
    	parameter = new CriteriaSearchParameter(new String[] {"String","String"}, "String/String", true);
    	parameter = new CriteriaSearchParameter("String", "String", true);
    	parameter = new CriteriaSearchParameter(new Object(), "String", true, 1);
    	parameter = new CriteriaSearchParameter(new Object(), "String", true, matchMode);
    	parameter = new CriteriaSearchParameter("String", "String", true, matchMode);
    	parameter = new CriteriaSearchParameter(new String[] {"String","String"}, "String", true, matchMode);
    	parameter = new CriteriaSearchParameter("String", "String", true, 1, matchMode);
    	parameter = new CriteriaSearchParameter("String", "String", 1, matchMode);
    	parameter = new CriteriaSearchParameter(new Object(), "String", matchMode);
    	parameter = new CriteriaSearchParameter("String", "String", matchMode);
    	parameter = new CriteriaSearchParameter(new String[] {"String","String"}, "String", matchMode);

    	parameter.getComparatorID();
    	parameter.setComparatorID(1);
    	parameter.getParameterPattern();
    	parameter.setParameterPattern("String");
    	parameter.getParameterName();
    	parameter.getParameterValue();
    	parameter.setParameterValue(new Object());
    	parameter.isSearchIfIsNull();
    	parameter.setSearchIfIsNull(false);
    	parameter.getMatchMode();
    	parameter.setMatchMode(matchMode);
    	parameter.getOrderDirection();
    	parameter.setOrderDirection(0);
    	parameter.getOrderRelevance();
    	parameter.setOrderRelevance(0);

    }

    @After
    public void checkExpectations() {
    }
}
