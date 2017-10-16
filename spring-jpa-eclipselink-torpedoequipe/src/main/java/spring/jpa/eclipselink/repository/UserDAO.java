package spring.jpa.eclipselink.repository;

import java.util.List;

import spring.jpa.eclipselink.domain.Company;
import spring.jpa.eclipselink.domain.User;
import spring.jpa.eclipselink.utils.ProfileEnum;

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
	User findByProfileAndCompany(ProfileEnum profile, Company company,
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
	User findByProfileAndMsisdn(ProfileEnum master, String msisdn,
			Boolean activeRegister);

	
	public List<User> findAllByCompany(final Company company);
}
