package br.com.ws.client.connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import br.com.ws.client.component.Address;
import br.com.ws.client.config.ParametersValidate;
import br.com.ws.client.config.StringUtils;
import br.com.ws.client.model.AddressModel;

public class Addresses implements Address {

	private Properties properties;
	
	public Addresses(Properties props) {
		this.properties = props;
	}

	public List<AddressModel> getAddress() throws IllegalArgumentException {
		Map<String, String> parameters = new HashMap<String, String>();

		String protocol = properties.getProperty("services.protocol");
		parameters.put("protocol", protocol);

		String server = properties.getProperty("services.server");
		parameters.put("server", server);

		String port = properties.getProperty("services.port");
		parameters.put("port", port);

		String context = properties.getProperty("services.context");
		parameters.put("context", context);

		String serviceName = properties
				.getProperty("services.service.name");
		parameters.put("service-name", context);

		String validate = new ParametersValidate()
				.parametersValidate(parameters);

		if (validate.contains("Parametro")) {
			throw new IllegalArgumentException(
					"Erro na definicao dos parametros obrigatorios: "
							.concat(validate));
		}

		String[] servicesName = StringUtils.splitStringSemiColon(serviceName);

		List<AddressModel> urls = new ArrayList<AddressModel>();

		for (String service : servicesName) {
			StringBuilder url = new StringBuilder();
			url.append(protocol.concat("://"));
			url.append(server.concat(":"));
			url.append(port);
			url.append(context);
			url.append(service);

			urls.add(new AddressModel(url.toString()));
		}

		return urls;
	}
}
