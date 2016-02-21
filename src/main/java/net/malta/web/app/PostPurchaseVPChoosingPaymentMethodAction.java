package net.malta.web.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.enclosing.util.HTTPGetRedirection;
import net.enclosing.util.HibernateSession;
import net.enclosing.util.StringFullfiller;
import net.malta.beans.PurchaseForm;
import net.malta.model.PaymentMethod;
import net.malta.model.Purchase;
import net.malta.model.StaticData;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.getsecual.shop.payment.BankingPaymentGatewayConfiguration;
import com.getsecual.shop.payment.GMOPaymentWrapper;
import com.getsecual.shop.payment.PaymentGatewayConfiguration;
import com.gmo_pg.g_pay.client.common.Method;

public class PostPurchaseVPChoosingPaymentMethodAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 本人認証画面からの戻り値
		String MD = req.getParameter("MD");
		String PaRes = req.getParameter("PaRes");

		String deliverymethod = req.getParameter("deliverymethod");

		PurchaseForm purchaseform = (PurchaseForm) form;

		if (MD != null && PaRes != null) {
			// PurchaseFormとdeliverymethodの値を復元
			purchaseform = (PurchaseForm)req.getSession().getAttribute("purchaseform");
			deliverymethod = (String)req.getSession().getAttribute("deliverymethod");
			req.getSession().removeAttribute("purchaseform");
			req.getSession().removeAttribute("deliverymethod");
		} else {
			req.getSession().removeAttribute("purchaseform");
			req.getSession().removeAttribute("deliverymethod");
		}

		Integer paymentMethodInt = purchaseform.getPaymentMethod();
		purchaseform.setPaymentMethod(null);

        String error = "";
		if(paymentMethodInt==null || paymentMethodInt==0){
		   error += "支払い方法が選択されていません<br/>";
		}

		if(StringUtils.isNotBlank(error)){
			req.getSession().setAttribute("error",error);
			req.setAttribute("form",purchaseform);
			new HTTPGetRedirection(req, res, "ShowPurchaseForConfirmation.do", null);
			return null;
		}

		Purchase purchase = (Purchase)req.getSession().getAttribute("purchase");
		if(StringUtils.isNotBlank(req.getParameter("deliverymethod"))){
//			Integer deliverymethodInteger = Integer.valueOf(req.getParameter("deliverymethod"));
//			req.getSession().setAttribute("deliverymethod", deliverymethodInteger);
		}

		ServletContext servletContext = this.getServlet().getServletContext();

		Session session = HibernateSession.currentSession(servletContext);

		session.evict(purchase);

		session.refresh(purchase);

		StringFullfiller.fullfil(purchase);

		Criteria criteria2 = session.createCriteria(PaymentMethod.class);
		criteria2.add(Restrictions.idEq(paymentMethodInt));
		PaymentMethod paymentmethod = (PaymentMethod) criteria2.uniqueResult();
		purchase.setPaymentMethod(paymentmethod);

		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(purchase);
		transaction.commit();
		session.flush();

		if (StringUtils.isNotBlank(req.getParameter("ajax"))) {
			req.setAttribute("message", "success");
			return mapping.findForward("success");
		}

