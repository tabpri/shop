package net.malta.dao.user;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import net.malta.dao.BaseDAO;
import net.malta.model.PublicUserSession;

@Component
public class PublicUserSessionDAO extends BaseDAO<PublicUserSession>{

	public PublicUserSession getUserSession(String sessionToken) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().
				createCriteria(PublicUserSession.class);		
		criteria.add(Restrictions.eq("sessionToken", sessionToken));
		return (PublicUserSession) criteria.uniqueResult();		
	}
}
