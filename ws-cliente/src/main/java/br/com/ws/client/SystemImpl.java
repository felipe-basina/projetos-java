package br.com.ws.client;

import java.util.HashMap;
import java.util.Map;

import br.com.ws.client.config.PropertiesFileName;
import br.com.ws.client.config.StringUtils;

public class SystemImpl {

	private Map<String, ICheckSystem> system;

	public SystemImpl() {
		system = new HashMap<String, ICheckSystem>();

		system.put("SOA", new CheckWSDL(
				PropertiesFileName.SOA_CONFIG_PROPERTY_FILES.getName(), null));

		system.put("WOA", new CheckWADL(
				PropertiesFileName.WOA_CONFIG_PROPERTY_FILES.getName(), null));
	}

	public ICheckSystem getSystemImpl(String systemName)
			throws IllegalArgumentException {
		if (StringUtils.isStringNullOrEmpty(systemName)) {
			throw new IllegalArgumentException(
					"Nome do sistema nao definido/encontrado!");
		}

		ICheckSystem checkSystem = null;

		try {

			checkSystem = system.get(systemName.toUpperCase());

			if (checkSystem == null) {
				throw new Exception("Implementacao nao encontrada!");
			}

		} catch (Exception ex) {
			throw new IllegalArgumentException(
					"Erro ao recuperar implementacao: " + ex.getMessage(), ex);
		}

		return checkSystem;
	}
}
