
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
import net.malta.model.Product;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
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
public class ShowProductActionTest {

    private ShowProductAction action;

    private Product product;

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
    private Session hibernateSession;

    @Mocked
    private Criteria criteria;

    private List productList;

    @Before
    public void setUp() {
        action = new ShowProductAction();

        product = Product.Factory.newInstance();
    }

    @Test
    public void testExecute1() {

        product.setId(new Integer(1));

    	productList = new ArrayList();
        for (int i= 0; i < 2; i++) {
        	Product product = Product.Factory.newInstance();
        	product.setId(new Integer(i + 1));
        	productList.add(product);
        }

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		criteria.uniqueResult(); result= product;
    		criteria.list(); result= productList; result= productList; result= productList; result= productList;
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
			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute2() {

    	product.setId(new Integer(2));

    	productList = new ArrayList();

        for (int i= 0; i < 2; i++) {
        	Product product = Product.Factory.newInstance();
        	product.setId(new Integer(i + 1));
        	productList.add(product);
        }

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		criteria.uniqueResult(); result= product;
    		criteria.list(); result= productList; result= productList; result= productList; result= productList;
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
			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute3() {

    	product.setId(new Integer(2));

    	productList = new ArrayList();

        for (int i= 0; i < 3; i++) {
        	Product product = Product.Factory.newInstance();
        	product.setId(new Integer(i + 1));
        	productList.add(product);
        }

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		criteria.uniqueResult(); result= product;
    		criteria.list(); result= productList; result= productList; result= productList; result= productList;
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
			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute4() {

    	product.setId(new Integer(2));

    	productList = new ArrayList();

        for (int i= 0; i < 3; i++) {
        	Product product = Product.Factory.newInstance();
        	product.setId(new Integer(i + 1));
        	productList.add(product);
        }

    	new NonStrictExpectations() {{
    		httpServeltRequest.getAttribute("id"); result= "1"; result= "1";
    		criteria.uniqueResult(); result= product;
    		criteria.list(); result= productList; result= productList; result= productList; result= productList;
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
			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @After
    public void checkExpectations() {
    }
}
