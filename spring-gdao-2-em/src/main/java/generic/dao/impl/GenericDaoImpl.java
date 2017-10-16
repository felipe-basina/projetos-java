package generic.dao.impl;

import generic.dao.interfaces.GenericDao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

	@PersistenceContext(unitName="PU_JPA")
	protected EntityManager em;
	
	private Class<T> type;

	@SuppressWarnings({"rawtypes", "unchecked"})
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public EntityManager getEntityManager() {
		return this.em;
	}
	
	@Transactional(readOnly=true)
	public T create(T t) {
		this.em.persist(t);
		return t;
	}

	@Transactional(readOnly=true)
	public void delete(Object id) {
		this.em.remove(this.em.getReference(type, id));
	}

	@Transactional(readOnly=true)
	public T find(Object id) {
		return (T) this.em.find(type, id);
	}

	@Transactional(readOnly=true)
	public T update(T t) {
		return this.em.merge(t);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<T> getAll() {		
		String clazz = type.toString().substring(type.toString().lastIndexOf(".") + 1, type.toString().length());
		return (ArrayList<T>) this.em.createQuery("from " + clazz).getResultList();
	}
}
