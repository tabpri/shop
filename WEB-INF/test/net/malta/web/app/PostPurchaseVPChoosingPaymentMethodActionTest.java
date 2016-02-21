
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
import net.enclosing.util.StringFullfiller;
import net.malta.beans.PurchaseForm;
import net.malta.model.PaymentMethod;
import net.malta.model.PublicUser;
import net.malta.model.Purchase;
import net.malta.model.StaticData;

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

import com.getsecual.shop.payment.BankingPaymentGatewayConfiguration;
import com.getsecual.shop.payment.GMOPaymentWrapper;
import com.getsecual.shop.payment.PaymentGatewayConfiguration;
import com.gmo_pg.g_pay.client.common.PaymentException;

/**
 * @author Denis Zhdanov
 * @since 01/19/2016
 */
@RunWith(JMockit.class)
public class PostPurchaseVPChoosingPaymentMethodActionTest {

    private PostPurchaseVPChoosingPaymentMethodAction action;

    private PurchaseForm purchaseform;

    private Purchase purchase;

    private PaymentMethod paymentMethod;

    private StaticData staticData;

    private StringBuffer stringbuffer;

    @Mocked
    private HttpServletRequest httpServletRequest;

    @Mocked
    private HttpServletResponse httpServletResponse;

    @Mocked
    private ActionMapping actionMapping;

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
        action = new PostPurchaseVPChoosingPaymentMethodAction();
        purchaseform = new PurchaseForm();
        purchase = Purchase.Factory.newInstance();
        purchase.setId(1);
        purchase.setPaymentMethod(PaymentMethod.Factory.newInstance());
        purchase.setPublicUser(PublicUser.Factory.newInstance());
        purchase.setCarriage(1000);
        purchase.setTotal(100000);
        purchaseform.setPaymentMethod(new Integer(1));

        paymentMethod = PaymentMethod.Factory.newInstance();
        staticData = StaticData.Factory.newInstance();

