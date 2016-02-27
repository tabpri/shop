
package net.malta.web.app;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import net.enclosing.util.SimpleMail;
import net.malta.model.Choise;
import net.malta.model.Prefecture;
import net.malta.model.PublicUser;
import net.malta.model.Purchase;
import net.malta.model.StaticData;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.jmock.Expectations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class MailAboutPurchaseToPublicUserActionTest {

    private MailAboutPurchaseToPublicUserAction action;

    private PublicUser publicUser;

    private Purchase purchase;

    private StaticData staticData;

    @Mocked
    private ActionMapping actionMapping;

    @Mocked
    private ActionForward actionForward;

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

    @Mocked
    private BeanFactory factory;

    @Before
    public void setUp() {
        action = new MailAboutPurchaseToPublicUserAction();

        publicUser = PublicUser.Factory.newInstance();

        Prefecture prefecture = Prefecture.Factory.newInstance();

        publicUser.setPrefecture(prefecture);

        publicUser.setMail("user@test.com");

        publicUser.setName("pName");
        publicUser.setKana("Kana");
        publicUser.setZipfour(1011);
        publicUser.setAddress("address");
        publicUser.setBuildingname("buildingname");

        purchase = Purchase.Factory.newInstance();

        List choises = new ArrayList();

        Choise choise = Choise.Factory.newInstance();

        choise.setName("name");
        choise.setPricewithtax(1000);
		choise.setOrdernum(10);

		choises.add(choise);

        purchase.setChoises(choises);

        staticData = StaticData.Factory.newInstance();

        staticData.setFromaddress("test@test.com");

        purchase.setPublicUser(publicUser);
    }

    @Test
    public void testExecute1() {

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
    		simpleMail.send("MailAboutPurchaseToPublicUser.eml", purchase.getPublicUser().getMail(), withAny(new HashMap()));;
    		simpleMail.send("MailAboutPurchaseToAdmin.eml", "aandl.order@gmail.com", withAny(new HashMap()));;
    		simpleMail.send("MailAboutPurchaseToAdmin.eml", "toukubo+africaandleo@gmail.com", withAny(new HashMap()));;
    		criteria.uniqueResult(); result= purchase; result= staticData;
    	}};

    	new MockUp<ClassPathXmlApplicationContext> () {
			@Mock public void $init(String[] configLocations) {
				this.getMockInstance();
			}
		};

    	try {
			action.execute(new Integer(1), hibernateSession, 1);
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
    		simpleMail.send("MailAboutPurchaseToPublicUser.eml", purchase.getPublicUser().getMail(), withAny(new HashMap()));;
    		simpleMail.send("MailAboutPurchaseToAdmin.eml", "aandl.order@gmail.com", withAny(new HashMap()));;
    		simpleMail.send("MailAboutPurchaseToAdmin.eml", "toukubo+africaandleo@gmail.com", withAny(new HashMap()));;
    		criteria.uniqueResult(); result= purchase; result= staticData;
    	}};

    	new MockUp<ClassPathXmlApplicationContext> () {
			@Mock public void $init(String[] configLocations) {
				this.getMockInstance();
			}
		};

		new MockUp<MimeUtility> () {
			@Mock public String encodeText(String paramString1, String paramString2, String paramString3) throws UnsupportedEncodingException {
				throw new UnsupportedEncodingException();
			}
		};

    	try {
			action.execute(new Integer(1), hibernateSession, 1);
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
    		simpleMail.send("MailAboutPurchaseToPublicUser.eml", purchase.getPublicUser().getMail(), withAny(new HashMap())); result= new Exception();
    		simpleMail.send("MailAboutPurchaseToAdmin.eml", "aandl.order@gmail.com",  withAny(new HashMap())); result= new Exception();
    		simpleMail.send("MailAboutPurchaseToAdmin.eml", "toukubo+africaandleo@gmail.com",  withAny(new HashMap())); result= new Exception();
    		criteria.uniqueResult(); result= purchase; result= staticData;
    	}};

    	new MockUp<ClassPathXmlApplicationContext> () {
			@Mock public void $init(String[] configLocations) {
				this.getMockInstance();
			}
		};

    	try {
			action.execute(new Integer(1), hibernateSession, 1);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @After
    public void checkExpectations() {
    }
}
