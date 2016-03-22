package net.malta.model;

import java.io.Serializable;

public class RoleAppFunction implements Serializable {

	private static final long serialVersionUID = -5102798030762051757L;

	private Integer id;
	
	private Integer role;
	
	private Integer appFunction;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getAppFunction() {
		return appFunction;
	}

	public void setAppFunction(Integer appFunction) {
		this.appFunction = appFunction;
	}	
		
}