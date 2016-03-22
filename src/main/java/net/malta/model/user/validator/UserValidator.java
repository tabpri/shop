package net.malta.model.user.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.malta.error.Errors;
import net.malta.error.ValidationError;
import net.malta.model.User;
import net.malta.model.validator.IValidator;
import net.malta.model.validator.ValidationException;
import net.malta.service.user.IUserService;

@Component
public class UserValidator implements IValidator<User>{
	
	@Override
	public void validate(User user, Errors errors) throws ValidationException {		
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<User>> violations = validator.validate(user, new Class[0]);
		for (ConstraintViolation<User> violation : violations) {
			errors.add(new ValidationError(violation.getMessage(), user.getId()));
		}
		if ( errors.hasErrors() ){
			throw new ValidationException(errors);
		}
	}
}