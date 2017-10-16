package spring.jpa.eclipselink.repository.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import spring.jpa.eclipselink.domain.Company;
import spring.jpa.eclipselink.domain.Persistent;
import spring.jpa.eclipselink.domain.User;
import spring.jpa.eclipselink.repository.BaseDAO;

/**
 * The Class BaseDAOImpl.
 * 
 * @param <T>
 *            the generic type
 */
@Transactional
public abstract class BaseDAOImpl<T extends Persistent<?>> implements
		BaseDAO<T> {

	/** The clazz. */
	private Class<T> clazz;

	/** The entity manager. */
	@PersistenceContext(unitName = "TorpedoEquipeUnit")
	private EntityManager entityManager;

	/**
	 * Instantiates a new base dao impl.
	 * 
	 * @param targetClass
	 *            the target class
	 */
	public BaseDAOImpl(final Class<T> targetClass) {
		this.clazz = targetClass;
	}

	/**
	 * Gets the clazz.
	 * 
	 * @return the clazz
	 */
	public final Class<T> getClazz() {
		return clazz;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<T> findAll(final Boolean activeRegister) {
		StringBuilder sQuery = new StringBuilder("select g from ".concat(
				this.clazz.getSimpleName()).concat(" g"));

		if (activeRegister != null) {
			sQuery.append(" where g.activeRegister = " + activeRegister);
		}

		Query query = this.entityManager.createQuery(sQuery.toString());
		return query.getResultList();

	}

	@Override
	public final void save(final T entity) {
		this.entityManager.persist(entity);
	}

	@Override
	public final void update(final T entity) {
		this.entityManager.merge(entity);
	}

	@Override
	public final void delete(final T entity) {
		this.entityManager.remove(this.entityManager.getReference(this.clazz,
				entity.getId()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<T> findAllByUser(final User user) {
		Query query = this.entityManager.createQuery("select entity from "
				.concat(this.clazz.getSimpleName()).concat(
						" entity where entity.user = :user"));
		query.setParameter("user", user);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<T> findAllByCompany(final Company company,
			final Boolean activeRegister) {
		StringBuilder sQuery = new StringBuilder("SELECT g from ");
		sQuery.append(getClazz().getSimpleName());
		sQuery.append(" g where g.company = :company ");

		if (activeRegister != null) {
			sQuery.append(" and g.activeRegister = " + activeRegister);
		}

		Query query = getEntityManager().createQuery(sQuery.toString());
		query.setParameter("company", company);

		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<T> findAllByCompanyAndUser(final Company company,
			final User user) {
		Query query = this.getEntityManager().createQuery(
				"select g from ".concat(getClazz().getSimpleName()).concat(
						" g where g.company = :company and g.user = :user"));
		query.setParameter("company", company);
		query.setParameter("user", user);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<T> executeSqlListQuery(final String sqlQuery,
			final Object... args) {
		Query query = this.entityManager.createNativeQuery(sqlQuery);
		if (args != null) {
			int i = 1;
			for (Object object : args) {
				query.setParameter(i++, object);
			}
		}
		return query.getResultList();
	}

	@Override
	public final T findById(final Serializable id) {
		return entityManager.find(clazz, id);
	}

	@Override
	@Transactional
	public final EntityManager getEntityManager() {
		return entityManager;
	}

}