//		String st_code = "";
//		if(paymentmethod.getId().intValue() == 1){
//			st_code = "10000-0000-00000";
//			System.err.println("st_code = 10000-0000-00000");
//		}
//		if(paymentmethod.getId().intValue() == 2){
//			st_code = "00100-0000-00000";
//			System.err.println("st_code = 00100-0000-00000");
//		}
//		if(paymentmethod.getId().intValue() == 3){
//			st_code = "00010-0000-00000";
//			System.err.println("st_code = 000010-0000-00000");
//
//		}
//		if(paymentmethod.getId().intValue() == 4){
//			new HTTPGetRedirection(req, res, "PostPurchaseVPForSettingNonTemp.do",
//					purchase.getId().toString(),"temp=0&deliverymethod="+req.getParameter("deliverymethod"));
//			return null;
//		}

		Criteria criteriaStaticData = session.createCriteria(StaticData.class);
		criteriaStaticData.add(Restrictions.eq("id", new Integer(1)));
		StaticData staticData = (StaticData) criteriaStaticData.uniqueResult();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhMMss");

		/** 決済処理 */
		try {

			GMOPaymentWrapper gmoPaymentWrapper = new GMOPaymentWrapper();

			PaymentGatewayConfiguration paymentGatewayConfiguration = new BankingPaymentGatewayConfiguration();

			// 取引情報
			paymentGatewayConfiguration.setShopId(GMOPaymentWrapper.SHOP_ID);
			paymentGatewayConfiguration.setShopPass(GMOPaymentWrapper.SHOP_PASS);
			paymentGatewayConfiguration.setOrderId("id" + purchase.getId().toString() + "date" +  dateFormat.format(new Date()));
			paymentGatewayConfiguration.setJobCd(GMOPaymentWrapper.JOB_CD_CAPTURE);
			paymentGatewayConfiguration.setAmount(purchase.getTotal());
			paymentGatewayConfiguration.setTdFlag(GMOPaymentWrapper.TD_FLAG_USE);

			// 決済情報
			paymentGatewayConfiguration.setMethod(Method.IKKATU);
			paymentGatewayConfiguration.setPayTimes(1);
			paymentGatewayConfiguration.setCardNo(purchaseform.getCardNo());
			paymentGatewayConfiguration.setExpire(purchaseform.getExpire());
			paymentGatewayConfiguration.setSecurityCode(purchaseform.getSecurityCode());
			paymentGatewayConfiguration.setHttpAccept(req.getHeader("ACCEPT"));
			paymentGatewayConfiguration.setHttpUserAgent(req.getHeader("USER-AGENT"));
			paymentGatewayConfiguration.setSiteId(GMOPaymentWrapper.SITE_ID);
			paymentGatewayConfiguration.setSitePass(GMOPaymentWrapper.SITE_PASS);
			paymentGatewayConfiguration.setMemberId(purchaseform.getMemberId());
			paymentGatewayConfiguration.setSeqMode(purchaseform.getSeqMode());
			paymentGatewayConfiguration.setCardSeq(purchaseform.getCardSeq());
			paymentGatewayConfiguration.setCardPass(purchaseform.getCardPass());
			paymentGatewayConfiguration.setTermUrl(req.getRequestURL().toString());

			// 本人認証画面から返却される値
			paymentGatewayConfiguration.setPaRes(PaRes);
			paymentGatewayConfiguration.setMD(MD);

			// 決済実行
			gmoPaymentWrapper.executePayment(paymentGatewayConfiguration);

			// エラー情報取得
			List errList = paymentGatewayConfiguration.getErrList();

			// エラーコード（残高不足など）がある場合
			if (errList != null) {
				String[] errinfo = (String[])errList.get(0);
				req.getSession().setAttribute("error",errinfo[1] + " 決済が正常に行われませんでした。");
				req.setAttribute("form",purchaseform);
				new HTTPGetRedirection(req, res, "ShowPurchaseForConfirmation.do", null);
				return null;
			}

			// ACS呼出判定が1(要)の場合（本人認証サービスの呼出が必要な場合）
			if (paymentGatewayConfiguration.getRedirectContents() != null) {
				// セッションにフォームを一時保存
				purchaseform.setPaymentMethod(paymentMethodInt);
				req.getSession().setAttribute("purchaseform", purchaseform);
				req.getSession().setAttribute("deliverymethod", deliverymethod);
				// リダイレクト用ページに遷移(レスポンスにHTML書き出し)
				res.getWriter().write(paymentGatewayConfiguration.getRedirectContents());
				res.getWriter().flush();
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("error"," システムエラーが発生しました。");
			req.setAttribute("form",purchaseform);
			new HTTPGetRedirection(req, res, "ShowPurchaseForConfirmation.do", null);
			return null;
		}

		// struts-config.xml exist?
		if (StringUtils.isNotBlank(req.getParameter("from"))
				&& !req.getParameter("from").equals("")) {
			System.err.println("mark 1");
			new HTTPGetRedirection(req, res, "PostPurchaseDetail.do", purchase
					.getId().toString(),"deliverymethod="+deliverymethod);
			return null;
		}

		// 決済完了
		System.err.println("mark 2, navigating to ForSettingNonTemp.");
		new HTTPGetRedirection(req, res, "PostPurchaseVPForSettingNonTemp.do",
				purchase.getId().toString(),"temp=0&deliverymethod="+deliverymethod);
		return null;
	}
}