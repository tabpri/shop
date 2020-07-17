package net.malta.web.app;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.malta.beans.PurchaseForm;
import net.malta.beans.mapper.PurchaseFormMapper;
import net.malta.error.Errors;
import net.malta.model.PurchaseInfo;
import net.malta.model.payment.PaymentInfo;
import net.malta.model.payment.RequestInfo;
import net.malta.model.validator.ValidationException;
import net.malta.service.payment.IPaymentService;
import net.malta.service.payment.PaymentException;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.SessionData;

// this action is responsible for payment entry for both acs and non-acs transactions
public class PostPurchaseVPChoosingPaymentMethodAction extends PostPurchaseVPPaymentBaseAction {
	
	private static final String USER_AGENT_HEADER = "USER-AGENT";

	private static final String ACCEPT_HEADER = "ACCEPT";

	private static final String APPLICATION_HTML = "application/html; charset=utf-8";
	
	static final Logger logger = LoggerFactory.getLogger(PostPurchaseVPChoosingPaymentMethodAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		PurchaseForm purchaseform = (PurchaseForm) form;

		paymentEntryExecute(req, res, purchaseform);
		
		return null;
	}

	private void paymentEntryExecute(HttpServletRequest req, HttpServletResponse res, PurchaseForm purchaseform) throws IOException {
		
		ServletContext context = this.getServlet().getServletContext();
		IPaymentService paymentService = (IPaymentService) BeanUtil.getBean("paymentService", context);				

		PurchaseFormMapper formMapper = (PurchaseFormMapper) BeanUtil.getBean("purchaseFormMapper", 
				context);
		
		PaymentInfo paymentInfo = formMapper.map(purchaseform, new PaymentInfo());
		RequestInfo requestDetails = new RequestInfo(acsURL(req),				
				req.getHeader(ACCEPT_HEADER),
				req.getHeader(USER_AGENT_HEADER));
		
		String redirectContents = null;
		
		PurchaseInfo sessionPuchaseInfo = SessionData.getInstance(context).getSessionPuchaseInfo(req);
		try {
			Integer purchaseId = sessionPuchaseInfo.getPurchaseId();
			logger.info("executing the paymentService.paymentRequest");
			redirectContents = paymentService.paymentRequest(purchaseId, paymentInfo, requestDetails);
			// ACS呼出判定が1(要)の場合（本人認証サービスの呼出が必要な場合）
			if ( redirectContents != null ) {
				// リダイレクト用ページに遷移(レスポンスにHTML書き出し)
				res.setContentType(APPLICATION_HTML);
				res.getWriter().write(redirectContents);
				res.getWriter().flush();
				return;
			} else {
				createNewTempPurchase(req, res);
			}
		} catch(ValidationException ve) {
			Errors errors = ve.getErrors();
			if ( errors != null && errors.hasErrors() ) {
				JSONResponseUtil.sendErrorJSON(res, errors);
				logger.info("executing the paymentService.paymentRequest validation errors ");
			}
		}catch(PaymentException paymentException) {
			Errors errors = paymentException.getErrors();
			if ( errors != null && errors.hasErrors() ) {
				JSONResponseUtil.sendErrorJSON(res, errors);
				logger.info("executing the paymentService.paymentRequest payment errors ");				
			}
		}
	}

	private String acsURL(HttpServletRequest req) {
		
		String reqURL = req.getRequestURL().toString();
		String reqURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		int uriIndex = reqURL.indexOf(reqURI);
		String requestPath = reqURL.subSequence(0, uriIndex).toString();
		
		String acsURL = requestPath + contextPath + "/PostPurchaseVPPaymentAcsConfirm.do?secual-auth-token="+req.getHeader("secual-auth-token");
		logger.info("acsURL:" + acsURL);
		return acsURL;
	}

}