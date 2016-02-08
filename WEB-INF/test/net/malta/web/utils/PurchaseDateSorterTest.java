
package net.malta.web.utils;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletContext;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import net.enclosing.util.HibernateSession;
import net.malta.model.PublicUser;
import net.malta.model.Purchase;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class PurchaseDateSorterTest {

    private PurchaseDateSorter sorter;

    @Mocked
   	private Session session;

    @Mocked
    private Criteria criteria;

   	@Mocked
   	private ServletContext servletContext;

   	@Mocked
    private Session hibernateSession;

    @Before
    public void setUp() {
        sorter = new PurchaseDateSorter();
    }

    @Test
    public void testDateSorter1() {

    	PublicUser publicUser = PublicUser.Factory.newInstance();

    	final Purchase purchase = Purchase.Factory.newInstance();

    	purchase.setDate(new Date());

    	new NonStrictExpectations() {{
    		criteria.list().size(); result= 1;
    		criteria.uniqueResult(); result= purchase;
    	}};

    	new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

    	try {
			PurchaseDateSorter.dateSorter(publicUser, servletContext);
		} catch (ParseException e) {
			fail();
		}

    }

    @Test
    public void testDateSorter2() {

    	PublicUser publicUser = PublicUser.Factory.newInstance();

    	final Purchase purchase = Purchase.Factory.newInstance();

    	purchase.setDate(new Date());

    	new NonStrictExpectations() {{
    		criteria.list().size(); result= 1;
    		criteria.uniqueResult(); result= purchase;
    	}};

    	new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

    	try {
			PurchaseDateSorter.dateSorter(publicUser, hibernateSession);
		} catch (ParseException e) {
			fail();
		}

    }

    @After
    public void checkExpectations() {
    }
}
