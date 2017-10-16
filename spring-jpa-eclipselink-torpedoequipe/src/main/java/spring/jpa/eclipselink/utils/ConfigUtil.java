package spring.jpa.eclipselink.utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.jpa.eclipselink.domain.Config;
import spring.jpa.eclipselink.repository.ConfigDAO;

/**
 * The Class ConfigUtil.
 */
@Component
public class ConfigUtil {

	/** The config map. */
	private Map<ConfigEnum, String> configMap;

	/** The config bo. */
	@Autowired
	private ConfigDAO configDao;

	/**
	 * Reload.
	 */
	public void reload() {
		List<Config> configs = configDao.findAll(true);
		this.configMap = new ConcurrentHashMap<ConfigEnum, String>();
		for (Config config : configs) {
			this.configMap.put(config.getId(), config.getVlSystemConfigParam());
		}
		this.init();
	}

	/**
	 * Gets the config map.
	 *
	 * @return the config map
	 */
	public final Map<ConfigEnum, String> getMap() {
		if (this.configMap == null) {
			this.reload();
		}
		return this.configMap;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		ConfigContextHolder.setConfigUtil(this);
	}

}
