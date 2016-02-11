package net.malta.model.json.mapper;

import org.apache.commons.beanutils.BeanUtils;

import net.malta.model.PublicUser;

public class PublicUserMapper implements IMapper<PublicUser, net.malta.model.json.PublicUser>{

	@Override
	public net.malta.model.json.PublicUser map(PublicUser publicUser, net.malta.model.json.PublicUser publicUserJSON) {
		
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
