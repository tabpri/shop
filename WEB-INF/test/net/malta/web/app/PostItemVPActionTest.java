
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
import net.malta.beans.ItemForm;
import net.malta.model.Carriage;
import net.malta.model.Item;
import net.malta.model.Product;

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
public class PostItemVPActionTest {

    private PostItemVPAction action;

    private Item item;

    private ItemForm itemForm;

    private Product product;

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
    private Criteria criteria;

    @Before
    public void setUp() {
        action = new PostItemVPAction();

        item = Item.Factory.newInstance();

        item.setId(new Integer(1));

        itemForm = new ItemForm();

        product = Product.Factory.newInstance();

        product.setId(new Integer(1));

        item.setProduct(product);

        carriage = Carriage.Factory.newInstance();
    }

    @Test
    public void testExecute1() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getScheme(); result= "http";
    		criteria.uniqueResult(); result= item; result= product;
    	}};

		try {
			action.execute(actionMapping, itemForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute2() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getScheme(); result= "https";
    		httpServeltRequest.getParameter("pricewithtax"); result= ""; result= "";
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
			action.execute(actionMapping, itemForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute3() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getScheme(); result= "https";
    		httpServeltRequest.getParameter("pricewithtax"); result= "a"; result= "a";
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
			action.execute(actionMapping, itemForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute4() {

    	itemForm.setNo("1");
    	itemForm.setId(new Integer(0));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getScheme(); result= "https";
    		httpServeltRequest.getParameter("pricewithtax"); result= "10"; result= "10";
    		criteria.uniqueResult(); result= item;
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
			action.execute(actionMapping, itemForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute5() {

    	itemForm.setName("name");
    	itemForm.setNo("1");
    	itemForm.setId(new Integer(0));

    	itemForm.setProduct(new Integer(1));
    	itemForm.setCarriage(new Integer(1));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getScheme(); result= "https";
    		httpServeltRequest.getParameter("pricewithtax"); result= "10"; result= "10";
    		criteria.uniqueResult(); result= null; result= product; result= carriage;
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
			action.execute(actionMapping, itemForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute6() {

    	itemForm.setName("name");
    	itemForm.setNo("1");
    	itemForm.setId(new Integer(1));

    	itemForm.setProduct(new Integer(1));
    	itemForm.setCarriage(new Integer(1));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getScheme(); result= "https";
    		httpServeltRequest.getParameter("pricewithtax"); result= "10"; result= "10";
    		httpServeltRequest.getParameter("id"); result= "1";
    		criteria.uniqueResult(); result= item; result= product; result= carriage;
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
			action.execute(actionMapping, itemForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute7() {

    	itemForm.setName("name");
    	itemForm.setNo("1");
    	itemForm.setId(new Integer(1));

    	itemForm.setProduct(new Integer(1));
    	itemForm.setCarriage(new Integer(1));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getScheme(); result= "https";
    		httpServeltRequest.getParameter("pricewithtax"); result= "10"; result= "10";
    		httpServeltRequest.getParameter("id"); result= "1";
    		httpServeltRequest.getParameter("ajax"); result= "ajax";
    		criteria.uniqueResult(); result= item; result= product; result= carriage;
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
			action.execute(actionMapping, itemForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute8() {

    	itemForm.setName("name");
    	itemForm.setNo("1");
    	itemForm.setId(new Integer(1));

    	itemForm.setProduct(new Integer(1));
    	itemForm.setCarriage(new Integer(1));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getScheme(); result= "https";
    		httpServeltRequest.getParameter("pricewithtax"); result= "10"; result= "10";
    		httpServeltRequest.getParameter("id"); result= "1";
    		httpServeltRequest.getParameter("from"); result= "detail"; result= "detail";
    		criteria.uniqueResult(); result= item; result= product; result= carriage;
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
			action.execute(actionMapping, itemForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @After
    public void checkExpectations() {
    }
}
