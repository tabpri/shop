package net.malta.web.app.admin.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;

import net.malta.model.Role;
import net.malta.model.User;
import net.malta.service.user.auth.IUserAuthService;

@Component
public class AdminUserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	IUserAuthService userAuthService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminUserAuthenticationProvider.class);
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
	     String name = authentication.getName();
	     String password = authentication.getCredentials().toString();

	     try {
    	 	logger.info("authenticating the user " + name);
    	 	System.out.println("AdminUserAuthenticationProvider - authenticating the user " + name);
	    	 
		     User authUser = userAuthService.authenticateUser(new User(name,password));
		     List<GrantedAuthority> authorities = createGrantedAuthorities(authUser);
            return new UsernamePasswordAuthenticationToken(authUser, password, authorities);
	     } catch(net.malta.service.user.auth.exception.AuthenticationException ae) {
	    	 	logger.error("authentication failed for the user " + name);
	    	 	throw new BadCredentialsException("User Name or Password doesnt match");
	     }
	 }

	private List<GrantedAuthority> createGrantedAuthorities(User authUser) {
		Set<Role> roles = authUser.getRoles();
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		for (Role role : roles) {
			grantedAuthorities.add(new GrantedAuthorityImpl(role.getRoleName()));
		}
		return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
