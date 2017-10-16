package spring.jpa.eclipselink.repository;

import java.util.List;

import spring.jpa.eclipselink.domain.CompanyGroup;

/**
 * The Interface CompanyGroupDAO.
 */
public interface CompanyGroupDAO extends BaseDAO<CompanyGroup> {

	/**
	 * Find by group name.
	 * 
	 * @param groupName
	 *            the group name
	 * @return the list
	 */
	List<CompanyGroup> findByGroupName(String groupName);
	
	List<CompanyGroup> findByCompanyGroupId(Long companyId);

}
