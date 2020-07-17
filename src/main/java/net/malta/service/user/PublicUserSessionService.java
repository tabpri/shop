package net.malta.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.user.PublicUserSessionDAO;
import net.malta.error.ValidationError;
import net.malta.model.PublicUserSession;
import net.malta.model.validator.ValidationException;

@Service
public class PublicUserSessionService implements IPublicUserSessionService {

	@Autowired
	SessionTokenGenerator sessionTokenGenerator;
	
	@Autowired
	PublicUserSessionDAO sessionTokenDAO;
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PublicUserSession createSession(Integer userId,Integer purchaseId) {
		return createSession(userId, purchaseId, null);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PublicUserSession createSession(Integer userId,Integer purchaseId,String parentSessionToken) {
		
		if ( parentSessionToken != null ) {
			PublicUserSession parentUserSession = sessionTokenDAO.getUserSession(parentSessionToken);
			parentUserSession.setExpired(true);
			sessionTokenDAO.saveOrUpdate(parentUserSession);
		}		
		String token = sessionTokenGenerator.generateToken(userId);
		PublicUserSession publicUserSessionToken = new PublicUserSession();
		publicUserSessionToken.setPublicUser(userId);
		publicUserSessionToken.setSessionToken(token);
		publicUserSessionToken.setPurchase(purchaseId);
		publicUserSessionToken.setParentSessionToken(parentSessionToken);
		sessionTokenDAO.saveOrUpdate(publicUserSessionToken);
		return publicUserSessionToken;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public PublicUserSession getSession(String sessionToken) {
		return sessionTokenDAO.getUserSession(sessionToken);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public PublicUserSession checkUserSession(String sessionToken) {
		
		PublicUserSession userSession = sessionTokenDAO.getUserSession(sessionToken);
		if ( userSession == null ) {
			throw new ValidationException(
					new ValidationError("PUBLICUSER.SESSION.DOESNOTEXIST", new Object[]{sessionToken}));			
		}
		if ( userSession.isExpired() ) {
			throw new ValidationException(
					new ValidationError("PUBLICUSER.SESSION.EXPIRED", new Object[]{sessionToken}));
		}
		return userSession;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public PublicUserSession expireSession(String sessionToken) {
		PublicUserSession userSession = sessionTokenDAO.getUserSession(sessionToken);
		userSession.setExpired(true);
		sessionTokenDAO.saveOrUpdate(userSession);
		return userSession;
	}
}
