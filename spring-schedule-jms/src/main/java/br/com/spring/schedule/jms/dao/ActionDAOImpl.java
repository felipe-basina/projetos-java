package br.com.spring.schedule.jms.dao;

import org.springframework.stereotype.Repository;

import br.com.spring.schedule.jms.domain.Action;

/**
 * The Class ActionDAOImpl.
 */
@Repository("actionDAO")
public class ActionDAOImpl extends BaseDAOImpl<Action> implements ActionDAO {

	/**
	 * Instantiates a new action dao impl.
	 */
	public ActionDAOImpl() {
		super(Action.class);
	}

}
