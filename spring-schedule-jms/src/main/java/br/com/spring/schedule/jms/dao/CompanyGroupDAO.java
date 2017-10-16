package br.com.spring.schedule.jms.dao;

import java.util.List;

import br.com.spring.schedule.jms.domain.CompanyGroup;

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

}
