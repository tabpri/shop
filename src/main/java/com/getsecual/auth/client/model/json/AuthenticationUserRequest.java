package com.getsecual.auth.client.model.json;

import java.io.Serializable;

public class AuthenticationUserRequest implements Serializable {

	private static final long serialVersionUID = -4157943746737412780L;
	
	private String email;
	private String password;
	
	public AuthenticationUserRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
		
}
