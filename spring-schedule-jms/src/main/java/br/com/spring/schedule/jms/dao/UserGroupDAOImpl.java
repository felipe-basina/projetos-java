package br.com.spring.schedule.jms.dao;

import org.springframework.stereotype.Repository;

import br.com.spring.schedule.jms.domain.UserGroup;

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
