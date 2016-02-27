
package net.malta.web.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
import net.malta.beans.PublicUserForm;
import net.malta.model.Choise;
import net.malta.model.Prefecture;
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
public class PostPublicUserVPActionTest {

    private PostPublicUserVPAction action;

    private PublicUser publicUser;

    private PublicUserForm publicUserForm;

    private Prefecture prefecture;

    private Purchase purchase;

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
        action = new PostPublicUserVPAction();

        publicUser = PublicUser.Factory.newInstance();

        publicUserForm = new PublicUserForm();

        prefecture = Prefecture.Factory.newInstance();

        publicUserForm.setPrefecture(47);

        purchase = Purchase.Factory.newInstance();

        publicUser.setId(new Integer(1));

        List choiseList = new ArrayList();

        for (int i = 0; i < 2; i++) {
        	Choise choise = Choise.Factory.newInstance();
        	choiseList.add(choise);
        }

        purchase.setChoises(choiseList);

    }

    @Test
    public void testExecute1() {

        publicUserForm.setPrefecture(0);

		try {
			action.execute(actionMapping, publicUserForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testExecute2() {

    	publicUserForm.setName("userName");
    	publicUserForm.setKana("userKana");
    	publicUserForm.setMail("user@test.com");
    	publicUserForm.setAddress("useraddress");
    	publicUserForm.setPhone("090-2000-2000");

    	publicUserForm.setId(new Integer(0));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getParameter("zipfour"); result= "1100"; result= "1100";
    		httpServeltRequest.getParameter("fax"); result= "08012341234"; result= "08012341234";
    		httpServeltRequest.getParameter("phone"); result= "08012341234"; result= "08012341234";
    		criteria.uniqueResult(); result= prefecture;
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

		new MockUp<SessionData> () {
			@Mock public void update(PublicUser u, Purchase purchase, HttpServletRequest req, HttpServletResponse res, Session session) {
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

    	publicUserForm.setName("userName");
    	publicUserForm.setKana("userKana");
    	publicUserForm.setMail("user@test.com");
    	publicUserForm.setAddress("useraddress");
    	publicUserForm.setPhone("090-2000-2000");

    	publicUserForm.setId(new Integer(1));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getParameter("id"); result= "1";
    		httpServeltRequest.getParameter("zipfour"); result= "1100"; result= "1100";
    		httpServeltRequest.getParameter("fax"); result= "08012341234"; result= "08012341234";
    		httpServeltRequest.getParameter("phone"); result= "08012341234"; result= "08012341234";
    		criteria.uniqueResult(); result= publicUser; result= prefecture;
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

		new MockUp<SessionData> () {
			@Mock public void update(PublicUser u, Purchase purchase, HttpServletRequest req, HttpServletResponse res, Session session) {
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

    	publicUserForm.setName("userName");
    	publicUserForm.setKana("userKana");
    	publicUserForm.setMail("user@test.com");
    	publicUserForm.setAddress("useraddress");
    	publicUserForm.setPhone("090-2000-2000");

    	publicUserForm.setId(new Integer(1));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getParameter("ajax"); result= "ajax";
    		httpServeltRequest.getParameter("id"); result= "1";
    		httpServeltRequest.getParameter("zipfour"); result= "1100"; result= "1100";
    		httpServeltRequest.getParameter("fax"); result= "08012341234"; result= "08012341234";
    		httpServeltRequest.getParameter("phone"); result= "08012341234"; result= "08012341234";
    		criteria.uniqueResult(); result= publicUser; result= prefecture;
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

		new MockUp<SessionData> () {
			@Mock public void update(PublicUser u, Purchase purchase, HttpServletRequest req, HttpServletResponse res, Session session) {
			}
		};

		try {
			action.execute(actionMapping, publicUserForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testExecute5() {

    	publicUserForm.setName("userName");
    	publicUserForm.setKana("userKana");
    	publicUserForm.setMail("user@test.com");
    	publicUserForm.setAddress("useraddress");
    	publicUserForm.setPhone("090-2000-2000");

    	publicUserForm.setId(new Integer(1));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getParameter("from"); result= "detail"; result= "detail";
    		httpServeltRequest.getParameter("id"); result= "1";
    		httpServeltRequest.getParameter("zipfour"); result= "1100"; result= "1100";
    		httpServeltRequest.getParameter("fax"); result= "08012341234"; result= "08012341234";
    		httpServeltRequest.getParameter("phone"); result= "08012341234"; result= "08012341234";
    		criteria.uniqueResult(); result= publicUser; result= prefecture;
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

		new MockUp<SessionData> () {
			@Mock public void update(PublicUser u, Purchase purchase, HttpServletRequest req, HttpServletResponse res, Session session) {
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
