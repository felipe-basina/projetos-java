package spring.jpa.eclipselink.repository.impl;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.Config;
import spring.jpa.eclipselink.repository.ConfigDAO;

/**
 * The Class ConfigDAOImpl.
 */
@Repository("configDAO")
public class ConfigDAOImpl extends BaseDAOImpl<Config> implements ConfigDAO {

	/**
	 * Instantiates a new config dao impl.
	 */
	public ConfigDAOImpl() {
		super(Config.class);
	}
}
