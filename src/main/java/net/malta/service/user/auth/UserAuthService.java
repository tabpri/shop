package net.malta.service.user.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.model.User;
import net.malta.service.user.IUserService;
import net.malta.service.user.auth.exception.AuthenticationException;

@Component
public class UserAuthService implements IUserAuthService {
	
	@Autowired
	IUserService userService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public User authenticateUser(User authUser) throws AuthenticationException {
		User user = userService.getUserByEmail(authUser.getEmail());
		if ( user == null ) {
			throw new AuthenticationException();			
		} else {
			//TODO: need to implement password decryption
			if ( !user.isRemoved() && user.getPassword().equals(authUser.getPassword())) {
				return user;
			} else {
				throw new AuthenticationException();
			}
		}
	}
}
