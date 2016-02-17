package net.malta.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

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
		getHibernateTemplate().flush();		
		return t;
	}

	@Override
	public Collection<? extends T> saveOrUpdateAll(Collection<? extends T> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
		getHibernateTemplate().flush();		
		return list;
	}

	@Override
	public Collection<? extends T> deleteAll(Collection<? extends T> list) {
		getHibernateTemplate().deleteAll(list);
		getHibernateTemplate().flush();		
		return list;
	}
	
	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
		getHibernateTemplate().flush();
	}
	
}
