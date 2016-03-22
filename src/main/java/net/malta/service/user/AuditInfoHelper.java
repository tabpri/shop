package net.malta.service.user;

import java.util.Date;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import net.malta.model.IAuditInfo;
import net.malta.model.User;

@Component
public class AuditInfoHelper {

	public void setAuditFields(IAuditInfo auditInfo) {
		User loggedInUser = getLoggedInUser();
		if ( auditInfo.getCreatedBy() == null ) { // audit fields for create
			auditInfo.setCreatedBy(loggedInUser.getId());
			auditInfo.setUpdatedBy(loggedInUser.getId());
			Date now = new Date();
			auditInfo.setCreatedDate(now);
			auditInfo.setUpdatedDate(now);	
		}
		else { // // audit fields for update
			Date now = new Date();		
			auditInfo.setUpdatedBy(loggedInUser.getId());
			auditInfo.setUpdatedDate(now);			
		}
}
	
	
	public User getLoggedInUser() {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
}
