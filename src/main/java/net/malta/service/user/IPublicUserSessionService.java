package net.malta.service.user;

import net.malta.model.PublicUserSession;

public interface IPublicUserSessionService {

	PublicUserSession createSession(Integer userId, Integer purchaseId);

	PublicUserSession getSession(String sessionToken);

	PublicUserSession createSession(Integer userId, Integer purchaseId, String parentSessionToken);

	PublicUserSession checkUserSession(String sessionToken);

	PublicUserSession expireSession(String sessionToken);

}