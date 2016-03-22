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

import net.malta.error.Errors;
import net.malta.model.PaymentStatus;
import net.malta.model.PurchaseInfo;
import net.malta.model.payment.PaymentStatusEnum;
import net.malta.service.payment.ACSResponse;
import net.malta.service.payment.IPaymentService;
import net.malta.service.payment.PaymentException;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.SessionData;

public class PostPurchaseVPPaymentAcsConfirmAction extends PostPurchaseVPPaymentBaseAction {
	
	private static final Logger logger = LoggerFactory.getLogger(PostPurchaseVPPaymentAcsConfirmAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		ServletContext context = this.getServlet().getServletContext();
		IPaymentService paymentService = (IPaymentService) BeanUtil.getBean("paymentService", 
				context);
		
		PurchaseInfo sessionPuchaseInfo = SessionData.getInstance(context).getSessionPuchaseInfo(req);
		Integer purchaseId = sessionPuchaseInfo.getPurchaseId();
		PaymentStatus paymentStatus = paymentService.getPaymentStatus(purchaseId);
		
		if ( paymentStatus.getPaymentStatus().equals(PaymentStatusEnum.ACS_CONFIRM) ){
			
			// authentication screen call back
			// doing a secure tran
			paymentExecute(req, res);
		}
		return null;
	}

	private void paymentExecute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			ServletContext context = this.getServlet().getServletContext();			
			PurchaseInfo sessionPuchaseInfo = SessionData.getInstance(context).getSessionPuchaseInfo(req);
			IPaymentService paymentService = (IPaymentService) BeanUtil.getBean("paymentService", 
					context);				
			
			// 本人認証画面からの戻り値
			String MD = req.getParameter("MD");
			String PaRes = req.getParameter("PaRes");
			ACSResponse acsResponse = new ACSResponse(PaRes, MD);
			logger.info("executing the ACS payment");
			paymentService.executePayment(sessionPuchaseInfo.getPurchaseId(),acsResponse);					
		}
		catch(PaymentException paymentException) {
			logger.info("processing the payment errors");
			
			Errors errors = paymentException.getErrors();
			if ( errors != null && errors.hasErrors() ) {
				JSONResponseUtil.sendErrorJSON(res, errors);
				return; // return
			}
		}					
		createNewTempPurchase(req, res);
	}	
}