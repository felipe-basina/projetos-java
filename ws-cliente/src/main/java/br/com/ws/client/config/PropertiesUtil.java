package br.com.ws.client.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

	private static Map<String, Properties> propertyMap;

	private static final PropertiesUtil instance = new PropertiesUtil();
	
	private PropertiesUtil() {
		if (propertyMap == null) {
			propertyMap = new HashMap<String, Properties>();
		}
	}
	
	public static PropertiesUtil getInstance() {
		return instance;
	}

	public void addPropertiesFile(String propertyFileName,
			Properties propertiesFile) {
		if (StringUtils.isStringNullOrEmpty(propertyFileName)) {
			throw new IllegalArgumentException(
					"Nome arquivo de propriedade nao definido");
		}

		if (propertiesFile == null) {
			throw new IllegalArgumentException(
					"Arquivo de propriedade nao definido/encontrado");
		}

		propertyMap.put(propertyFileName, propertiesFile);
	}

	public String getPropertyByPropertyNameFromPropertyFile(
			String propertyName, String propertyFileName)
			throws IllegalArgumentException {
		if (StringUtils.isStringNullOrEmpty(propertyName)) {
			throw new IllegalArgumentException(
					"Nome propriedadade nao definida");
		}

		if (StringUtils.isStringNullOrEmpty(propertyFileName)) {
			throw new IllegalArgumentException(
					"Nome arquivo de propriedade nao definido");
		}

		String property = "";

		try {

			Properties properties = propertyMap.get(propertyFileName);

			property = properties.getProperty(propertyName);

		} catch (Exception ex) {
			// Não fazer nada
		}

		return property;
	}

}
