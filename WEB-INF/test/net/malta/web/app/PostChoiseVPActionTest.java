
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
import net.malta.beans.ChoiseForm;
import net.malta.model.Carriage;
import net.malta.model.Choise;
import net.malta.model.Item;
import net.malta.model.Purchase;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
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
public class PostChoiseVPActionTest {

    private PostChoiseVPAction action;

    private Purchase purchase;

    private Choise choise;

    private Item item;

    private ChoiseForm choiseForm;

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
    private SQLQuery sqlQuery;

    @Mocked
    private Criteria criteria;

    @Before
    public void setUp() {
        action = new PostChoiseVPAction();

        choise = Choise.Factory.newInstance();

        choise.setOrdernum(0);

        Carriage carriage = Carriage.Factory.newInstance();

        carriage.setValue(100);

        item = Item.Factory.newInstance();

        item.setPricewithtax(10);
        item.setCarriage(carriage);

        choiseForm = new ChoiseForm();

        choiseForm.setPurchase(new Integer(1));
        choiseForm.setItem(new Integer(1));

        purchase = Purchase.Factory.newInstance();

        choise.setItem(item);
    }

    @Test
    public void testExecute1() {

    	choiseForm.setId(new Integer(0));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getParameter("id"); result= "1";
    		criteria.uniqueResult(); result= null;
    		hibernateSession.createQuery(anyString); result= sqlQuery; result= sqlQuery; result= sqlQuery;
    		sqlQuery.uniqueResult(); result= "0"; result= "0"; result= "0\\]0";
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
			action.execute(actionMapping, choiseForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @Test
    public void testExecute2() {

    	choiseForm.setId(new Integer(1));

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getParameter("id"); result= "1";
    		criteria.uniqueResult(); result= choise; result= choise;
    		hibernateSession.createQuery(anyString); result= sqlQuery; result= sqlQuery; result= sqlQuery;
    		sqlQuery.uniqueResult(); result= "0"; result= "0"; result= "0\\]0";
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
			action.execute(actionMapping, choiseForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @After
    public void checkExpectations() {
    }
}
