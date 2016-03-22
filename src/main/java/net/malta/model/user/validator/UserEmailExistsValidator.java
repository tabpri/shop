package net.malta.model.user.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.malta.error.Errors;
import net.malta.error.ValidationError;
import net.malta.model.User;
import net.malta.model.validator.IValidator;
import net.malta.model.validator.ValidationException;
import net.malta.service.user.IUserService;

@Component
public class UserEmailExistsValidator implements IValidator<User>{

	@Autowired
	private IUserService userService; 

	@Override
	public void validate(User user, Errors errors) throws ValidationException {		
		String email = user.getEmail();
		boolean emailChanged = false;
		if ( user.getId() != null ) {
			User existingUser = userService.getUser(user.getId());
			emailChanged = !existingUser.getEmail().equals(user.getEmail());
		} else {
			emailChanged = true;
		}
		if ( emailChanged ) {
			User userByEmail = userService.getUserByEmail(email);
			if ( userByEmail != null ) {
				errors.add(new ValidationError("USER.EMAILEXISTS", user.getId()));			
			}			
		}
		if ( errors.hasErrors() ){
			throw new ValidationException(errors);
		}		
	}

}