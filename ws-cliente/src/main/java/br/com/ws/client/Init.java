package br.com.ws.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.ws.client.config.LoadConfig;
import br.com.ws.client.config.PropertiesFileName;
import br.com.ws.client.config.StringUtils;

public class Init {

	private static final Logger LOGGER = Logger.getLogger(Init.class);

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"dd/MM/yyyy kk:mm:ss");

	public static void main(String[] args) {

		LOGGER.debug(" ----------- Inicio processamento: "
				+ sdf.format(new Date()) + "\n");

		try {

			String systemName = Init.getSystemName();

			ICheckSystem checkSystem = new SystemImpl()
					.getSystemImpl(systemName);
			checkSystem.checkSystem();

		} catch (Exception ex) {
			LOGGER.error("#### Erro: " + ex.getMessage(), ex);
		}

		LOGGER.debug(" ----------- Fim de processamento: "
				+ sdf.format(new Date()) + "\n");

		System.exit(1);
	}

	public static String getSystemName() throws IllegalArgumentException {
		Properties properties = new LoadConfig().loadPropertiesByName(
				PropertiesFileName.APPLICATION_PROPERTY_FILES.getName(), null);

		String system = properties.getProperty("application.system");

		if (StringUtils.isStringNullOrEmpty(system)) {
			system = "soa";
		}

		return system;
	}

}