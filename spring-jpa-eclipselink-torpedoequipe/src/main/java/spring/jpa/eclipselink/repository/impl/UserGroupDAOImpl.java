package spring.jpa.eclipselink.repository.impl;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.UserGroup;
import spring.jpa.eclipselink.repository.UserGroupDAO;

/**
 * The Class UserGroupDAOImpl.
 */
@Repository("userGroupDAO")
public class UserGroupDAOImpl extends BaseDAOImpl<UserGroup> implements
		UserGroupDAO {

	/**
	 * Instantiates a new user group dao impl.
	 */
	public UserGroupDAOImpl() {
		super(UserGroup.class);
	}

}
