package com.getsecual.auth.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.getsecual.auth.client.exception.AuthenticationException;
import com.getsecual.auth.client.model.json.AuthenticationUserRequest;
import com.getsecual.auth.client.model.json.AuthenticationUserResponse;

@Component
public class UserAuthServiceClient {

	@Value("${USERAUTHAPI_URI}")
	private String AUTHAPI_URI;
	
	public AuthenticationUserResponse authenticateUser(AuthenticationUserRequest authenticationRequest) 
			throws AuthenticationException {
		try {
			URI uri = new URI(AUTHAPI_URI);
			HttpEntity<AuthenticationUserRequest> authenticationRequestEntity = 
					new HttpEntity<AuthenticationUserRequest>(authenticationRequest);			
			RestTemplate restTemplate = new RestTemplate();
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
			messageConverters.add(new GsonHttpMessageConverter());
			restTemplate.setMessageConverters(messageConverters);
			ResponseEntity<AuthenticationUserResponse> response = restTemplate.exchange(uri, 
					HttpMethod.POST, authenticationRequestEntity, AuthenticationUserResponse.class);
			if ( response.getStatusCode() == HttpStatus.OK ) {
				AuthenticationUserResponse authenticationResponse = response.getBody();
				String accessToken = response.getHeaders().getFirst("access-token");
				authenticationResponse.setAccessToken(accessToken);
				String client = response.getHeaders().getFirst("client");
				authenticationResponse.setClient(client);
				String uid = response.getHeaders().getFirst("uid");
				authenticationResponse.setUid(uid);
				System.out.println("UserAuthServiceClient: ----- " + accessToken + " ----- " + client + " --------- " + uid);
				return authenticationResponse;
			} else {
				throw new AuthenticationException(response.getStatusCode().value());
			}
		}catch (HttpClientErrorException hcee) {
			throw new AuthenticationException(hcee.getStatusCode().value());		
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new AuthenticationException(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}				
	}

	public void setAUTHAPI_URI(String authAPIURI) {
		this.AUTHAPI_URI = authAPIURI;
	}

	public static void main(String[] args) throws AuthenticationException {
		UserAuthServiceClient client = new UserAuthServiceClient();
		client.setAUTHAPI_URI("http://zxc.cz:5000/api/v1/auth/sign_in");
		AuthenticationUserRequest authenticationRequest = new AuthenticationUserRequest("user2@secual.com", "12345678");
		AuthenticationUserResponse authenticationUserResponse = client.authenticateUser(authenticationRequest);
		
		System.out.println(authenticationUserResponse.getAccessToken());
		System.out.println(authenticationUserResponse.getName());
		System.out.println(authenticationUserResponse.getEmail());
		System.out.println(authenticationUserResponse.getId());		
		
	}
}