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
import org.hibernate.criterion.Restrictions;

import net.malta.model.DeliveryAddress;
import net.malta.model.PublicUser;
import net.malta.model.json.mapper.DeliveryAddressesMapper;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.DeliveryMethodUtils;
import net.malta.web.utils.HibernateUtil;
import net.malta.web.utils.JSONResponseUtil;

public class DeliveryAddressListAction extends Action {
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		DeliveryMethodUtils.setIntoSesssion(req);

		Session session = null;
		PublicUser pu = (PublicUser) req.getSession(false).getAttribute("u");

		try {
			session = HibernateUtil.getCurrentSession(this);
			Criteria criteria = session.createCriteria(DeliveryAddress.class);
			criteria.createCriteria("publicUser").add(Restrictions.eq("id", pu.getId()));
			List<DeliveryAddress> deliveryAddresses = criteria.list();
			DeliveryAddressesMapper mapper = BeanUtil.getDeliveryAddressesMapper(this.getServlet().getServletContext());
			List<net.malta.model.json.DeliveryAddress> deliveryAddressesJson = mapper.map(deliveryAddresses,
					new ArrayList<net.malta.model.json.DeliveryAddress>());
			JSONResponseUtil.writeResponseAsJSON(res, deliveryAddressesJson);
		} finally {
			HibernateUtil.closeSession(session);
		}
		return null;
	}

}