package net.malta.web.validator;

public interface IValidator<T> {

	public Errors validate(T object,Errors errors);
	
}
