package fr.lokm.model.dao;

import java.util.List;



public interface InterfaceDAO<T> {
	T find(int id);
	void delete(int id);
	void update(T obj);
	void create(T obj);
	List<T> findAll();
}
