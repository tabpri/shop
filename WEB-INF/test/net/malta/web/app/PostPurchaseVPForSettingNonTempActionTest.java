
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
import net.malta.beans.PurchaseForm;
import net.malta.model.PublicUser;
import net.malta.model.Purchase;
import net.malta.web.utils.SessionData;

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
public class PostPurchaseVPForSettingNonTempActionTest {

    private PostPurchaseVPForSettingNonTempAction action;

    private Purchase purchase;

    private PurchaseForm purchaseForm;

    private PublicUser publicUser;

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
    private Transaction transaction;

    @Mocked
    private Criteria criteria;

    @Before
    public void setUp() {
        action = new PostPurchaseVPForSettingNonTempAction();

        purchase = Purchase.Factory.newInstance();

        purchase.setId(new Integer(1));

        purchaseForm = new PurchaseForm();

        publicUser = PublicUser.Factory.newInstance();
    }

    @Test
    public void testExecute1() {

    	purchaseForm.setId(new Integer(0));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result = purchase;
    		criteria.uniqueResult(); result= purchase;
    		httpServeltRequest.getParameter("ajax"); result= "ajax";
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
    		@Mock public Session currentSession(ServletContext servletContext, int p) {
    			return hibernateSession;
    		}
		};

		try {
			action.execute(actionMapping, purchaseForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute2() {

    	purchaseForm.setId(new Integer(0));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result = purchase;
    		criteria.uniqueResult(); result= purchase;
    		httpServeltRequest.getParameter("from"); result= "from"; result= "from";
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
    		@Mock public Session currentSession(ServletContext servletContext, int p) {
    			return hibernateSession;
    		}
		};

		try {
			action.execute(actionMapping, purchaseForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute3() {

    	purchaseForm.setId(new Integer(0));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result = purchase;
    		httpServeltRequest.getSession().getAttribute("u"); result = publicUser; result = publicUser;
    		criteria.uniqueResult(); result= purchase;
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
    		@Mock public Session currentSession(ServletContext servletContext, int p) {
    			return hibernateSession;
    		}
		};

		new MockUp<MailAboutPurchaseToPublicUserAction> () {
    		@Mock public void execute(Integer id, Session session, int deliverymethod) {
    		}
		};

		new MockUp<StringFullfiller> () {
    		@Mock public void fullfil(Object obj) {
    		}
		};

		new MockUp<SessionData> () {
    		@Mock public void update(PublicUser u, Purchase purchase, HttpServletRequest req, HttpServletResponse res, Session session) {
    		}
		};

		try {
			action.execute(actionMapping, purchaseForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @After
    public void checkExpectations() {
    }
}
