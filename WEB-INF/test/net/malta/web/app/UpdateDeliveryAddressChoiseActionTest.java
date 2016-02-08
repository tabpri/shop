
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
import net.malta.beans.DeliveryAddressChoiseForm;
import net.malta.model.Choise;
import net.malta.model.DeliveryAddressChoise;
import net.malta.model.DeliveryAddressImpl;
import net.malta.model.Purchase;

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
public class UpdateDeliveryAddressChoiseActionTest {

    private UpdateDeliveryAddressChoiseAction action;

    private DeliveryAddressChoiseForm deliveryAddressChoiseForm;

    private DeliveryAddressChoise deliveryAddressChoise;

    private Purchase purchase;

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

    	deliveryAddressChoiseForm = new DeliveryAddressChoiseForm();

    	deliveryAddressChoiseForm.setId(new Integer(1));

        deliveryAddressChoise = DeliveryAddressChoise.Factory.newInstance();

        deliveryAddressChoise.setId(new Integer(1));

        purchase = Purchase.Factory.newInstance();

        purchase.setId(new Integer(1));
    }

    @Test
    public void testExecute1() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("deliveryAddress"); result= ""; result= "";
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
	        action = new UpdateDeliveryAddressChoiseAction();

			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute2() {

    	List choiseList = new ArrayList();
    	Choise choise = Choise.Factory.newInstance();
    	choise.setId(new Integer(1));
    	choiseList.add(choise);
    	purchase.setChoises(choiseList);

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("deliveryAddress"); result= "1"; result= "1"; result= "1";
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		httpServeltRequest.getParameter("reset"); result= "reset"; result= "reset";
    		criteria.uniqueResult(); result= deliveryAddressChoise; result= new DeliveryAddressImpl(); result= deliveryAddressChoise; result= deliveryAddressChoise;
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
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
	        action = new UpdateDeliveryAddressChoiseAction();

			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute3() {

    	List choiseList = new ArrayList();
    	Choise choise = Choise.Factory.newInstance();
    	choise.setId(new Integer(1));
    	choiseList.add(choise);
    	purchase.setChoises(choiseList);

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("deliveryAddress"); result= "1"; result= "1"; result= "1";
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		httpServeltRequest.getParameter("1"); result= "1";
    		criteria.uniqueResult(); result= deliveryAddressChoise; result= new DeliveryAddressImpl(); result= deliveryAddressChoise; result= deliveryAddressChoise;
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
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
	        action = new UpdateDeliveryAddressChoiseAction();

			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute4() {

    	List choiseList = new ArrayList();
    	Choise choise = Choise.Factory.newInstance();
    	choise.setId(new Integer(1));
    	choiseList.add(choise);
    	purchase.setChoises(choiseList);

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("deliveryAddress"); result= "1"; result= "1"; result= "1";
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		httpServeltRequest.getAttribute("form"); result= deliveryAddressChoiseForm; result= deliveryAddressChoiseForm;
    		httpServeltRequest.getParameter("1"); result= "1";
    		criteria.uniqueResult(); result= deliveryAddressChoise; result= new DeliveryAddressImpl(); result= null;
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
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
	        action = new UpdateDeliveryAddressChoiseAction();

			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute5() {

    	new NonStrictExpectations() {{
    		criteria.uniqueResult(); result= deliveryAddressChoise;
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

		new MockUp<StringFullfiller> () {
			@Mock public void fullfil(Object object) {
			}
		};

		try {
			UpdateDeliveryAddressChoiseAction.main(null);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute6() {

    	new NonStrictExpectations() {{
    		criteria.uniqueResult(); result= deliveryAddressChoise;
    	}};

    	new MockUp<ClassPathXmlApplicationContext> () {
    		@Mock public void $init(String[] configLocations) {
    			this.getMockInstance();
    		}
		};

		try {
			UpdateDeliveryAddressChoiseAction.main(null);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @After
    public void checkExpectations() {
    }
}
