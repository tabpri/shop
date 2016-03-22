package net.malta.dao.user;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import net.malta.dao.BaseDAO;
import net.malta.model.AppFunction;
import net.malta.model.Role;
import net.malta.model.User;

@Component
public class UserDAO extends BaseDAO<User> {

	public Set<Role> getRoles(Integer userId) {
		//String query = "SELECT role FROM USER user,ROLE role,USERROLE userrole ";
		User user = find(userId);
		if ( user != null ){
			return user.getRoles();			
		}
		return null;
	}

	public List<AppFunction> getAppFunctions(Integer userId) {
		String queryString = "SELECT distinct appFunction FROM User user,UserRole userRole," +
				" AppFunction appFunction, RoleAppFunction roleAppFunction where user.id = ? and user.id = userRole.user "
				+ " and userRole.role = roleAppFunction.role";
		Query query = this.getSessionFactory().getCurrentSession().createQuery(queryString);
		query.setParameter(0, userId);
		return query.list() ;
	}

	public User getUserByEmail(String email) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		return (User) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getActiveUsers() {
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("removed", false));
		return (List<User>) criteria.list();
	}
}