package net.malta.beans;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = -8612925873264449931L;

	private String email;
	private String password;
	
	
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
