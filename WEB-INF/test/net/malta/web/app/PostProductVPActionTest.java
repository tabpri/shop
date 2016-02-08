
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
import net.malta.beans.ProductForm;
import net.malta.model.Carriage;
import net.malta.model.Category;
import net.malta.model.Item;
import net.malta.model.Product;

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
public class PostProductVPActionTest {

    private PostProductVPAction action;

    private Product product;

    private ProductForm productForm;

    private Item item;

    private Category category;

    private Carriage carriage;

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
        action = new PostProductVPAction();

        product = Product.Factory.newInstance();

        item = Item.Factory.newInstance();

        category = Category.Factory.newInstance();

        carriage = Carriage.Factory.newInstance();

        product.setId(new Integer(1));

        item.setId(new Integer(1));

        productForm = new ProductForm();
    }

    @Test
    public void testExecute1() {

		try {
			action.execute(actionMapping, productForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute2() {

    	productForm.setSize("1");

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("pricewithtax"); result= "a"; result= "a";
    		httpServeltRequest.getParameter("stocknum"); result= "a";
    		criteria.uniqueResult(); result= product;
    	}};

		try {
			action.execute(actionMapping, productForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute3() {

    	productForm.setId(new Integer(0));
    	productForm.setNo("1");
    	productForm.setName("name");
    	productForm.setSize("1");

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("pricewithtax"); result= "100"; result= "100";
    		httpServeltRequest.getParameter("stocknum"); result= "3";
    		httpServeltRequest.getParameter("carriage"); result= "10";
    		criteria.uniqueResult(); result= category; result= carriage;
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
			action.execute(actionMapping, productForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testExecute4() {

    	productForm.setId(new Integer(1));
    	productForm.setNo("1");
    	productForm.setName("name");
    	productForm.setSize("1");

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("pricewithtax"); result= "100"; result= "100";
    		httpServeltRequest.getParameter("stocknum"); result= "3";
    		httpServeltRequest.getParameter("carriage"); result= "10";
    		httpServeltRequest.getParameter("id"); result= "1";
    		criteria.uniqueResult(); result= product; result= item; result= item; result= category; result= carriage;
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
			action.execute(actionMapping, productForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testExecute5() {

    	productForm.setId(new Integer(1));
    	productForm.setNo("1");
    	productForm.setName("name");
    	productForm.setSize("1");

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("pricewithtax"); result= "100"; result= "100";
    		httpServeltRequest.getParameter("stocknum"); result= "3";
    		httpServeltRequest.getParameter("carriage"); result= "10";
    		httpServeltRequest.getParameter("id"); result= "1";
    		httpServeltRequest.getParameter("ajax"); result= "ajax";
    		criteria.uniqueResult(); result= product; result= item; result= item; result= category; result= carriage;
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
			action.execute(actionMapping, productForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testExecute6() {

    	productForm.setId(new Integer(1));
    	productForm.setNo("1");
    	productForm.setName("name");
    	productForm.setSize("1");

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("pricewithtax"); result= "100"; result= "100";
    		httpServeltRequest.getParameter("stocknum"); result= "3";
    		httpServeltRequest.getParameter("carriage"); result= "10";
    		httpServeltRequest.getParameter("id"); result= "1";
    		httpServeltRequest.getParameter("from"); result= "detail"; result= "detail";
    		criteria.uniqueResult(); result= product; result= item; result= item; result= category; result= carriage;
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
			action.execute(actionMapping, productForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @After
    public void checkExpectations() {
    }
}
