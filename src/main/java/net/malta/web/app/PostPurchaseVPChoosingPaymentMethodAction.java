package net.malta.web.app;

import java.text.SimpleDateFormat;
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

public class PostPurchaseVPChoosingPaymentMethodAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		PurchaseForm purchaseform = (PurchaseForm) form;
		Integer paymentMethodInt = purchaseform.getPaymentMethod();
		purchaseform.setPaymentMethod(null);

//        String error = "";
//		if(paymentMethodInt==null || paymentMethodInt==0){
//		   error += "支払い方法が選択されていません<br/>";
//		}
//
//		if(StringUtils.isNotBlank(error)){
//			req.getSession().setAttribute("error",error);
//			req.setAttribute("form",purchaseform);
//			new HTTPGetRedirection(req, res, "ShowPurchaseForConfirmation.do", null);
//			return null;
//		}

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

		// 非同期通信でPurchaseテーブルを更新
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

			PaymentGatewayConfiguration paymentGatewayConfiguration = new BankingPaymentGatewayConfiguration();

			GMOPaymentWrapper gmoPaymentWrapper = new GMOPaymentWrapper();

			// 取引情報
			paymentGatewayConfiguration.setShopId(purchaseform.getShopId());
			paymentGatewayConfiguration.setShopPass(purchaseform.getShopPass());
			paymentGatewayConfiguration.setOrderId(purchaseform.getOrderId());
			paymentGatewayConfiguration.setJobCd(purchaseform.getJobCd());
			paymentGatewayConfiguration.setItemCode(purchaseform.getItemCode());
			paymentGatewayConfiguration.setAmount(purchaseform.getAmount());
			paymentGatewayConfiguration.setTax(purchaseform.getTax());
			paymentGatewayConfiguration.setTdFlag(purchaseform.getTdFlag());
			paymentGatewayConfiguration.setTdTenantName(purchaseform.getTdTenantName());

			// 決済情報
			paymentGatewayConfiguration.setAccessId(purchaseform.getAccessId());
			paymentGatewayConfiguration.setAccessPass(purchaseform.getAccessPass());
			paymentGatewayConfiguration.setMethod(purchaseform.getMethod());
			paymentGatewayConfiguration.setPayTimes(purchaseform.getPayTimes());
			paymentGatewayConfiguration.setCardNo(purchaseform.getCardNo());
			paymentGatewayConfiguration.setExpire(purchaseform.getExpire());
			paymentGatewayConfiguration.setSecurityCode(purchaseform.getSecurityCode());
			paymentGatewayConfiguration.setClientField1(purchaseform.getClientField1());
			paymentGatewayConfiguration.setClientField2(purchaseform.getClientField2());
			paymentGatewayConfiguration.setClientField3(purchaseform.getClientField3());
			paymentGatewayConfiguration.setClientFieldFlag(purchaseform.getClientFieldFlag());
			paymentGatewayConfiguration.setHttpAccept(req.getHeader("ACCEPT"));
			paymentGatewayConfiguration.setHttpUserAgent(req.getHeader("USER-AGENT"));
			paymentGatewayConfiguration.setDeviceCategory(purchaseform.getDeviceCategory());
			paymentGatewayConfiguration.setSiteId(purchaseform.getSiteId());
			paymentGatewayConfiguration.setSitePass(purchaseform.getSitePass());
			paymentGatewayConfiguration.setMemberId(purchaseform.getMemberId());
			paymentGatewayConfiguration.setSeqMode(purchaseform.getSeqMode());
			paymentGatewayConfiguration.setCardSeq(purchaseform.getCardSeq());
			paymentGatewayConfiguration.setCardPass(purchaseform.getCardPass());
			paymentGatewayConfiguration.setTermUrl(purchaseform.getTermUrl());

			// 3Dセキュア認証後決済に必要な値
			paymentGatewayConfiguration.setPaRes(req.getParameter("PaRes"));
			paymentGatewayConfiguration.setMD(req.getParameter("MD"));

			// 決済実行
			gmoPaymentWrapper.executePayment(paymentGatewayConfiguration);

			// エラー情報取得
			List errList = paymentGatewayConfiguration.getErrList();

			// エラーコード（残高不足など）がある場合
			if (errList != null) {
				purchaseform.setAccessId(paymentGatewayConfiguration.getAccessId());
				purchaseform.setAccessPass(paymentGatewayConfiguration.getAccessId());
				// エラーページかリトライ用の画面に戻る?
				String[] errinfo = (String[])errList.get(0);
				throw new Exception(errinfo[0] + " " + errinfo[1]);
			}

			// ACS呼出判定が1(要)の場合（本人認証サービスの呼出が必要な場合）
			if (paymentGatewayConfiguration.getRedirectContents() != null) {
				// リダイレクト用ページに遷移(レスポンスにHTML書き出し)
				res.getWriter().write(paymentGatewayConfiguration.getRedirectContents());
				res.getWriter().flush();
				return null;
			}

