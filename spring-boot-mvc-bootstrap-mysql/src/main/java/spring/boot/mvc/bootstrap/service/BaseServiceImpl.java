package spring.boot.mvc.bootstrap.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("baseServiceImpl")
@Scope("prototype")
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public T create(T t) {
		return this.getRepository().save(t);
	}
	
	@Override
	public T update(T t) {
		return this.create(t);
	}
	
	@Override
	public List<T> getAll() {
		return this.getRepository().findAll();
	}
	
	@Override
	public void delete(Object id) {
		this.getRepository().delete((Long) id);
	}
	
	@Override
	public T find(Object id) {
		return this.getRepository().findById((Long) id);
	}
	
	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}
}