        stringbuffer = new StringBuffer();
        stringbuffer.append("http://test/myaction.do");
    }

    /**
     * 正常系 ajaxで呼ばれた場合を想定
     */
    @Test
    public void testExecute1() {

    	new NonStrictExpectations() {{
    		httpServletRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServletRequest.getParameter("ajax"); result= "ajax";
    		criteria.uniqueResult(); result = paymentMethod;
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
    		@Mock public void fullfil(Object obj) {
    		}
		};

		try {
			action.execute(actionMapping, purchaseform, httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    /**
     * 正常系 決済を実行→(本人認証なし)→正常終了(PostPurchaseDetail.doにリダイレクト)
     */
    @Test
    public void testExecute2() {

    	new NonStrictExpectations() {{
    		httpServletRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServletRequest.getParameter("from"); result = "from";
    		httpServletRequest.getRequestURL(); result= stringbuffer;
    		criteria.uniqueResult(); result = paymentMethod; result= staticData;
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
    		@Mock public void fullfil(Object obj) {
    		}
		};

		new MockUp<StringFullfiller> () {
    		@Mock public void fullfil(Object obj) {
    		}
		};
		new MockUp<GMOPaymentWrapper> () {
    		@Mock public void executePayment(PaymentGatewayConfiguration paymentGatewayConfiguration) {
    		}
		};

		try {
			action.execute(actionMapping, purchaseform, httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    /**
     * 正常系 決済を実行→(本人認証なし)→正常終了(PostPurchaseVPForSettingNonTemp.doにリダイレクト)
     */
    @Test
    public void testExecute3() {

    	new NonStrictExpectations() {{
    		httpServletRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServletRequest.getRequestURL(); result= stringbuffer;
    		criteria.uniqueResult(); result = paymentMethod; result= staticData;
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
    		@Mock public void fullfil(Object obj) {
    		}
		};

		new MockUp<StringFullfiller> () {
    		@Mock public void fullfil(Object obj) {
    		}
		};
		new MockUp<GMOPaymentWrapper> () {
    		@Mock public void executePayment(PaymentGatewayConfiguration paymentGatewayConfiguration) {
    		}
		};

		try {
			action.execute(actionMapping, purchaseform, httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    /**
     * 正常系 決済を実行→(本人認証あり(ＨＴＭＬ書き出し))
     */
    @Test
    public void testExecute4() {

    	new NonStrictExpectations() {{
    		httpServletRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServletRequest.getRequestURL(); result= stringbuffer;
    		criteria.uniqueResult(); result = paymentMethod; result= staticData;
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
    		@Mock public void fullfil(Object obj) {
    		}
		};

		new MockUp<StringFullfiller> () {
    		@Mock public void fullfil(Object obj) {
    		}
		};
		new MockUp<GMOPaymentWrapper> () {
    		@Mock public void executePayment(PaymentGatewayConfiguration paymentGatewayConfiguration) {
    		}
		};

		new MockUp<BankingPaymentGatewayConfiguration> () {
    		@Mock public String getRedirectContents() {
    			return "<html></html>";
    		}
		};

		try {
			action.execute(actionMapping, purchaseform, httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    /**
     * 異常系 決済を実行→PaymentException
     */
    @Test
    public void testExecute5() {

    	new NonStrictExpectations() {{
    		httpServletRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServletRequest.getRequestURL(); result= stringbuffer;
    		criteria.uniqueResult(); result = paymentMethod; result= staticData;
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
    		@Mock public void fullfil(Object obj) {
    		}
		};

		new MockUp<StringFullfiller> () {
    		@Mock public void fullfil(Object obj) {
    		}
		};

		new MockUp<GMOPaymentWrapper> () {
    		@Mock public void executePayment(PaymentGatewayConfiguration paymentGatewayConfiguration) throws PaymentException {
    			throw new PaymentException();
    		}
		};

		try {
			action.execute(actionMapping, purchaseform, httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    /**
     * 異常系 決済を実行→取引不可などのエラー情報が取得された場合
     */
    @Test
    public void testExecute6() {

    	new NonStrictExpectations() {{
    		httpServletRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServletRequest.getRequestURL(); result= stringbuffer;
    		criteria.uniqueResult(); result = paymentMethod; result= staticData;
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
    		@Mock public void fullfil(Object obj) {
    		}
		};

		new MockUp<StringFullfiller> () {
    		@Mock public void fullfil(Object obj) {
    		}
		};

		new MockUp<GMOPaymentWrapper> () {
    		@Mock public void executePayment(PaymentGatewayConfiguration paymentGatewayConfiguration) throws PaymentException {
    		}
		};

		new MockUp<BankingPaymentGatewayConfiguration> () {
    		@Mock public List getErrList() {
    			List errorList = new ArrayList();
    			errorList.add(new String[] {"E01", "E9999999999"});
    			return errorList;
    		}
		};

		try {
			action.execute(actionMapping, purchaseform, httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    /**
     * 追加 PaymentMethod未指定
     */
    @Test
    public void testExecute7() {
    	try {

    		purchaseform.setPaymentMethod(null);

			action.execute(actionMapping, purchaseform, httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    /**
     * 追加 PaymentMethod未指定
     */
    @Test
    public void testExecute8() {
    	try {

    		purchaseform.setPaymentMethod(0);

			action.execute(actionMapping, purchaseform, httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
    }

    /**
     * 追加 本人認証後の決済
     */
    @Test
    public void testExecute9() {

    	new NonStrictExpectations() {{
    		httpServletRequest.getParameter("MD"); result = "abcde";
    		httpServletRequest.getParameter("PaRes"); result = "abcde";
    		httpServletRequest.getSession().getAttribute("purchaseform"); result= purchaseform;
    		httpServletRequest.getSession().getAttribute("deliverymethod"); result= "1";
    		httpServletRequest.getSession().getAttribute("purchase"); result= purchase;
    		httpServletRequest.getRequestURL(); result= stringbuffer;
    		criteria.uniqueResult(); result = paymentMethod; result= staticData;
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
    		@Mock public void fullfil(Object obj) {
    		}
		};

		new MockUp<StringFullfiller> () {
    		@Mock public void fullfil(Object obj) {
    		}
		};
		new MockUp<GMOPaymentWrapper> () {
    		@Mock public void executePayment(PaymentGatewayConfiguration paymentGatewayConfiguration) {
    		}
		};

		try {
			action.execute(actionMapping, purchaseform, httpServletRequest, httpServletResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}

    }

    @After
    public void checkExpectations() {
    }
}
