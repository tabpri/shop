package net.malta.model.user.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.malta.dao.meta.PrefectureDAO;
import net.malta.dao.user.PublicUserDAO;
import net.malta.error.Errors;
import net.malta.error.ValidationError;
import net.malta.model.Prefecture;
import net.malta.model.PublicUser;
import net.malta.model.validator.IValidator;
import net.malta.model.validator.ValidationException;
import net.malta.model.validator.constants.PublicUserConstants;

@Component
public class PublicUserValidator implements IValidator<PublicUser> {

	@Autowired
	private PrefectureDAO prefectureDAO;
	
	@Autowired
	private PublicUserDAO publicUserDAO;
	
	@Override
	public void validate(PublicUser user,Errors errors) {

		Object[] blank = new Object[0]; 
				
		String name = user.getName();
		if (StringUtils.isBlank(name)) {
			errors.add(new ValidationError(PublicUserConstants.USERNAME_ISBLANK, blank));
		}

/*		String kana = user.getKana();
		if (StringUtils.isBlank(kana)) {
			errors.add(new ValidationError(PublicUserConstants.KANA_ISBLANK, blank));
		}
*/
		int zip = user.getZipthree();

		if (zip == 0) {
			errors.add(new ValidationError(PublicUserConstants.ZIP_ISBLANK, blank));
		}

		Prefecture prefecture = user.getPrefecture();
		if (prefecture == null || prefecture.getId() == null || prefecture.getId().intValue() == 0) {
			errors.add(new ValidationError(PublicUserConstants.PREFECTURE_ISBLANK, blank));
		} else {
			Integer prefectureId = prefecture.getId();
			Prefecture prefectureReturned = prefectureDAO.find(prefectureId);
			if ( prefectureReturned == null ) {
				errors.add(new ValidationError(PublicUserConstants.PREFECTURE_NOTVALID, new Object[] {prefectureId}));
			}
		}

		String mail = user.getMail();
		if (StringUtils.isBlank(mail)) {
			errors.add(new ValidationError(PublicUserConstants.EMAIL_ISBLANK, blank));
		}

		String mailConfirm = user.getMailforconfirm();
		if (StringUtils.isBlank(mailConfirm)) {
			errors.add(new ValidationError(PublicUserConstants.EMAILCONFIRM_ISBLANK, blank));
		}

		if ( StringUtils.isNotBlank(mail) && StringUtils.isNotBlank(mailConfirm) && !mail.equals(mailConfirm) ) {
			errors.add(new ValidationError(PublicUserConstants.EMAIL_EMAILCONFIRM_NOTSAME, new Object[] {mail,mailConfirm}));
		} else {
			PublicUser publicUserByEmail = publicUserDAO.findUserByEmail(mail);
			if ( publicUserByEmail != null ) {
				errors.add(new ValidationError(PublicUserConstants.USER_ALREADYEXISTSWITHTHISEMAIL, new Object[] {mail}));
			}
		}
		
		String address = user.getAddress();
		if (StringUtils.isBlank(address)) {
			errors.add(new ValidationError(PublicUserConstants.ADDRESS_ISBLANK, blank));
		}

/*		String fax = user.getFax();

		if (StringUtils.isBlank(fax)) {
			errors.add(new ValidationError(PublicUserConstants.FAX_ISBLANK, blank));
		}

		if (!StringUtils.isNumeric(fax)) {
			errors.add(new ValidationError(PublicUserConstants.FAX_ISNOTNUMERIC, fax));
		}
*/
		String phone = user.getPhone();
		if (StringUtils.isBlank(phone)) {
			errors.add(new ValidationError(PublicUserConstants.PHONE_ISBLANK, blank));
		}

		String city = user.getCity();
		if (StringUtils.isBlank(city)) {
			errors.add(new ValidationError(PublicUserConstants.CITY_ISBLANK, blank));
		}

/*		Integer authuserid = user.getAuthuserid();
		
		if (authuserid == null ) {
			errors.add(new ValidationError(PublicUserConstants.AUTHUSERID_ISBLANK, blank));
		}
*/
		
		
		if ( errors.hasErrors() ) {
			throw new ValidationException(errors);
		}
	}
}