package net.malta.web.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.Session;

import net.malta.model.PaymentMethod;
import net.malta.model.json.mapper.PaymentMethodsMapper;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.HibernateUtil;
import net.malta.web.utils.JSONResponseUtil;


public class PaymentMethodsAction extends Action{
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{

		Session session = null;		
		try {
			session = HibernateUtil.getCurrentSession(this);			
			Criteria criteria = session.createCriteria(PaymentMethod.class);
			List<PaymentMethod> paymentMethods = criteria.list();
			
			PaymentMethodsMapper paymentMethodsMapper = BeanUtil.getPaymentMethodsMapper(this.getServlet().getServletContext());
			List<net.malta.model.json.PaymentMethod> paymentMethodsJson = paymentMethodsMapper.map(paymentMethods, new ArrayList<net.malta.model.json.PaymentMethod>());
			JSONResponseUtil.writeResponseAsJSON(res, paymentMethodsJson);			
		} finally {
			HibernateUtil.closeSession(session);
		}
		return null;
	}
	
	
}