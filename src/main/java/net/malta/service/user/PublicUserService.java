package net.malta.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.enclosing.util.StringFullfiller;
import net.malta.dao.user.PublicUserDAO;
import net.malta.dao.user.PublicUserSessionDAO;
import net.malta.error.Errors;
import net.malta.model.Prefecture;
import net.malta.model.PublicUser;
import net.malta.model.PublicUserImpl;
import net.malta.model.PublicUserSession;
import net.malta.model.user.validator.PublicUserValidator;
import net.malta.service.meta.IPrefectureService;

@Service
public class PublicUserService implements IPublicUserService {

	@Autowired
	private IPrefectureService prefectureService;
	
	@Autowired
	private PublicUserDAO publicUserDAO;
	
	@Autowired
	private PublicUserValidator validator;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public PublicUser getUser(Integer id) {
		return publicUserDAO.find(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public PublicUser getUserByAuthUser(Integer authUserId) {
		return publicUserDAO.findUserByAuthUser(authUserId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public PublicUser getUserByEmail(String email) {
		return publicUserDAO.findUserByEmail(email);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)		
	public PublicUser createUser(PublicUser publicUser) {
		return publicUserUpdate(publicUser);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)		
	public PublicUser updateUser(PublicUser publicUser) {
		return publicUserUpdate(publicUser);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PublicUser createTempUser() {
		PublicUser publicUser = new PublicUserImpl();
		StringFullfiller.fullfil(publicUser);
		publicUser.setTemp(true);
		publicUserDAO.saveOrUpdate((PublicUserImpl) publicUser);
		return publicUser;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PublicUser updateAuthUser(Integer id,Integer authuserid) {
		PublicUserImpl publicUser = publicUserDAO.find(id);
		publicUser.setAuthuserid(authuserid);
		publicUserDAO.saveOrUpdate((PublicUserImpl) publicUser);
		return publicUser;
	}

	private PublicUser publicUserUpdate(PublicUser publicUser) {
		validator.validate(publicUser, new Errors());		
		StringFullfiller.fullfil(publicUser);		
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