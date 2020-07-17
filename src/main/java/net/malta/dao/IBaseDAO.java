package net.malta.dao;

import java.util.Collection;
import java.util.List;

public interface IBaseDAO<T> {

	T find(Integer id);

	T saveOrUpdate(T t);
	
	void  delete(T t);

	Collection<? extends T> saveOrUpdateAll(Collection<? extends T> list);

	Collection<? extends T> deleteAll(Collection<? extends T> list);

	List<T> findAll();
	
}