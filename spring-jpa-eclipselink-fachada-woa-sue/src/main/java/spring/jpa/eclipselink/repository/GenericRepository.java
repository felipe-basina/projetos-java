package spring.jpa.eclipselink.repository;

import java.util.List;

import javax.persistence.EntityManager;

import spring.jpa.eclipselink.exception.DataException;

public interface GenericRepository<T> {
	T create(T t) throws DataException;

	void delete(Object id) throws DataException;

	T find(Object id) throws DataException;

	T update(T t) throws DataException;

	List<T> getAll() throws DataException;

	EntityManager getEntityManager();
}
