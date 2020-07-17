package net.malta.service.user.auth;

import net.malta.model.User;
import net.malta.service.user.auth.exception.AuthenticationException;

public interface IUserAuthService {

	User authenticateUser(User authUser) throws AuthenticationException;

}