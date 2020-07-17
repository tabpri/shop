package net.malta.beans.mapper;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import net.malta.beans.UserForm;
import net.malta.mapper.IMapper;
import net.malta.model.User;

@Component
public class UserToUserFormMapper implements IMapper<User, UserForm>{

	@Override
	public UserForm map(User user, UserForm userForm) {
		try {
			BeanUtils.copyProperties(userForm, user);
		} catch (Exception e) {
			return null;
		} 
		return userForm;
	}

}
