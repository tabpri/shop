package net.malta.web.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.enclosing.util.HibernateSession;
import net.malta.model.Item;
import net.malta.model.Purchase;
import net.malta.model.Purchase;
import net.malta.model.PurchaseImpl;
import net.malta.model.json.mapper.PurchaseMapper;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.JSONResponseUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ShowPurchaseAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		Session session = new HibernateSession().currentSession(this
				.getServlet().getServletContext());
//
//		Purchase purchase = new PurchaseImpl();
//		
		Purchase purchase = (Purchase)req.getSession().getAttribute("purchase");
		if (req.getParameter("id") != null
				&& !req.getParameter("id").equals("")) {
			Criteria criteria = session.createCriteria(Purchase.class);
			criteria.add(Restrictions.idEq(Integer.valueOf(req
					.getParameter("id"))));
			purchase = (Purchase) criteria.uniqueResult();
		}

//}
		
//		Criteria criteriaitem=session.createCriteria(Item.class);
//		criteriaitem.createCriteria("choises").add(Restrictions.eq("purchase", purchase));
//		criteriaitem.addOrder(Order.desc("stocknum"));
//		req.setAttribute("items", criteriaitem.list());
		
//		System.out.println("ss"+criteriaitem.list());
		
//		req.setAttribute("purchase",purchase);

//		req.setAttribute("purchase", purchase);
//

		PurchaseMapper purchaseMapper = BeanUtil.getPurchaseMapper(this.getServlet().getServletContext());
		net.malta.model.json.Purchase purchaseJSON = purchaseMapper.map(purchase, new net.malta.model.json.Purchase());
		JSONResponseUtil.writeResponseAsJSON(res,purchaseJSON);
		
		return null;
	}
}