package net.malta.model.user.json;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 9084033276832464973L;
	
	private Integer id;
	private Integer authuserid;
	
	private String name;
	private String email;
	
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAuthuserid() {
		return authuserid;
	}
	public void setAuthuserid(Integer authuserid) {
		this.authuserid = authuserid;
	}	
}