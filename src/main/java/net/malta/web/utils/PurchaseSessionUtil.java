package net.malta.web.utils;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import net.malta.model.Purchase;

public class PurchaseSessionUtil {

	public static Purchase getPurchaseFromSession(HttpSession httpSession,Session session) {
		Purchase purchaseInSession = (Purchase)httpSession.getAttribute("purchase");
		Purchase purchase = loadFromDatabase(session, purchaseInSession);
		return purchase;
	}

	private static Purchase loadFromDatabase(Session session, Purchase purchaseInSession) {
		Criteria criteria = session.createCriteria(Purchase.class);
		criteria.add(Restrictions.idEq(purchaseInSession.getId()));
		Purchase purchase = (Purchase) criteria.uniqueResult();
		return purchase;
	}
}
