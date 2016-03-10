package net.malta.service.user;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.model.PublicUserSession;

public interface IPublicUserSessionService {

	PublicUserSession createSession(Integer userId, Integer purchaseId);

	PublicUserSession getSession(String sessionToken);

}