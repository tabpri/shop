package net.malta.service.user;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class SessionTokenGenerator {
	
	public String generateToken(Integer userId) {		
		return UUID.randomUUID().toString();
	}
	
}
