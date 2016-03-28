package com.getsecual.auth.client;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.getsecual.auth.client.constant.AuthApiConstants;
import com.getsecual.auth.client.exception.AuthenticationException;
import com.getsecual.auth.client.model.json.AuthenticationUserRequest;
import com.getsecual.auth.client.model.json.AuthenticationUserResponse;

@Component
public class UserAuthServiceClient {

	@Value("${USERAUTHAPI_SIGNIN_URI}")
	private String AUTHAPI_SIGNIN_URI;

	@Value("${USERAUTHAPI_VALIDATETOKEN_URI}")
	private String AUTHAPI_VALIDATETOKEN_URI;

	@Value("${USERAUTHAPI_SIGNOUT_URI}")
	private String AUTHAPI_SIGNOUT_URI;

	public AuthenticationUserResponse authenticateUser(AuthenticationUserRequest authenticationRequest)
			throws AuthenticationException {
		try {
			URI uri = new URI(AUTHAPI_SIGNIN_URI);
			HttpEntity<AuthenticationUserRequest> authenticationRequestEntity = new HttpEntity<AuthenticationUserRequest>(
					authenticationRequest);
			RestTemplate restTemplate = newRestTemplate();
			ResponseEntity<AuthenticationUserResponse> response = restTemplate.exchange(uri, HttpMethod.POST,
					authenticationRequestEntity, AuthenticationUserResponse.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				AuthenticationUserResponse authenticationResponse = response.getBody();
				String accessToken = response.getHeaders().getFirst(AuthApiConstants.AUTHAPI_HEADER_ACCESS_TOKEN);
				authenticationResponse.setAccessToken(accessToken);
				String client = response.getHeaders().getFirst(AuthApiConstants.AUTHAPI_HEADER_CLIENT);
				authenticationResponse.setClient(client);
				String uid = response.getHeaders().getFirst(AuthApiConstants.AUTHAPI_HEADER_UID);
				authenticationResponse.setUid(uid);
				System.out.println(
						"UserAuthServiceClient: ----- " + accessToken + " ----- " + client + " --------- " + uid);
				return authenticationResponse;
			} else {
				throw new AuthenticationException(response.getStatusCode().value());
			}
		} catch (HttpClientErrorException hcee) {
			throw new AuthenticationException(hcee.getStatusCode().value());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AuthenticationException(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	public AuthenticationUserResponse validateToken(UserAuthApiTokens tokens) throws AuthenticationException {
		try {
			URI uri = new URI(AUTHAPI_VALIDATETOKEN_URI);
			RestTemplate restTemplate = newRestTemplate();
			// http entity with the token headers
			HttpHeaders headers = addHeaders(tokens);

			HttpEntity<String> entity = new HttpEntity<String>("", headers);

			ResponseEntity<AuthenticationUserResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
					entity, AuthenticationUserResponse.class);

			return responseEntity.getBody();
		} catch (HttpClientErrorException hcee) {
			throw new AuthenticationException(hcee.getStatusCode().value());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AuthenticationException(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	private HttpHeaders addHeaders(UserAuthApiTokens tokens) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(AuthApiConstants.AUTHAPI_HEADER_ACCESS_TOKEN, tokens.getAccessToken());
		headers.set(AuthApiConstants.AUTHAPI_HEADER_CLIENT, tokens.getClient());
		headers.set(AuthApiConstants.AUTHAPI_HEADER_UID, tokens.getUid());
		return headers;
	}

	public Boolean signout(UserAuthApiTokens tokens) throws AuthenticationException {
		try {
			RestTemplate restTemplate = newRestTemplate();
			HttpHeaders headers = addHeaders(tokens);
			HttpEntity<String> entity = new HttpEntity<String>("", headers);

			URI uri = new URI(AUTHAPI_SIGNOUT_URI);

			@SuppressWarnings("rawtypes")
			ResponseEntity<Map> responseEntity = restTemplate.exchange(uri, HttpMethod.DELETE,
					entity, Map.class);
			Map responseMap = responseEntity.getBody();
			return (Boolean) responseMap.get("success");
		} catch (HttpClientErrorException hcee) {
			throw new AuthenticationException(hcee.getStatusCode().value());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AuthenticationException(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

	}

	private RestTemplate newRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new GsonHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}

	public void setAUTHAPI_URI(String authAPIURI) {
		this.AUTHAPI_SIGNIN_URI = authAPIURI;
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