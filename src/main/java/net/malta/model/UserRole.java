package net.malta.model;

import java.io.Serializable;

public class UserRole implements Serializable {

	private static final long serialVersionUID = -2793262966758432703L;

	private Integer id;
	
	private Integer user;
	
	private Integer role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
		
}
