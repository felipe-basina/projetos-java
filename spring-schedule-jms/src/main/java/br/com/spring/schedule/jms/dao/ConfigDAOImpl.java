package br.com.spring.schedule.jms.dao;

import org.springframework.stereotype.Repository;

import br.com.spring.schedule.jms.domain.Config;

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
