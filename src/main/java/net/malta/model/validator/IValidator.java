package net.malta.model.validator;

import net.malta.error.Errors;

public interface IValidator<T> {

	public void validate(T object,Errors errors) throws ValidationException;
	
}
