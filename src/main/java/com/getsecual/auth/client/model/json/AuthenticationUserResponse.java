package com.getsecual.auth.client.model.json;

import java.io.Serializable;
import java.util.Map;

public class AuthenticationUserResponse implements Serializable {

	private static final long serialVersionUID = 5650778232096488409L;

	private Map<Object,Object> data;

	private Integer id;	
	private String name;	
	private String email;
	
	private String accessToken;
	private String client;
	private String uid;
	
	public Integer getId() {
		return ((Double) this.data.get("id")).intValue();
	}

	
	public String getName() {
		return (String) this.data.get("name");
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return (String) this.data.get("email");
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}

}