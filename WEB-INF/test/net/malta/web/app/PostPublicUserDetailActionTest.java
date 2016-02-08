
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
import net.malta.beans.PublicUserForm;
import net.malta.model.Prefecture;
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
public class PostPublicUserDetailActionTest {

    private PostPublicUserDetailAction action;

    private PublicUser publicUser;

    private PublicUserForm publicUserForm;

    private Purchase purchase;

    private Prefecture prefecture;

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
        action = new PostPublicUserDetailAction();

        publicUser = PublicUser.Factory.newInstance();

        publicUserForm = new PublicUserForm();

        purchase = Purchase.Factory.newInstance();

        prefecture = Prefecture.Factory.newInstance();

        prefecture.setId(new Integer(1));

        publicUser.setPrefecture(prefecture);
    }

    @Test
    public void testExecute1() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		criteria.uniqueResult(); result= publicUser;
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
			action.execute(actionMapping, publicUserForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testExecute2() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		criteria.uniqueResult(); result= publicUser;
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
			action.execute(actionMapping, publicUserForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testExecute3() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getAttribute("form"); result= publicUserForm; result= publicUserForm;
    		criteria.uniqueResult(); result= publicUser;
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
			action.execute(actionMapping, publicUserForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testExecute4() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getSession().getAttribute("u"); result= publicUser; result= publicUser;
    		criteria.uniqueResult(); result= publicUser;
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
			action.execute(actionMapping, publicUserForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @After
    public void checkExpectations() {
    }
}
