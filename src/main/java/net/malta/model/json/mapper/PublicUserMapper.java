package net.malta.model.json.mapper;

import org.apache.commons.beanutils.BeanUtils;

import net.malta.mapper.IMapper;
import net.malta.model.PublicUser;

public class PublicUserMapper implements IMapper<PublicUser, net.malta.model.user.json.PublicUser>{

	@Override
	public net.malta.model.user.json.PublicUser map(PublicUser publicUser, net.malta.model.user.json.PublicUser publicUserJSON) {
		
		try {
			BeanUtils.copyProperties(publicUserJSON, publicUser);
			publicUserJSON.setZip(publicUser.getZipthree());
			publicUserJSON.setPrefecture(publicUser.getPrefecture().getId());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}		
		return publicUserJSON;
	}

}
