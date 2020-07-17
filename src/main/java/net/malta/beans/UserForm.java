package net.malta.beans;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorActionForm;

public class UserForm extends ValidatorActionForm {

	private static final long serialVersionUID = -7408899387174195910L;

	private Integer id;
	private String name;
	private String email;
	private String password;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}