package spring.jpa.eclipselink.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import spring.jpa.eclipselink.domain.Company;
import spring.jpa.eclipselink.domain.Persistent;
import spring.jpa.eclipselink.domain.User;

/**
 * The Interface BaseDAO.
 *
 * @param <T>
 *            the generic type
 */
public interface BaseDAO<T extends Persistent<?>> {

	/**
	 * Find all by user.
	 *
	 * @param user
	 *            the user
	 * @return the list
	 */
	List<T> findAllByUser(User user);

	/**
	 * Execute sql list query.
	 *
	 * @param sqlQuery
	 *            the sql query
	 * @param args
	 *            the args
	 * @return the list
	 */
	List<?> executeSqlListQuery(String sqlQuery, Object... args);

	/**
	 * Find all.
	 *
	 * @param activeRegister
	 *            the active register
	 * @return the list
	 */
	List<T> findAll(final Boolean activeRegister);

	/**
	 * Find by id.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	T findById(Serializable id);

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	EntityManager getEntityManager();

	/**
	 * Save.
	 *
	 * @param entity
	 *            the entity
	 */
	void save(T entity);

	/**
	 * Update.
	 *
	 * @param entity
	 *            the entity
	 */
	void update(T entity);

	/**
	 * Delete.
	 *
	 * @param entity
	 *            the entity
	 */
	void delete(T entity);

	/**
	 * Find all by company.
	 *
	 * @param company
	 *            the company
	 * @param activeRegister
	 *            the active register
	 * @return the list
	 */
	List<T> findAllByCompany(Company company, Boolean activeRegister);

	/**
	 * Find all by company and user.
	 *
	 * @param company
	 *            the company
	 * @param user
	 *            the user
	 * @return the list
	 */
	List<T> findAllByCompanyAndUser(Company company, User user);

}