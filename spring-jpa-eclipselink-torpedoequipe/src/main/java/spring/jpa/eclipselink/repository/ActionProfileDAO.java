package spring.jpa.eclipselink.repository;

import java.util.List;

import spring.jpa.eclipselink.domain.ActionProfile;
import spring.jpa.eclipselink.domain.User;

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
