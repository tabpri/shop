package net.malta.service.user;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.user.PublicUserDAO;
import net.malta.error.Errors;
import net.malta.model.Prefecture;
import net.malta.model.PublicUser;
import net.malta.model.PublicUserImpl;
import net.malta.model.user.validator.PublicUserValidator;
import net.malta.service.meta.IPrefectureService;

public class PublicUserService implements IPublicUserService {

	private IPrefectureService prefectureService;
	
	private PublicUserDAO publicUserDAO;
		
	private PublicUserValidator validator;
	
	@Override
	public PublicUser getUser(Integer id) {
		return publicUserDAO.find(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)		
	public PublicUser createUser(PublicUser publicUser) {
		return publicUserUpdate(publicUser);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)		
	public PublicUser updateUser(PublicUser publicUser) {
		validator.validate(publicUser, new Errors());
		return publicUserUpdate(publicUser);
	}

	private PublicUser publicUserUpdate(PublicUser publicUser) {
		if ( publicUser.getPrefecture() != null ){
			Prefecture prefecture = prefectureService.getPrefecture(publicUser.getPrefecture().getId());
			publicUser.setPrefecture(prefecture);			
		}
		return publicUserDAO.saveOrUpdate((PublicUserImpl) publicUser);
	}
	
	public void setValidator(PublicUserValidator validator) {
		this.validator = validator;
	}
	
	public void setPrefectureService(IPrefectureService prefectureService) {
		this.prefectureService = prefectureService;
	}

	public void setPublicUserDAO(PublicUserDAO publicUserDAO) {
		this.publicUserDAO = publicUserDAO;
	}	
}