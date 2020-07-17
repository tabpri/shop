package net.malta.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;

import net.malta.model.PaymentMethod;

public class BaseDAO<T> implements IBaseDAO<T> {

	@Autowired	
	SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T find(Integer id) {
		Class persistentClass = (Class)
				   ((ParameterizedType)this.getClass().getGenericSuperclass())
				      .getActualTypeArguments()[0];
		return (T) this.sessionFactory.getCurrentSession().get(persistentClass, id);
	}
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })	
	public List<T> findAll() {
		Class persistentClass = (Class)
				   ((ParameterizedType)this.getClass().getGenericSuperclass())
				      .getActualTypeArguments()[0];		
		Criteria criteria = this.getSessionFactory().getCurrentSession().createCriteria(persistentClass);
		return criteria.list();
	}
	
	@Override
	public T saveOrUpdate(T t) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(t);
		currentSession.flush();		
		return t;
	}

	@Override
	public Collection<? extends T> saveOrUpdateAll(Collection<? extends T> list) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		for (T t : list) {
			currentSession.save(t);			
		}
		currentSession.flush();		
		return list;
	}

	@Override
	public Collection<? extends T> deleteAll(Collection<? extends T> list) {
		Session currentSession = this.sessionFactory.getCurrentSession();
		for (T t : list) {
			currentSession.delete(t);			
		}
		currentSession.flush();		
		return list;
	}
	
	@Override
	public void delete(T t) {		
		Session currentSession = this.sessionFactory.getCurrentSession();
		currentSession.delete(t);
		currentSession.flush();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}