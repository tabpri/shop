package net.malta.dao.user;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import net.malta.model.AppFunction;
import net.malta.model.Role;
import net.malta.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({"classpath:applicationContext-localDataSource.xml","classpath:applicationContext.xml"})
@Transactional
public class UserDAOTest {

	@Autowired
	UserDAO userDAO;
	
	@Test
	public void testGetUserWithRoles() {
		Integer userId = 1;
		Set<Role> roles = userDAO.getRoles(userId);
		assertNotNull(roles);
		assertTrue("roles must not be empty",roles.size() > 0);
		printRoles(roles);
	}

	@Test
	public void testGetUserAppFunctions() {
		Integer userId = 1;
		List<AppFunction> appFunctions = userDAO.getAppFunctions(userId);
		assertNotNull(appFunctions);
		assertTrue("app functions must not be empty",appFunctions.size() > 0);		
		printAppFunctions(appFunctions);
	}

	@Test
	public void testGetUsers() {
		List<User> users = userDAO.findAll();
		assertNotNull(users);
		assertTrue("users must not be empty",users.size() > 0);
		printUsers(users);
	}

	private void printUsers(List<User> users) {
		for (User user : users) {
			System.out.println("user : " + user.getId() + " " + user.getName() + " " + user.getEmail());
		}
	}

	private void printAppFunctions(List<AppFunction> appFunctions) {
		for (AppFunction appFunction : appFunctions) {
			System.out.println(appFunction.getFunctionName() + " " + appFunction.getFunctionURI());
		}
	}

	private void printRoles(Set<Role> roles) {
		for (Role role : roles) {
			System.out.println("role name:" + role.getRoleName());
		}		
	}
}