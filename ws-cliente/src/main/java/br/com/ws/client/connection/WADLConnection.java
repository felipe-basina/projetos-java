package br.com.ws.client.connection;

import java.util.Properties;

import br.com.ws.client.component.Credentials;
import br.com.ws.client.config.StringUtils;
import br.com.ws.client.model.AddressModel;

public class WADLConnection extends HTTPConnection {

	public WADLConnection(Properties props, AddressModel addressModel) {
		super();
		this.properties = props;
		this.address = addressModel;
		this.authorization = new Credentials(properties);

		String httpMethod = properties.getProperty("services.request.method");

		if (!StringUtils.isStringNullOrEmpty(httpMethod)) {
			this.httpRequestMethod = httpMethod;
		}
	}
}