package net.malta.web.app.admin.purchase;

import static net.malta.web.utils.ActionUtil.getModulePrefix;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import net.enclosing.util.HTTPGetRedirection;
import net.malta.beans.PaymentStatusForm;
import net.malta.model.PaymentStatus;
import net.malta.model.payment.PaymentStatusEnum;
import net.malta.service.purchase.IPurchaseService;
import net.malta.web.utils.BeanUtil;

public class UpdatePaymentStatusAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PaymentStatusForm paymentStatusForm = (PaymentStatusForm) form;
		
		PaymentStatus paymentStatus = new PaymentStatus();
		paymentStatus.setPaymentStatus(PaymentStatusEnum.COMPLETED);
		paymentStatus.setTransactionReference(paymentStatusForm.getTransactionReference());
		paymentStatus.setTransactionDate(paymentStatusForm.getTransactionDate());
		
		IPurchaseService purchaseService =  (IPurchaseService) BeanUtil.getBean("purchaseService", 
				this.getServlet().getServletContext());
		purchaseService.updatePaymentStatus(paymentStatusForm.getPurchaseId(), paymentStatus);
		
		new HTTPGetRedirection(request, response, getModulePrefix(request)+"Purchases.do", null);
		
		return null;
	}

}
