package net.malta.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.user.PublicUserSessionDAO;
import net.malta.model.PublicUserSession;

@Service
public class PublicUserSessionService implements IPublicUserSessionService {

	@Autowired
	SessionTokenGenerator sessionTokenGenerator;
	
	@Autowired
	PublicUserSessionDAO sessionTokenDAO;
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PublicUserSession createSession(Integer userId,Integer purchaseId) {
		String token = sessionTokenGenerator.generateToken(userId);
		PublicUserSession publicUserSessionToken = new PublicUserSession();
		publicUserSessionToken.setPublicUser(userId);
		publicUserSessionToken.setSessionToken(token);
		publicUserSessionToken.setPurchase(purchaseId);
		sessionTokenDAO.saveOrUpdate(publicUserSessionToken);
		return publicUserSessionToken;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public PublicUserSession getSession(String sessionToken) {
		return sessionTokenDAO.getUserSession(sessionToken);
	}
	
}
