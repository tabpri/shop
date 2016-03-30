package net.malta.service.user;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.*;
import net.malta.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({"classpath:applicationContext-localDataSource.xml","classpath:applicationContext.xml"})
@Transactional
@Ignore
public class UserServiceTest {

	@Autowired
	IUserService userService;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setName("testuser1");
		user.setEmail("testuser1@test.com");
		user.setPassword("test1234");		
		userService.createUser(user);
		assertNotNull(user.getId());
	}
	
/*	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setName("testuser1");
		user.setEmail("testuser1@test.com");
		user.setPassword("test1234");		
		userService.updateUser(user);
	}
*/
}
