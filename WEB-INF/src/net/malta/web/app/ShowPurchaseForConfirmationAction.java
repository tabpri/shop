package net.malta.web.app;

import net.enclosing.util.HTTPGetRedirection;
import net.enclosing.util.HibernateSession;
import net.malta.model.*;
import net.malta.web.utils.JsonUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ShowPurchaseForConfirmationAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		Session session = new HibernateSession().currentSession(this
				.getServlet().getServletContext());
		
		Purchase purchase = (Purchase)req.getSession().getAttribute("purchase");
		
		if(req.getSession().getAttribute("deliverymethod")!=null &&
				((Integer)req.getSession().getAttribute("deliverymethod")).intValue() == 3){
			String error = "";
			boolean flag = true;
			for (Iterator iter = purchase.getChoises().iterator(); iter.hasNext();) {
				Choise choise = (Choise) iter.next();
				int totalofchoise = 0;
				for (Iterator iterator = choise.getDeliveryAddressChoises().iterator(); iterator.hasNext();) {
					DeliveryAddressChoise deliveryAddressChoise = (DeliveryAddressChoise) iterator.next();
					totalofchoise+=deliveryAddressChoise.getOrdernum();
				}
				if(totalofchoise < choise.getOrdernum()){
					flag = false;
					break;
				}
			}
			if(!flag){
				new HTTPGetRedirection(req, res, "DeliveryAddressChoiseList.do", null,"notfixed=true");
				return null;
			}

		}
		
		
		

		Criteria criteria = session.createCriteria(Purchase.class);

		Map<String, Object> mapJson = new HashMap<String, Object>();
		
		System.err.println("----------------------------------------------------------------- mark 1000000000000000");
		Criteria criteriadeliveryaddress=session.createCriteria(DeliveryAddress.class);
		criteriadeliveryaddress.add(Restrictions.eq("publicUser",(PublicUser)req.getSession().getAttribute("u")));
		/*criteriadeliveryaddress.add(Restrictions.eq("publicUser",purchase.getPublicUser()));*/
		if(req.getSession().getAttribute("deliverymethod")!=null){
			Integer deliverymethodInteger = (Integer)req.getSession().getAttribute("deliverymethod");
			//req.getSession().setAttribute("deliverymethod", deliverymethodInteger);
			mapJson.put("deliveryMethod", deliverymethodInteger);
			if(deliverymethodInteger.intValue()==2){
				criteriadeliveryaddress.setMaxResults(1);
				criteriadeliveryaddress.addOrder(Order.desc("id"));
				req.setAttribute("deliveryAddress", criteriadeliveryaddress.list());
			}else{
				Map<Integer, DeliveryAddress> map = new TreeMap<Integer, DeliveryAddress>();
				for (Iterator iterator = purchase.getChoises().iterator(); iterator.hasNext();) {
					Choise choise = (Choise) iterator.next();
					for (Iterator iterator2 = choise.getDeliveryAddressChoises().iterator(); iterator2
							.hasNext();) {
						DeliveryAddressChoise deliveryAddressChoise = (DeliveryAddressChoise) iterator2.next();
						if(map.get(deliveryAddressChoise.getDeliveryAddress().getId())==null){
							map.put(deliveryAddressChoise.getDeliveryAddress().getId(),deliveryAddressChoise.getDeliveryAddress());
						}
					}
				}
				System.err.println("here --------------------------");
				//req.setAttribute("deliveryAddress", map.values());
				//res.getWriter().print(JsonUtil.INSTANCE.toJson(map.values()));
			}
		}
		
		Criteria criteriaPaymentMethod = session.createCriteria(PaymentMethod.class);
		//req.setAttribute("PaymentMethods", criteriaPaymentMethod.list());

		mapJson.put("paymentMethods", criteriaPaymentMethod.list());

		res.setContentType("application/json");

		res.getWriter().print(JsonUtil.INSTANCE.toJson(mapJson));

		return null;
		//return mapping.findForward("success");
	}
}