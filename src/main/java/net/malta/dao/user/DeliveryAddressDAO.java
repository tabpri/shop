package net.malta.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import net.malta.dao.BaseDAO;
import net.malta.model.DeliveryAddress;
import net.malta.model.DeliveryAddressImpl;

public class DeliveryAddressDAO extends BaseDAO<DeliveryAddressImpl>{

	@SuppressWarnings("unchecked")
	public List<DeliveryAddress> getDeliveryAddresses(Integer userId) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(DeliveryAddress.class);		
		criteria.add(Restrictions.eq("publicUser.id", userId));
		return (List<DeliveryAddress>) criteria.list();
	}
}
