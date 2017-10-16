package spring.jpa.eclipselink.repository.impl;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.Action;
import spring.jpa.eclipselink.repository.ActionDAO;

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
