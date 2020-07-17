package net.malta.dao.user;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import net.malta.dao.BaseDAO;
import net.malta.model.Role;

@Component
public class RoleDAO extends BaseDAO<Role> {

	public Role getRole(String roleName) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(Role.class);
		criteria.add(Restrictions.eq("roleName", roleName));
		return (Role) criteria.uniqueResult();
	}
}