package br.com.spring.schedule.jms.dao;

import java.util.List;

import br.com.spring.schedule.jms.domain.ActionProfile;
import br.com.spring.schedule.jms.domain.User;

/**
 * The Interface ActionProfileDAO.
 */
public interface ActionProfileDAO extends BaseDAO<ActionProfile> {

	/**
	 * Gets the actions profile by user.
	 *
	 * @param user
	 *            the user
	 * @return the actions profile by user
	 */
	List<ActionProfile> getActionsProfileByUser(User user);

}
