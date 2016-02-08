
package net.malta.web.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import net.malta.model.PublicUser;
import net.malta.model.Purchase;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class SessionDataTest {

    private SessionData data;

    @Mocked
	private Session session;

    @Mocked
    private Transaction transaction;

    @Mocked
    private HttpServletRequest httpServletRequest;

    @Mocked
    private HttpServletResponse httpServletResponse;

    @Mocked
    private Cookie cookie;

    @Before
    public void setUp() {
        data = new SessionData();
    }

    @Test
    public void testUpdate1() {

    	PublicUser publicUser = PublicUser.Factory.newInstance();

    	publicUser.setId(new Integer(1));

    	Purchase purchase = Purchase.Factory.newInstance();

    	SessionData.update(publicUser, purchase, httpServletRequest, httpServletResponse, session);

    }

    @Test
    public void testUpdate2() throws Exception {

    	new NonStrictExpectations() {{
    		httpServletResponse.flushBuffer(); result= new Exception("test");
    	}};

    	PublicUser publicUser = PublicUser.Factory.newInstance();

    	publicUser.setId(new Integer(1));

    	Purchase purchase = Purchase.Factory.newInstance();

    	SessionData.update(publicUser, purchase, httpServletRequest, httpServletResponse, session);

    }

    @After
    public void checkExpectations() {
    }
}
