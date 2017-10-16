package spring.jpa.eclipselink.utils;

import java.util.Map;

/**
 * The Class ConfigContextHolder.
 */
public final class ConfigContextHolder {

	/**
	 * Instantiates a new config context holder.
	 */
	private ConfigContextHolder() {

	}

	/** The config util. */
	private static ConfigUtil configUtil;

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	public static String get(final ConfigEnum id) {
		return ConfigContextHolder.configUtil.getMap().get(id);
	}

	/**
	 * Gets the by id.
	 *
	 * @param id
	 *            the id
	 * @return the by id
	 */
	public static String get(final String id) {
		return ConfigContextHolder.configUtil.getMap().get(id);
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public static Map<ConfigEnum, String> getMap() {
		return ConfigContextHolder.configUtil.getMap();
	}

	/**
	 * Sets the config util.
	 *
	 * @param pConfigUtil
	 *            the new config util
	 */
	public static void setConfigUtil(final ConfigUtil pConfigUtil) {
		ConfigContextHolder.configUtil = pConfigUtil;
	}

	/**
	 * Reload.
	 */
	public static void reload() {
		ConfigContextHolder.configUtil.reload();
	}

}
