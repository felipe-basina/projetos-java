package spring.jpa.eclipselink.repository.impl;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.Profile;
import spring.jpa.eclipselink.repository.ProfileDAO;

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
