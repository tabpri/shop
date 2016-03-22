package net.malta.model.user.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import net.malta.error.Errors;
import net.malta.error.IError;
import net.malta.model.User;
import net.malta.model.validator.ValidationException;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration({"classpath:applicationContext-localDataSource.xml","classpath:applicationContext.xml"})
@Transactional
public class UserValidatorTest {

	@Test
	public void testUserValidationsWithErrors() {
		UserValidator validator = new UserValidator();
		User user=new User();
		try {
			validator.validate(user, new Errors());			
		} catch(ValidationException ve) {
			assertNotNull(ve.getErrors());
			printErrors(ve.getErrors());			
			assertTrue(ve.getErrors().size() == 3);
			return;
		}
		fail();
	}

	private void printErrors(Errors errors) {

		for (IError error : errors) {
			System.out.println(error.getErrorCode() + " " + error.getValues());
		}
	}
}