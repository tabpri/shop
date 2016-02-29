package net.malta.dao.payment;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.malta.dao.BaseDAO;
import net.malta.model.PaymentStatus;

@Repository
public class PaymentStatusDAO extends BaseDAO<PaymentStatus>{

	public PaymentStatus getPaymentStatus(Integer purchaseId) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(PaymentStatus.class);		
		criteria.add(Restrictions.eq("purchaseId", purchaseId));
		return (PaymentStatus) criteria.uniqueResult();
	}
}