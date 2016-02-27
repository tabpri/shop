
package net.malta.web.utils;

import static org.junit.Assert.*;

import javax.servlet.ServletContext;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import net.enclosing.util.HibernateSession;
import net.malta.model.Product;

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
public class MainItemTest {

    private MainItem item;

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
        item = new MainItem();
    }

    @Test
    public void testOf1() {

		Product product = Product.Factory.newInstance();

		assertNull(MainItem.Of(product, servletContext));
    }

    @Test
    public void testOf2() {
    	new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

		Product product = Product.Factory.newInstance();

		product.setId(new Integer(1));

		MainItem.Of(product, servletContext);
    }

    @Test
    public void testOf3() {

		Product product = Product.Factory.newInstance();

		MainItem.Of(product, session);
    }

    @Test
    public void testOf4() {
    	new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

		Product product = Product.Factory.newInstance();

		product.setId(new Integer(1));

		MainItem.Of(product, session);
    }

    @After
    public void checkExpectations() {
    }
}
