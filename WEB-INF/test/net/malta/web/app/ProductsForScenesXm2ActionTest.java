
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
import net.malta.beans.ItemForm;
import net.malta.model.Category;
import net.malta.model.Item;

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
public class ProductsForScenesXm2ActionTest {

    private ProductsForScenesXm2Action action;

    private Category category;

    private Item item;

    private ItemForm itemForm;

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
        action = new ProductsForScenesXm2Action();

        category = Category.Factory.newInstance();

        item = Item.Factory.newInstance();

        itemForm = new ItemForm();
    }

    @Test
    public void testExecute1() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("removed"); result= "true"; result= "true";
    		httpServeltRequest.getParameter("category"); result= "2"; result= "2";
    		httpServeltRequest.getParameter("datestartdate"); result= "2016/01/01"; result= "2016/01/01";
    		httpServeltRequest.getParameter("dateenddate"); result= "2019/01/01"; result= "2019/01/01";
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		criteria.uniqueResult(); result= category; result= item;
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
			action.execute(actionMapping, itemForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute2() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("removed"); result= "false"; result= "false";
    		httpServeltRequest.getParameter("category"); result= "2"; result= "2";
    		httpServeltRequest.getParameter("datestartdate"); result= "2016/01/01"; result= "2016/01/01";
    		httpServeltRequest.getParameter("dateenddate"); result= "2019/01/01"; result= "2019/01/01";
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		httpServeltRequest.getParameter("displayexport"); result= "displayexport";
    		criteria.uniqueResult(); result= category; result= item;
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
			action.execute(actionMapping, itemForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute3() {

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("removed"); result= "true"; result= "true";
    		httpServeltRequest.getParameter("category"); result= "2"; result= "2";
    		httpServeltRequest.getParameter("datestartdate"); result= "2016/01/01"; result= "2016/01/01";
    		httpServeltRequest.getParameter("dateenddate"); result= "2019/01/01"; result= "2019/01/01";
    		httpServeltRequest.getAttribute("form"); result=itemForm;
    		criteria.uniqueResult(); result= category; result= item;
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
			action.execute(actionMapping, itemForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @After
    public void checkExpectations() {
    }
}
