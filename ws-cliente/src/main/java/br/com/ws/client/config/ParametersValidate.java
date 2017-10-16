package br.com.ws.client.config;

import java.util.Map;

public class ParametersValidate {

	public String parametersValidate(Map<String, String> parameters) throws IllegalArgumentException {
		if (parameters == null
				|| parameters.size() <= 0) {
			throw new IllegalArgumentException("Parametros nao definidos");
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (Map.Entry<String, String> parameter : parameters.entrySet()) {
			if (parameter.getValue() == null
					|| "".equals(parameter.getValue())
					|| parameter.getValue().length() <= 0) {
				sb.append("\n...Parametro [".concat(parameter.getKey())
						.concat("] nao definido"));
			}
		}
		
		return sb.toString();
	}
}
