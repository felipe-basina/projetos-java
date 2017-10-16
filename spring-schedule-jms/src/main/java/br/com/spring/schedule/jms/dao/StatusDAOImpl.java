package br.com.spring.schedule.jms.dao;

import org.springframework.stereotype.Repository;

import br.com.spring.schedule.jms.domain.Status;

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
