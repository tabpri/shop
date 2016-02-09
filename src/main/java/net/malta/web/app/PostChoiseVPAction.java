package net.malta.web.app;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import net.malta.beans.ChoiseForm;
import net.malta.beans.Errors;
import net.malta.beans.ValidationError;
import net.malta.model.Choise;
import net.malta.model.ChoiseImpl;
import net.malta.model.Item;
import net.malta.model.Purchase;
import net.malta.model.json.mapper.PurchaseChoiseMapper;
import net.malta.model.wrapper.ChoiseTotal;
import net.malta.model.wrapper.PurchaseTotal;
import net.malta.web.utils.BeanUtil;
import net.malta.web.utils.HibernateUtil;
import net.malta.web.utils.JSONResponseUtil;
import net.malta.web.utils.PurchaseSessionUtil;


public class PostChoiseVPAction extends Action{
	
	private static final String CHOISE_DOESNOTEXIST = "CHOISE.DOESNOTEXIST";

	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest req,
			HttpServletResponse res) throws Exception{

		Session session = null;

		try {
			ChoiseForm choiseform = (ChoiseForm) form;
			session = HibernateUtil.getCurrentSession(this);
			HttpSession httpSession = req.getSession(false);
			
			Purchase purchase = PurchaseSessionUtil.getPurchaseFromSession(httpSession, session);
			
			Choise choise = null;
			boolean newchoise = false;
			if( choiseform.getId() != null ){ // existing choise
				choise = existingChoiceCheck(session,choiseform,purchase);
				if ( choise == null ) { // not an existing choise with the combination of id, itemid, purchase id
					Errors errors = new Errors();
					errors.add(new ValidationError(CHOISE_DOESNOTEXIST,choiseform.getId(),choiseform.getItem(),purchase.getId()));					
					sendErrorJSON(res,errors);
					return null;
				}
			} else { // new choise
				newchoise = true;
			}
			
			// not implemented yet
			checkForStock(choiseform.getItem());

			if( newchoise ) {
				choise = createNewChoise(session,choiseform,purchase);
			} else {
				choise = updateChoise(session,choiseform,choise,purchase);
			}

			sendJSON(res, choise);			
		} finally {
			HibernateUtil.closeSession(session);
		}
		return null;
	}

	// need to be encapsulated in a separate class for reuse
	private void sendErrorJSON(HttpServletResponse res,Errors errors) throws IOException {
		res.setStatus(HttpServletResponse.SC_BAD_REQUEST);		
		JSONResponseUtil.writeResponseAsJSON(res, errors);
	}

	private void checkForStock(Integer itemId) {
		
	}

	private void sendJSON(HttpServletResponse res, Choise choise) throws IOException {
		PurchaseChoiseMapper mapper = BeanUtil.getPurchaseChoiseMapper(this.getServlet().getServletContext());
		net.malta.model.json.Choise choiseJSON = new net.malta.model.json.Choise();
		mapper.map(choise, choiseJSON);
		JSONResponseUtil.writeResponseAsJSON(res, choiseJSON);
	}

	private Choise existingChoiceCheck(Session session,ChoiseForm choiseform,Purchase purchase) throws Exception {
		
		Integer itemInt = choiseform.getItem();
		Integer choiseid = choiseform.getId();
		
		Choise choise = null;
		
		Criteria criteria = session.createCriteria(Choise.class);
		criteria.add(Restrictions.idEq(choiseid));
		criteria.add(Restrictions.eq("wp_posts_id", itemInt));
		criteria.add(Restrictions.eq("purchase", purchase));
		choise = (Choise) criteria.uniqueResult();			
		return choise;
	}

	private Choise updateChoise(Session session, ChoiseForm choiseform,Choise choise,Purchase purchase) {
		choise.setPurchase(purchase);
		setBasicAttributes(session,choiseform, choise);
		calcTotals(purchase, choise);		
		saveOrUpdate(session,purchase, choise);
		return choise;
	}

	@SuppressWarnings("unchecked")
	private Choise createNewChoise(Session session, ChoiseForm choiseform,Purchase purchase) {

		Choise choise = new ChoiseImpl();
		choise.setPurchase(purchase);		
		setBasicAttributes(session,choiseform, choise);
		setAttributesFromWPPosts(session, choise);		
		purchase.getChoises().add(choise);
		//totals - choise and purchase
		calcTotals(purchase, choise);
		saveOrUpdate(session,purchase, choise);
		return choise;
	}

	private void calcTotals(Purchase purchase, Choise choise) {
		new ChoiseTotal(choise).calcAndSetTotal();		
		new PurchaseTotal(purchase).calcAndSetTotal();
	}

	private void saveOrUpdate(Session session, Purchase purchase, Choise choise) {
		session.evict(purchase);
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(choise);
		session.saveOrUpdate(purchase);
		transaction.commit();
		session.flush();
	}

	private void setAttributesFromWPPosts(Session session, Choise choise) {
		Integer price = getPriceOf(choise.getWp_posts_id(), session);
		choise.setName(getNameOf(choise.getWp_posts_id(), session));
		choise.setImg(getImgOf(choise.getWp_posts_id(), session));;
		choise.getItem().setPricewithtax(price);
	}

	public Choise setBasicAttributes(Session session,ChoiseForm choiseform,Choise choise) {
		int ordernum = (choiseform.getOrdernum() == 0) ? 1 : choiseform.getOrdernum();		
		choise.setOrdernum(ordernum);
		choise.setWrapping(choiseform.isWrapping());
		choise.setVarietychoise(choiseform.getVarietychoise());
				
		Criteria criteriaItem = session.createCriteria(Item.class);
		criteriaItem.add(Restrictions.idEq(choiseform.getItem()));
		Item item = (Item) criteriaItem.uniqueResult();
		choise.setItem(item);
		choise.setWp_posts_id(item.getId());
		return choise;
	}
	
	private String getImgOf(int wp_posts_id, Session session) {
		 SQLQuery query = session.createSQLQuery("SELECT meta_value value FROM wp_postmeta where meta_key = 'product-thumbnail' and post_id = " + wp_posts_id);
		 query.addScalar( "value", Hibernate.STRING); 
		 String result = (String)query.uniqueResult();
		 System.err.println(result);
		 // TODO this sql should be changed to get the image url
		 String name =result.split("\\]")[1];
		 System.err.println(name);
		 return name;
	}
 
	private String getNameOf(int wp_posts_id, Session session) {
		 SQLQuery query = session.createSQLQuery("SELECT post_title value FROM wp_posts where ID = " + wp_posts_id);
		 query.addScalar( "value", Hibernate.STRING); 
 
		 String result = (String)query.uniqueResult();
		 System.err.println(result);
		 return result;
		 //TODO this sql should be changed to get the name of the product.
	}

	private int getPriceOf(int wp_posts_id,Session session) {
		 SQLQuery query = session.createSQLQuery("SELECT meta_value value FROM wp_postmeta where meta_key = 'rate' and post_id = " + wp_posts_id);
		 query.addScalar( "value", Hibernate.STRING); 
		  
		 
		 String result = (String)query.uniqueResult();
		 String rate = result.replaceAll("￥", "");
		 rate = rate.replaceAll("\\.", "");
		 rate= rate.trim();
		 System.err.println(rate);
		 return Integer.valueOf(rate);
		 
		 
		 //how to remove yen mark, and . mark in the middle.
		// TODO Auto-generated method stub 
		// here the codes to get the price of wp_post using wp_post_id
		 //now returning test price....
//		return 500;
	}
	
	
}