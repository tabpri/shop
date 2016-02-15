/**
 * @author SB
 */
package net.malta.web.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import net.malta.model.PaymentMethod;
import net.malta.model.json.mapper.PaymentMethodsMapper;
import net.malta.service.payment.IPaymentService;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;

public class PaymentMethodsAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		IPaymentService paymentService = (IPaymentService) BeanUtil.getBean("paymentService",
				this.getServlet().getServletContext());

		List<PaymentMethod> paymentMethods = paymentService.getPaymentMethods();

		PaymentMethodsMapper paymentMethodsMapper = BeanUtil
				.getPaymentMethodsMapper(this.getServlet().getServletContext());
		
		List<net.malta.model.payment.json.PaymentMethod> paymentMethodsJson = paymentMethodsMapper.map(paymentMethods,
				new ArrayList<net.malta.model.payment.json.PaymentMethod>());
		JSONResponseUtil.writeResponseAsJSON(res, paymentMethodsJson);

		return null;
	}	
}