package net.malta.web.model;

import java.io.Serializable;

public class PublicUserInfo implements Serializable {

	private static final long serialVersionUID = -6186279627519111181L;

	private Integer userId;

	private String name;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
