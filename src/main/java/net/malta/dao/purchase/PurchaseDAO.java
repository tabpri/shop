/**
 * @author SB
 */
package net.malta.dao.purchase;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import net.malta.dao.BaseDAO;
import net.malta.model.Purchase;
import net.malta.model.PurchaseImpl;

@Component
public class PurchaseDAO extends BaseDAO<PurchaseImpl>{

	public Purchase getPurchase(Integer userId,boolean temp) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Purchase.class);		
		criteria.add(Restrictions.eq("publicUser.id", userId));
		criteria.add(Restrictions.eq("temp", true));
		return (Purchase) criteria.uniqueResult();			
	}
	
	@SuppressWarnings("unchecked")
	public List<Purchase> getPurchases(Integer userId) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Purchase.class);		
		criteria.add(Restrictions.eq("publicUser.id", userId));
		criteria.add(Restrictions.eq("temp", false));
		criteria.addOrder(Order.desc("id"));
		return (List<Purchase>) criteria.list();			
	}

}
