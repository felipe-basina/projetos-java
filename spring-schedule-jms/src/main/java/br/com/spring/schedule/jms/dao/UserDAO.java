package br.com.spring.schedule.jms.dao;

import br.com.spring.schedule.jms.domain.Company;
import br.com.spring.schedule.jms.domain.User;

/**
 * The Interface UserDAO.
 */
public interface UserDAO extends BaseDAO<User> {

	/**
	 * Find by msisdn.
	 *
	 * @param msisdn
	 *            the msisdn
	 * @param activeRegister
	 *            the active register
	 * @return the user
	 */
	User findByMsisdn(String msisdn, Boolean activeRegister);

	/**
	 * Find by profile and company.
	 *
	 * @param profile
	 *            the profile
	 * @param company
	 *            the company
	 * @param activeRegister
	 *            the active register
	 * @return the user
	 */
	User findByProfileAndCompany(Company company,
			Boolean activeRegister);

	/**
	 * Find by profile and msisdn.
	 *
	 * @param master
	 *            the master
	 * @param msisdn
	 *            the msisdn
	 * @param activeRegister
	 *            the active register
	 * @return the user
	 */
	User findByProfileAndMsisdn(String msisdn, Boolean activeRegister);

}
