package net.malta.service.user;

import net.malta.model.PublicUser;

public interface IPublicUserService {

	public PublicUser getUser(Integer id);

	public PublicUser createUser(PublicUser publicUser);

	public PublicUser updateUser(PublicUser publicUser);

	public PublicUser getUserByAuthUser(Integer authUserId);

}