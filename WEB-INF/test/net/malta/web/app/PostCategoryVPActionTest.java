
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
import net.malta.beans.CategoryForm;
import net.malta.model.Category;

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
public class PostCategoryVPActionTest {

    private PostCategoryVPAction action;

    private Category category;

    private CategoryForm categoryForm;

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
        action = new PostCategoryVPAction();

        category = Category.Factory.newInstance();

        categoryForm = new CategoryForm();
    }

    @Test
    public void testExecute1() {

        categoryForm.setId(new Integer(0));
        categoryForm.setName("name");

    	new NonStrictExpectations() {{
    		criteria.uniqueResult(); result= category;
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
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

		new MockUp<StringFullfiller> () {
			@Mock public void fullfil(Object object) {
			}
		};

		try {
			action.execute(actionMapping, categoryForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute2() {

        categoryForm.setId(new Integer(1));
        categoryForm.setName("name");

    	new NonStrictExpectations() {{
    		criteria.uniqueResult(); result= category;
    		httpServeltRequest.getParameter("id"); result= "1";
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
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

		new MockUp<StringFullfiller> () {
			@Mock public void fullfil(Object object) {
			}
		};

		try {
			action.execute(actionMapping, categoryForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute4() {

        category.setId(new Integer(1));

        categoryForm.setId(new Integer(1));
        categoryForm.setName("name");

    	new NonStrictExpectations() {{
    		criteria.uniqueResult(); result= category;
    		httpServeltRequest.getParameter("id"); result= "1";
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
    		@Mock public Session currentSession(ServletContext servletContext) {
    			return hibernateSession;
    		}
		};

		new MockUp<StringFullfiller> () {
			@Mock public void fullfil(Object object) {
			}
		};

		try {
			action.execute(actionMapping, categoryForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute5() {

        categoryForm.setId(new Integer(0));
        categoryForm.setName("name");

    	new NonStrictExpectations() {{
    		criteria.uniqueResult(); result= category;
    		httpServeltRequest.getParameter("from"); result= "";
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
			action.execute(actionMapping, categoryForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute6() {

        categoryForm.setId(new Integer(1));
        categoryForm.setName("name");

    	new NonStrictExpectations() {{
    		criteria.uniqueResult(); result= category;
    		httpServeltRequest.getParameter("id"); result= "1";
    		httpServeltRequest.getParameter("from"); result= "";
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
			action.execute(actionMapping, categoryForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @After
    public void checkExpectations() {
    }
}
