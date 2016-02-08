
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
import net.malta.model.Attachment;
import net.malta.model.Carriage;
import net.malta.model.Choise;
import net.malta.model.Item;
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
public class UpdatePurchaseForTotalActionTest {

    private UpdatePurchaseForTotalAction action;

    private Purchase purchase;

    @Mocked
    private Attachment attachment;

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
        action = new UpdatePurchaseForTotalAction();

        purchase = Purchase.Factory.newInstance();
    }

    @Test
    public void testExecute1() {

    	List choiseList = new ArrayList();
    	for (int i= 0; i < 2; i++) {
    		Choise choise = Choise.Factory.newInstance();
    		Item item = Item.Factory.newInstance();
    		Carriage carriage = Carriage.Factory.newInstance();
    		choise.setId(new Integer(i + 1));
    		choise.setOrdernum(100);
    		item.setPricewithtax(30);
    		carriage.setValue(20);
    		item.setCarriage(carriage);
    		choise.setItem(item);

    		choiseList.add(choise);
    	}

    	purchase.setChoises(choiseList);

    	new NonStrictExpectations() {{
    		httpServeltRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServeltRequest.getParameter("id"); result= "1"; result= "1";
    		httpServeltRequest.getParameter("ordernum_1"); result= "100"; result= "100"; result= "100";
    		httpServeltRequest.getParameter("ordernum_2"); result= "100"; result= "100"; result= "100";
    		httpServeltRequest.getParameter("ordernum_3"); result= "100"; result= "100"; result= "100";
    		criteria.uniqueResult(); result= purchase;
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
