
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
import net.malta.model.Choise;
import net.malta.model.DeliveryAddress;
import net.malta.model.DeliveryAddressChoise;
import net.malta.model.PublicUser;
import net.malta.model.Purchase;

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
public class ShowPurchaseForConfirmationActionTest {

    private ShowPurchaseForConfirmationAction action;

    private Purchase purchase;

    private PublicUser publicUser;

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

    @Before
    public void setUp() {
        action = new ShowPurchaseForConfirmationAction();

        purchase = Purchase.Factory.newInstance();

        publicUser = PublicUser.Factory.newInstance();
        publicUser.setId(new Integer(1));

    }

    @Test
    public void testExecute1() {
    	List choisesList = new ArrayList();
        for (int i = 0; i < 3; i++) {
        	Choise choise = Choise.Factory.newInstance();
        	choise.setOrdernum(100);
        	List deliveryAddressChoiseList = new ArrayList();
        	for (int j = 0; j < 5; j++) {
        		DeliveryAddressChoise deliveryAddressChoise = DeliveryAddressChoise.Factory.newInstance();
        		deliveryAddressChoise.setOrdernum(10);
        		deliveryAddressChoiseList.add(deliveryAddressChoise);
        	}
        	choise.setDeliveryAddressChoises(deliveryAddressChoiseList);
        	choisesList.add(choise);
        }
        purchase.setChoises(choisesList);

        new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getSession().getAttribute("deliverymethod"); result= new Integer(3); result= new Integer(3);
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

        new NonStrictExpectations() {{
        	httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getSession().getAttribute("u"); result= publicUser;
    		httpServeltRequest.getSession().getAttribute("deliverymethod"); result= new Integer(2); result= new Integer(2);
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

    	List choisesList = new ArrayList();
        for (int i = 0; i < 3; i++) {
        	Choise choise = Choise.Factory.newInstance();
        	choise.setId(new Integer(i + 1));
        	choise.setOrdernum(100);
        	List deliveryAddressChoiseList = new ArrayList();
        	for (int j = 0; j < 5; j++) {
        		DeliveryAddressChoise deliveryAddressChoise = DeliveryAddressChoise.Factory.newInstance();
        		DeliveryAddress deliveryAddress = DeliveryAddress.Factory.newInstance();

        		deliveryAddressChoise.setId(new Integer(j + 1));
        		deliveryAddressChoise.setOrdernum(10);

        		deliveryAddress.setId(new Integer(j + 1));

        		deliveryAddressChoise.setDeliveryAddress(deliveryAddress);
        		deliveryAddressChoiseList.add(deliveryAddressChoise);
        	}
        	choise.setDeliveryAddressChoises(deliveryAddressChoiseList);
        	choisesList.add(choise);
        }
        purchase.setChoises(choisesList);

        new NonStrictExpectations() {{
        	httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getSession().getAttribute("u"); result= publicUser;
    		httpServeltRequest.getSession().getAttribute("deliverymethod"); result= new Integer(1); result= new Integer(1);
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
