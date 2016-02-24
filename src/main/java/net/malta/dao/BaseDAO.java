package net.malta.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDAO<T> implements IBaseDAO<T> {

	@Autowired	
	SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public T find(Integer id) {
		Class persistentClass = (Class)
				   ((ParameterizedType)this.getClass().getGenericSuperclass())
				      .getActualTypeArguments()[0];
		
		return (T) sessionFactory.getCurrentSession().get(persistentClass, id);
	}
	
	@Override
	public T saveOrUpdate(T t) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(t);
		currentSession.flush();		
		return t;
	}

	@Override
	public Collection<? extends T> saveOrUpdateAll(Collection<? extends T> list) {
		Session currentSession = sessionFactory.getCurrentSession();
		for (T t : list) {
			currentSession.save(t);			
		}
		currentSession.flush();		
		return list;
	}

	@Override
	public Collection<? extends T> deleteAll(Collection<? extends T> list) {
		Session currentSession = sessionFactory.getCurrentSession();
		for (T t : list) {
			currentSession.delete(t);			
		}
		currentSession.flush();		
		return list;
	}
	
	@Override
	public void delete(T t) {		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(t);
		currentSession.flush();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}