package net.malta.dao.payment;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import net.malta.dao.BaseDAO;
import net.malta.model.PaymentMethod;
import net.malta.model.PaymentMethodImpl;

@Repository
public class PaymentMethodDAO extends BaseDAO<PaymentMethodImpl> {

	@SuppressWarnings("unchecked")
	public List<PaymentMethod> getPaymentMethods() {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(PaymentMethod.class);
		return criteria.list();
	}
}
