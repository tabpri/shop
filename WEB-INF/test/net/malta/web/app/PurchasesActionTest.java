
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
import net.malta.beans.PurchaseForm;
import net.malta.model.PublicUser;
import net.malta.model.Purchase;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
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
public class PurchasesActionTest {

    private PurchasesAction action;

    private PublicUser publicUser;

    private Purchase purchase;

    private PurchaseForm purchaseForm;

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

    @Before
    public void setUp() {
        action = new PurchasesAction();

        purchase = Purchase.Factory.newInstance();

        purchaseForm = new PurchaseForm();

        publicUser = PublicUser.Factory.newInstance();
    }

    @Test
    public void testExecute1() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("datestartdate"); result= "2016/01/01"; result= "2016/01/01";
    		httpServeltRequest.getParameter("dateenddate"); result= "2019/01/01"; result= "2019/01/01";
    		httpServeltRequest.getParameter("publicUser"); result= "1"; result= "1";
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		criteria.uniqueResult(); result= publicUser; result= purchase;
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

		try {
			action.execute(actionMapping, purchaseForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute2() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("datestartdate"); result= "2016/01/01"; result= "2016/01/01";
    		httpServeltRequest.getParameter("dateenddate"); result= "2019/01/01"; result= "2019/01/01";
    		httpServeltRequest.getParameter("publicUser"); result= "1"; result= "1";
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		httpServeltRequest.getAttribute("form"); result = purchaseForm; result = purchaseForm;
    		criteria.uniqueResult(); result= publicUser; result= purchase;
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

		try {
			action.execute(actionMapping, purchaseForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute3() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("datestartdate"); result= "2016/01/01"; result= "2016/01/01";
    		httpServeltRequest.getParameter("dateenddate"); result= "2019/01/01"; result= "2019/01/01";
    		httpServeltRequest.getParameter("publicUser"); result= "1"; result= "1";
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		httpServeltRequest.getAttribute("form"); result = purchaseForm; result = purchaseForm;
    		httpServeltRequest.getParameter("displayexport"); result= "displayexport";
    		criteria.uniqueResult(); result= publicUser; result= purchase;
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
