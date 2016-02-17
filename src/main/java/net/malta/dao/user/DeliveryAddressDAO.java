package net.malta.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.malta.dao.BaseDAO;
import net.malta.model.DeliveryAddress;

public class DeliveryAddressDAO extends BaseDAO<DeliveryAddress>{

	@SuppressWarnings("unchecked")
	public List<DeliveryAddress> getDeliveryAddresses(Integer userId) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(DeliveryAddress.class);		
		criteria.add(Restrictions.eq("publicUser.id", userId));
		return (List<DeliveryAddress>) criteria.list();
	}
	
	public DeliveryAddress getDeliveryAddress(Integer userId,Integer id) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(DeliveryAddress.class);
		criteria.add(Restrictions.eq("id", id));		
		criteria.add(Restrictions.eq("publicUser.id", userId));
		return (DeliveryAddress) criteria.uniqueResult();		
	}	
}
