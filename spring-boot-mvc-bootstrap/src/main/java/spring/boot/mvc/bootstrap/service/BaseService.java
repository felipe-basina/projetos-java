package spring.boot.mvc.bootstrap.service;

import java.util.List;

import javax.persistence.EntityManager;

import spring.boot.mvc.bootstrap.repository.BaseRepository;

public interface BaseService<T> {
	BaseRepository<T, Long> getRepository();

	T create(T t);

	void delete(Object id);

	T find(Object id);

	T update(T t);

	List<T> getAll();
	
	EntityManager getEntityManager();
}
