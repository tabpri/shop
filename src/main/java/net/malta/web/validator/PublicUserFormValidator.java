/**
 * @author SB
 */
package net.malta.web.validator;

import org.apache.commons.lang.StringUtils;

import net.malta.beans.PublicUserForm;
import net.malta.beans.ValidationError;
import net.malta.web.constants.PublicUserConstants;

public class PublicUserFormValidator implements IValidator<PublicUserForm> {

	@Override
	public Errors validate(PublicUserForm publicUserform,Errors errors) {

		Object[] blank = new Object[0]; 
				
		String name = publicUserform.getName();
		if (StringUtils.isBlank(name)) {
			errors.add(new ValidationError(PublicUserConstants.USERNAME_ISBLANK, blank));
		}

		String kana = publicUserform.getKana();
		if (StringUtils.isBlank(kana)) {
			errors.add(new ValidationError(PublicUserConstants.KANA_ISBLANK, blank));
		}

		int zip = publicUserform.getZip();

		if (zip == 0) {
			errors.add(new ValidationError(PublicUserConstants.ZIP_ISBLANK, blank));
		}

		Integer prefecture = publicUserform.getPrefecture();
		if (prefecture == null || prefecture.intValue() == 0) {
			errors.add(new ValidationError(PublicUserConstants.PREFECTURE_ISBLANK, blank));
		}

		String mail = publicUserform.getMail();
		if (StringUtils.isBlank(mail)) {
			errors.add(new ValidationError(PublicUserConstants.EMAIL_ISBLANK, blank));
		}

		String address = publicUserform.getAddress();
		if (StringUtils.isBlank(address)) {
			errors.add(new ValidationError(PublicUserConstants.ADDRESS_ISBLANK, blank));
		}

		String fax = publicUserform.getFax();

		if (StringUtils.isBlank(fax)) {
			errors.add(new ValidationError(PublicUserConstants.FAX_ISBLANK, blank));
		}

		if (!StringUtils.isNumeric(fax)) {
			errors.add(new ValidationError(PublicUserConstants.FAX_ISNOTNUMERIC, fax));
		}

		String phone = publicUserform.getPhone();
		if (StringUtils.isBlank(phone)) {
			errors.add(new ValidationError(PublicUserConstants.PHONE_ISBLANK, blank));
		}
		return errors;
	}

}