package spring.jpa.eclipselink.utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring.jpa.eclipselink.domain.ParametroSistema;
import spring.jpa.eclipselink.repository.ParametroSistemaRepository;

/**
 * The Class ConfigUtil.
 */
@Component
public class ConfigUtil {

	protected final Logger logger = Logger.getLogger(this.getClass());

	/** The config map. */
	private Map<ConfigEnum, String> configMap;

	/** The config bo. */
	@Autowired
	private ParametroSistemaRepository psRepository;

	/**
	 * Reload.
	 */
	public void reload() {
		List<ParametroSistema> configs;
		try {
			configs = psRepository.getAll();

			this.configMap = new ConcurrentHashMap<ConfigEnum, String>();
			for (ParametroSistema config : configs) {
				this.configMap.put(config.getNoParametro(),
						config.getVlParametro());
			}

			this.init();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
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
