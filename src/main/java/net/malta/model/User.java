package net.malta.model;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class User implements Serializable {
	
	private static final long serialVersionUID = 6329887363824896342L;
	
	private Integer id;
	
	@NotBlank(message="USER.NAMEISBLANK")
	private String name;
	
	@NotBlank(message="USER.EMAILISBLANK")
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
	        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
	        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	             message="USER.INVALIDEMAIL")
	private String email;
	
	@NotBlank(message="USER.PASSWORDISBLANK")
	private String password;

	private IAuditInfo auditInfo;
	
	private Set<Role> roles;
	
	private boolean removed;

	public User() {		
	}
	
	public User(String email,String password) {
		this.email = email;
		this.password = password;
	}
	
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

	public IAuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(IAuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}	
}