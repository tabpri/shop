package com.getsecual.auth;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.getsecual.auth.client.UserAuthApiTokens;
import com.getsecual.auth.client.UserAuthServiceClient;
import com.getsecual.auth.client.exception.AuthenticationException;
import com.getsecual.auth.client.model.json.AuthenticationUserResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource(value="authapi.properties")
@ContextConfiguration({"classpath:applicationContext-localDataSource.xml","classpath:applicationContext.xml"})
public class UserAuthServiceClientTest {

	@Autowired
	UserAuthServiceClient authApiClient;
	
	@Test
	public void testValidateToken() {
		UserAuthApiTokens tokens = new UserAuthApiTokens("wz0tlyAuBdLMwJv5kio4PQ", 
				"1mAkoMdkWJbjkDEp6orDuw", "user1@secual.com");		
		try {
			AuthenticationUserResponse response = authApiClient.validateToken(tokens);
			assertNotNull(response.getId());
			System.out.println("auth user id " + response.getId());
		} catch (AuthenticationException e) {
			System.out.println("Exception occurred with the status " + e.getStatusCode());
			e.printStackTrace();
			fail();
		}
	}
}
