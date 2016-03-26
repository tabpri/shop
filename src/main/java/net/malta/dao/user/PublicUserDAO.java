package net.malta.dao.user;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import net.malta.dao.BaseDAO;
import net.malta.model.PublicUser;
import net.malta.model.PublicUserImpl;

@Component
public class PublicUserDAO extends BaseDAO<PublicUserImpl>{

	public PublicUser findUserByAuthUser(Integer authUserId) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(PublicUser.class);		
		criteria.add(Restrictions.eq("authuserid", authUserId));
		return (PublicUser)criteria.uniqueResult();
	}

	public PublicUser findUserByEmail(String email) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(PublicUser.class);		
		criteria.add(Restrictions.eq("mail", email));
		criteria.add(Restrictions.eq("temp", false));		
		return (PublicUser)criteria.uniqueResult();
	}

}