//			HttpClient httpClient = new HttpClient();
//			PostMethod postMethod = new PostMethod(staticData.getCreditcardprocesurl());
//			postMethod.addParameter(new NameValuePair("contract_code","23715700"));
//			System.err.println("contract_code is " + 23715700);
//			postMethod.addParameter(new NameValuePair("contract_code","23278700"));　this is for akarui heya
//			postMethod.addParameter(new NameValuePair("user_id",purchase.getPublicUser().getId().toString()));
//			postMethod.addParameter(new NameValuePair("user_name", purchase.getPublicUser().getId().toString()));
//			postMethod.addParameter(new NameValuePair("user_name_kana", new String(purchase.getPublicUser().getKana().getBytes("EUC-JP"))));
//			postMethod.addParameter(new NameValuePair("user_mail_add",purchase.getPublicUser().getMail()));
//			postMethod.addParameter(new NameValuePair("item_code",purchase.getId().toString()));
//			postMethod.addParameter(new NameValuePair("item_name",purchase.getId().toString()));
//			postMethod.addParameter(new NameValuePair("order_number","id" + purchase.getId().toString() + "date" +  dateFormat.format(new Date())));
//			postMethod.addParameter(new NameValuePair("st_code",st_code));
//			postMethod.addParameter(new NameValuePair("mission_code","1"));
//			postMethod.addParameter(new NameValuePair("item_price",String.valueOf( purchase.getTotal()) ));
//			postMethod.addParameter(new NameValuePair("process_code","1"));
//			postMethod.addParameter(new NameValuePair("memo1","0"));
//			postMethod.addParameter(new NameValuePair("memo2","0"));
//			postMethod.addParameter(new NameValuePair("xml","1"));
//
//			httpClient.executeMethod(postMethod);
//
//			String response = postMethod.getResponseBodyAsString();
//			System.err.println(response);
//
//			SAXReader reader = new SAXReader();
//			StringReader stringReader = new StringReader(response);
//			Document document = reader.read(stringReader);
//			Element rootElement = document.getRootElement();
//			for (Iterator iterator = rootElement.elementIterator("result"); iterator.hasNext();) {
//				Element element = (Element) iterator.next();
//				if(element.attribute("redirect") !=null){
//					String redirecturl = URLDecoder.decode(element.attributeValue("redirect"));
//					new HTTPGetRedirection(req, res, redirecturl);
//					System.err.println("redirecting to epsilong");
//					return null;
//				}
//
//			}


//			 <Epsilon_result>
//			  <result result="1" />
//			  <result redirect="http%3A%2F%2Fbeta.epsilon.jp%2Fcgi-bin%2Forder%2Fmethod_select3.cgi%3Ftrans_code%3DSsY6rqJcVec34" />
//			  </Epsilon_result>


//			System.err.println(response);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 決済完了

		if (StringUtils.isNotBlank(req.getParameter("from"))
				&& !req.getParameter("from").equals("")) {
			System.err.println("mark 1");
			new HTTPGetRedirection(req, res, "PostPurchaseDetail.do", purchase
					.getId().toString(),"deliverymethod="+req.getParameter("deliverymethod"));
			return null;
		}

		System.err.println("mark 2, navigating to ForSettingNonTemp.");
		new HTTPGetRedirection(req, res, "PostPurchaseVPForSettingNonTemp.do",
				purchase.getId().toString(),"temp=0&deliverymethod="+req.getParameter("deliverymethod"));
		return null;
	}
}