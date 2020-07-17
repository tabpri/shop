package net.malta.web.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import net.enclosing.util.HTTPGetRedirection;
import net.enclosing.util.HibernateSession;
import net.malta.model.GiftCard;



public class DeleteGiftCardAction extends Action{
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{



		Session session = new HibernateSession().currentSession(this
				.getServlet().getServletContext());

		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(GiftCard.class);
		criteria.add(Restrictions.idEq(Integer.valueOf(req.getParameter("id"))));


		GiftCard giftCard = (GiftCard) criteria.uniqueResult();
		session.delete(giftCard);
		transaction.commit();
		session.flush();


		
				new HTTPGetRedirection(req, res, "GiftCards.do",null);
		return null;

	}
	
	
}