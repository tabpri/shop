package net.malta.dao;

import java.lang.reflect.ParameterizedType;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDAO<T> extends HibernateDaoSupport implements IBaseDAO<T> {

	@Override
	@SuppressWarnings("unchecked")
	public T find(Integer id) {
		Class persistentClass = (Class)
				   ((ParameterizedType)this.getClass().getGenericSuperclass())
				      .getActualTypeArguments()[0];
		return (T) getHibernateTemplate().get(persistentClass, id);
	}
	
	@Override
	public T saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
		return t;
	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
		getHibernateTemplate().flush();
	}

}
