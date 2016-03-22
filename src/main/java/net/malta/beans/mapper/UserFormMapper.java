package net.malta.beans.mapper;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import net.malta.beans.UserForm;
import net.malta.mapper.IMapper;
import net.malta.model.User;

@Component
public class UserFormMapper implements IMapper<UserForm, User>{

	@Override
	public User map(UserForm userForm, User user) {
		try {
			BeanUtils.copyProperties(user, userForm);
			if ( user.getId().equals(0) ) {
				user.setId(null);
			}
		} catch (Exception e) {
			return null;
		} 
		return user;
	}

}
