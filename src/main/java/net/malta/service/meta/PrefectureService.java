package net.malta.service.meta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.malta.dao.meta.PrefectureDAO;
import net.malta.model.Prefecture;

@Service
public class PrefectureService implements IPrefectureService {

	@Autowired
	private PrefectureDAO prefectureDAO;
		
	@Override
	@Transactional(propagation=Propagation.REQUIRED)	
	public Prefecture getPrefecture(Integer id) {
		return prefectureDAO.find(id);
	}

	public void setPrefectureDAO(PrefectureDAO prefectureDAO) {
		this.prefectureDAO = prefectureDAO;
	}
}
