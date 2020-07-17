package net.malta.service.user;

import java.util.List;
import java.util.Set;

import net.malta.model.AppFunction;
import net.malta.model.Role;
import net.malta.model.User;

public interface IUserService {

	public User createUser(User user);

	public User updateUser(User user);

	public User deleteUser(Integer userId);

	public User getUser(Integer userId);

	public User getUserByEmail(String email);

	public List<User> getUsers();

	public Set<Role> getRoles(Integer userId);

	public List<AppFunction> getAppFunctions(Integer userId);

}