
package net.malta.web.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
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
import net.enclosing.util.SimpleMail;
import net.malta.model.Choise;
import net.malta.model.Item;
import net.malta.model.PublicUser;
import net.malta.model.Purchase;
import net.malta.model.StaticData;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class MailAboutPurchaseToAdminActionTest {

    private MailAboutPurchaseToAdminAction action;

    private Purchase purchase;

    private PublicUser publicUser;

    private StaticData staticData;

    @Mocked
    private ActionServlet actionServlet;

    @Mocked
    private ServletContext servletContext;

    @Mocked
    private WebApplicationContext webApplicationContext;

    @Mocked
    private ClassPathXmlApplicationContext classPathXmlApplicationContext;

    @Mocked
    private StringBuilder stringBuilder;

    @Mocked
    private BeanFactory factory;

    @Mocked
    private ActionMapping actionMapping;

    @Mocked
    private ActionForm actionForm;

    @Mocked
    private HttpServletRequest httpServeltRequest;

    @Mocked
    private HttpServletResponse httpServletResponse;

    @Mocked
    private Session hibernateSession;

    @Mocked
    private Criteria criteria;

    @Mocked
    private SimpleMail simpleMail;

    @Before
    public void setUp() {
    	purchase = Purchase.Factory.newInstance();
    	staticData = StaticData.Factory.newInstance();

    	publicUser = PublicUser.Factory.newInstance();
    	publicUser.setName("test");
    	publicUser.setKana("test");
    	publicUser.setZipfour(1000011);
    	publicUser.setAddress("address");
    	publicUser.setPhone("098-9999-0001");
    	publicUser.setFax("098-2020-2020");
    	publicUser.setKeitai("090-0000-0011");

    	purchase.setPublicUser(publicUser);

    	List choises = new ArrayList();
    	for(int i = 0; i < 2; i++) {
    		Choise choise = Choise.Factory.newInstance();
    		choise.setPricewithtax(1000);
    		choise.setOrdernum(10);

    		Item item = Item.Factory.newInstance();
    		item.setNo("09999");
    		item.setName("test");

    		choise.setItem(item);

    		choises.add(choise);
    	}
    	purchase.setChoises(choises);
    }

    @Test
    public void testExecute1() throws Exception {

    	new Expectations() {{
    		SimpleMail.create(factory); returnValue(simpleMail);
    	}};

    	new NonStrictExpectations() {{
    		simpleMail.send("MailAboutPurchaseToAdmin.eml", "order@malta-net.jp", withAny(new HashMap()));;
    		actionServlet.getServletContext(); result= servletContext;
    		criteria.uniqueResult(); result= purchase; result= staticData;
    	}};

    	new MockUp<MailAboutPurchaseToAdminAction> () {
			@Mock public ActionServlet getServlet() {
				return actionServlet;
			}
		};

    	new MockUp<WebApplicationContextUtils> () {
			@Mock public WebApplicationContext getWebApplicationContext(ServletContext sc) {
				return webApplicationContext;
			}
		};

		new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(BeanFactory factory) {
    			return hibernateSession;
    		}
		};

        action = new MailAboutPurchaseToAdminAction();

        try {
			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testExecute2() throws Exception {

    	new Expectations() {{
    		SimpleMail.create(factory); returnValue(simpleMail);
    	}};

    	new NonStrictExpectations() {{
    		simpleMail.send("MailAboutPurchaseToAdmin.eml", "order@malta-net.jp", withAny(new HashMap())); result= new Exception();
    		actionServlet.getServletContext(); result= servletContext;
    		criteria.uniqueResult(); result= purchase; result= staticData;
    	}};

    	new MockUp<MailAboutPurchaseToAdminAction> () {
			@Mock public ActionServlet getServlet() {
				return actionServlet;
			}
		};

    	new MockUp<WebApplicationContextUtils> () {
			@Mock public WebApplicationContext getWebApplicationContext(ServletContext sc) {
				return webApplicationContext;
			}
		};

		new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(BeanFactory factory) {
    			return hibernateSession;
    		}
		};

        action = new MailAboutPurchaseToAdminAction();

        try {
			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testExecute3() throws Exception {

    	new Expectations() {{
    		SimpleMail.create(factory); returnValue(simpleMail);
    	}};

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("id"); result= "123"; result= "123"; result= "123";
    		simpleMail.send("MailAboutPurchaseToAdmin.eml", "order@malta-net.jp",  withAny(new HashMap()));;
    		actionServlet.getServletContext(); result= servletContext;
    		criteria.uniqueResult(); result= purchase; result= staticData;
    	}};

    	new MockUp<MailAboutPurchaseToAdminAction> () {
			@Mock public ActionServlet getServlet() {
				return actionServlet;
			}
		};

    	new MockUp<WebApplicationContextUtils> () {
			@Mock public WebApplicationContext getWebApplicationContext(ServletContext sc) {
				return webApplicationContext;
			}
		};

		new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(BeanFactory factory) {
    			return hibernateSession;
    		}
		};

        action = new MailAboutPurchaseToAdminAction();

        try {
			action.execute(actionMapping, actionForm, httpServeltRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testExecute4() throws Exception {

    	new Expectations() {{
    		SimpleMail.create(factory); returnValue(simpleMail);
    	}};

    	new NonStrictExpectations() {{
    		httpServeltRequest.getParameter("id"); result= "123"; result= "123"; result= "123";
    		simpleMail.send("MailAboutPurchaseToAdmin.eml", "order@malta-net.jp",  withAny(new HashMap()));;
    		actionServlet.getServletContext(); result= servletContext;
    		criteria.uniqueResult(); result= purchase; result= staticData;
    	}};

    	new MockUp<MailAboutPurchaseToAdminAction> () {
			@Mock public ActionServlet getServlet() {
				return actionServlet;
			}
		};

    	new MockUp<WebApplicationContextUtils> () {
			@Mock public WebApplicationContext getWebApplicationContext(ServletContext sc) {
				return webApplicationContext;
			}
		};

		new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(BeanFactory factory) {
    			return hibernateSession;
    		}
		};

        try {
        	MailAboutPurchaseToAdminAction.main(null);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    @Test
    public void testExecute5() throws Exception {

    	new MockUp<HibernateSession> () {
    		@Mock public Session currentSession(BeanFactory factory) throws Exception {
    			throw new Exception();
    		}
		};

		MailAboutPurchaseToAdminAction.main(null);
    }

    @After
    public void checkExpectations() {
    }
}
