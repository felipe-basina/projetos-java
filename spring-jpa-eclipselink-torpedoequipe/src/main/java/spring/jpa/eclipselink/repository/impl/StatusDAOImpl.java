package spring.jpa.eclipselink.repository.impl;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.Status;
import spring.jpa.eclipselink.repository.StatusDAO;

/**
 * The Class StatusDAOImpl.
 */
@Repository("statusDAO")
public class StatusDAOImpl extends BaseDAOImpl<Status> implements StatusDAO {

	/**
	 * Instantiates a new status dao impl.
	 */
	public StatusDAOImpl() {
		super(Status.class);
	}
}
