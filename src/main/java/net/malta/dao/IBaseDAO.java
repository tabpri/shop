package net.malta.dao;

public interface IBaseDAO<T> {

	T find(Integer id);

	T saveOrUpdate(T t);
	
	void  delete(T t);
	
}