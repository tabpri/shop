
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
import net.malta.beans.ChoiseForm;
import net.malta.model.Choise;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class UpdateChoiseForOrdernumActionTest {

    private UpdateChoiseForOrdernumAction action;

    private Choise choise;

    private ChoiseForm choiseForm;

    @Mocked
    private ActionMapping actionMapping;

    @Mocked
    private ActionForm actionForm;

    @Mocked
    private HttpServletRequest httpServeltRequest;

    @Mocked
    private HttpServletResponse httpServletResponse;

    @Mocked
    private ServletContext servletContext;

    @Mocked
    private SessionFactory sessionFactory;

    @Mocked
    private Session hibernateSession;

    @Mocked
    private Transaction transaction;

    @Mocked
    private Criteria criteria;

    @Before
    public void setUp() {
    	choise = Choise.Factory.newInstance();
    	choiseForm = new ChoiseForm();

    	choiseForm.setId(new Integer(1));
    }

    @Test
    public void testExecute1() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		httpServeltRequest.getParameter("ordernum"); result= "1"; result= "1"; result= "1";
    		criteria.uniqueResult(); result= choise;
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

		action = new UpdateChoiseForOrdernumAction();

		try {
			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute2() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		httpServeltRequest.getParameter("ordernum"); result= "1"; result= "1"; result= "1";
    		httpServeltRequest.getAttribute("form"); result= choiseForm;
    		criteria.uniqueResult(); result= choise;
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

		action = new UpdateChoiseForOrdernumAction();

		try {
			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute3() {

    	new NonStrictExpectations() {{
    		criteria.uniqueResult(); result= choise;
    	}};

    	new MockUp<ClassPathXmlApplicationContext> () {
    		@Mock public void $init(String[] configLocations) {
    			this.getMockInstance();
    		}
		};

		new MockUp<ClassPathXmlApplicationContext> () {
    		@Mock public Object getBean(String arg0) throws BeansException {
    			return sessionFactory;
    		}
		};

		try {
			UpdateChoiseForOrdernumAction.main(null);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute4() {

    	new NonStrictExpectations() {{
    		criteria.uniqueResult(); result= choise;
    	}};

    	new MockUp<ClassPathXmlApplicationContext> () {
    		@Mock public void $init(String[] configLocations) {
    			this.getMockInstance();
    		}
		};

		try {
			UpdateChoiseForOrdernumAction.main(null);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @After
    public void checkExpectations() {
    }
}
