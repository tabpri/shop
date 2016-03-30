package com.getsecual.email.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailAPICredentials {

	@Value("${SENDGRIDEMAIL_APIKEY}")
	private String apiKey;

	private String userName;
	private String password;

	public EmailAPICredentials() {
	}

	public EmailAPICredentials(@Value("${SENDGRIDEMAIL_APIKEY}") String apiKey) {
		this.apiKey = apiKey;
	}

	public EmailAPICredentials(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String setApiKey(String apiKey) {
		return apiKey;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
}
