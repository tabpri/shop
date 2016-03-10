package net.malta.model;

import java.io.Serializable;

public class PublicUserSession implements Serializable {

	private static final long serialVersionUID = 557049155483019680L;

	private Integer id;
	
	private Integer publicUser;
	
	private Integer purchase;
	
	private String sessionToken;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPublicUser() {
		return publicUser;
	}

	public void setPublicUser(Integer publicUser) {
		this.publicUser = publicUser;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public Integer getPurchase() {
		return purchase;
	}

	public void setPurchase(Integer purchase) {
		this.purchase = purchase;
	}
	
}
