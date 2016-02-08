
package net.malta.web.app;

import static org.junit.Assert.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import net.enclosing.util.HibernateSession;
import net.enclosing.util.StringFullfiller;
import net.malta.beans.GiftCardForm;
import net.malta.model.GiftCard;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.hibernate.Criteria;
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
public class PostGiftCardVPActionTest {

    private PostGiftCardVPAction action;

    private GiftCard giftCard;

    private GiftCardForm giftCardForm;

    @Mocked
    private ActionMapping actionMapping;

    @Mocked
    private HttpServletRequest httpServeltRequest;

    @Mocked
    private HttpServletResponse httpServletResponse;

    @Mocked
    private ServletContext servletContext;

    @Mocked
    private Session hibernateSession;

    @Mocked
    private Criteria criteria;

    @Mocked
    private Transaction transaction;

    @Before
    public void setUp() {
        action = new PostGiftCardVPAction();

        giftCard = GiftCard.Factory.newInstance();

        giftCard.setId(new Integer(1));

        giftCardForm = new GiftCardForm();

    }

    @Test
    public void testExecute1() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		httpServeltRequest.getParameter("ajax"); result= "ajax";
    		criteria.uniqueResult(); result= giftCard;
    	}};

    	new MockUp<Action> () {
			@Mock public ActionServlet getServlet() {
				return new ActionServlet();
			}
		};
		new MockUp<ActionServlet> () {
			@Mock public ServletContext getServletContext() {
				return servletContext;
			}
		};

    	new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

		new MockUp<StringFullfiller> () {
    		@Mock public void fullfil(Object object) {
    		}
		};

		try {
			action.execute(actionMapping, giftCardForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute2() {

    	giftCardForm.setId(new Integer(0));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		httpServeltRequest.getParameter("ajax"); result= "ajax";
    		criteria.uniqueResult(); result= giftCard;
    	}};

    	new MockUp<Action> () {
			@Mock public ActionServlet getServlet() {
				return new ActionServlet();
			}
		};
		new MockUp<ActionServlet> () {
			@Mock public ServletContext getServletContext() {
				return servletContext;
			}
		};

    	new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

		new MockUp<StringFullfiller> () {
    		@Mock public void fullfil(Object object) {
    		}
		};

		try {
			action.execute(actionMapping, giftCardForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute3() {

    	giftCardForm.setId(new Integer(1));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		criteria.uniqueResult(); result= giftCard;
    	}};

    	new MockUp<Action> () {
			@Mock public ActionServlet getServlet() {
				return new ActionServlet();
			}
		};
		new MockUp<ActionServlet> () {
			@Mock public ServletContext getServletContext() {
				return servletContext;
			}
		};

    	new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

		new MockUp<StringFullfiller> () {
    		@Mock public void fullfil(Object object) {
    		}
		};

		try {
			action.execute(actionMapping, giftCardForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @After
    public void checkExpectations() {
    }
}
