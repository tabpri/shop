package net.malta.dao.meta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import net.malta.dao.BaseDAO;
import net.malta.model.PrefectureCarriage;

@Component
public class PrefectureCarriageDAO extends BaseDAO<PrefectureCarriage>{

	@SuppressWarnings("unchecked")
	public List<PrefectureCarriage> getPrefectureCarriageValues() {
		Criteria criteria = this.getSessionFactory().getCurrentSession().
				createCriteria(PrefectureCarriage.class);
		return criteria.list();		
	}

	public Integer getCarriageValue(Integer prefecture) {
		Criteria criteria = this.getSessionFactory().getCurrentSession().
				createCriteria(PrefectureCarriage.class);
		criteria.add(Restrictions.eq("prefecture", prefecture));
		PrefectureCarriage carriage = (PrefectureCarriage) criteria.uniqueResult();
		return carriage.getCarriageValue();
	}
}