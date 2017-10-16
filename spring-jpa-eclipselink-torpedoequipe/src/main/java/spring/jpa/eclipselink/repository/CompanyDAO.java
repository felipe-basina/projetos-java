package spring.jpa.eclipselink.repository;

import java.util.List;

import spring.jpa.eclipselink.domain.Company;

/**
 * The Interface CompanyDAO.
 */
public interface CompanyDAO extends BaseDAO<Company> {

	/**
	 * Find by name.
	 *
	 * @param companyName
	 *            the company name
	 * @param activeRegister
	 *            the active register
	 * @return the company
	 */
	Company findByName(String companyName, Boolean activeRegister);

	/**
	 * Find company master.
	 *
	 * @return the list
	 */
	List<Company> findCompanyMaster();

}
