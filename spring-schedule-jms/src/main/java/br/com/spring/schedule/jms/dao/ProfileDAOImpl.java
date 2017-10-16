package br.com.spring.schedule.jms.dao;

import org.springframework.stereotype.Repository;

import br.com.spring.schedule.jms.domain.Profile;

/**
 * The Class ProfileDAOImpl.
 */
@Repository("profileDAO")
public class ProfileDAOImpl extends BaseDAOImpl<Profile> implements ProfileDAO {

	/**
	 * Instantiates a new profile dao impl.
	 */
	public ProfileDAOImpl() {
		super(Profile.class);
	}

}
