package net.malta.beans.mapper;

import org.springframework.stereotype.Component;

import net.malta.beans.PublicUserForm;
import net.malta.mapper.IMapper;
import net.malta.model.Prefecture;
import net.malta.model.PrefectureImpl;
import net.malta.model.PublicUser;

@Component
public class PublicUserFormMapper implements IMapper<PublicUserForm, PublicUser>{

	@Override
	public PublicUser map(PublicUserForm form, PublicUser user) {
		user.setId(form.getId());
		user.setName(form.getName());
		user.setKana(form.getKana());
		user.setZipthree(form.getZip());
		user.setZipfour(form.getZipfour());
		user.setMailforconfirm(form.getMailforconfirm());
		user.setMail(form.getMail());
		user.setAddress(form.getAddress());
		user.setBuildingname(form.getBuildingname());
		user.setCity(form.getCity());
		user.setRegisted(form.isRegistered());
		user.setFax(form.getFax());
		user.setPhone(form.getPhone());
		user.setPassword(form.getPassword());
		user.setTemp(new Boolean(false));
		Prefecture prefecture = new PrefectureImpl();
		prefecture.setId(form.getPrefecture());
		user.setPrefecture(prefecture);
		user.setAuthuserid(form.getAuthuserid());
		return user;
	}

}
