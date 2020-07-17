package net.malta.service.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.user.RoleDAO;
import net.malta.dao.user.UserDAO;
import net.malta.error.Errors;
import net.malta.model.AppFunction;
import net.malta.model.AuditInfo;
import net.malta.model.IAuditInfo;
import net.malta.model.Role;
import net.malta.model.User;
import net.malta.model.user.validator.UserEmailExistsValidator;
import net.malta.model.user.validator.UserValidator;

@Component
public class UserService implements IUserService {

	private static final String DEFAULT_ROLE_NAME = "SHOPPING_CART_OPERATOR";

	@Autowired
	UserDAO userDAO;

	@Autowired
	RoleDAO roleDAO;
	
	@Autowired
	UserValidator userValidator;

	@Autowired
	UserEmailExistsValidator emailExistsValidator;

	@Autowired
	AuditInfoHelper auditInfoHelper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public User createUser(User user) {
		validateUser(user);		
		Set<Role> roles = user.getRoles();
		if ( roles == null || roles.isEmpty() ) {
			roles = new HashSet<Role>();
			roles.add(roleDAO.getRole(DEFAULT_ROLE_NAME));
		}
		user.setRoles(roles);
		IAuditInfo auditInfo = user.getAuditInfo();
		if ( auditInfo == null ) {
			user.setAuditInfo(new AuditInfo());
		}
		auditInfoHelper.setAuditFields(user.getAuditInfo());		
		return userUpdate(user);
	}

	private User userUpdate(User user) {
		return userDAO.saveOrUpdate(user);
	}

	private void validateUser(User user) {
		Errors errors = new Errors();
		userValidator.validate(user, errors);
		emailExistsValidator.validate(user, errors);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public User updateUser(User user) {
		validateUser(user);
		User existingUser = userDAO.find(user.getId());
		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		auditInfoHelper.setAuditFields(existingUser.getAuditInfo());		
		return userUpdate(existingUser);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public User deleteUser(Integer userId) {
		User user = userDAO.find(userId);
		user.setRemoved(true);
		return userDAO.saveOrUpdate(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public User getUser(Integer userId) {
		return userDAO.find(userId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public Set<Role> getRoles(Integer userId) {
		return userDAO.getRoles(userId);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public List<AppFunction> getAppFunctions(Integer userId) {
		return userDAO.getAppFunctions(userId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public List<User> getUsers() {
		return userDAO.getActiveUsers();		
	}

	@Override
	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}
}