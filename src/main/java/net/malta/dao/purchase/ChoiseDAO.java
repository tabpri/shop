package net.malta.dao.purchase;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.malta.dao.BaseDAO;
import net.malta.model.Choise;
import net.malta.model.ChoiseImpl;

public class ChoiseDAO extends BaseDAO<ChoiseImpl>{

	public Choise getChoise(Integer purchaseId, Integer choiseId) {		
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Choise.class);		
		criteria.add(Restrictions.idEq(choiseId));
		criteria.add(Restrictions.eq("purchase.id", purchaseId));
		return (Choise) criteria.uniqueResult();	
	}

}
