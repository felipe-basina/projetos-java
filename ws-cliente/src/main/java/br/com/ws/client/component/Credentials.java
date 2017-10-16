package br.com.ws.client.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import br.com.ws.client.config.ParametersValidate;

public class Credentials implements Authorization {

	private Properties properties;
	
	public Credentials(Properties prop) {
		this.properties = prop;
	}
	
	public String getEncodedAuthorization() throws IllegalArgumentException {
		Map<String, String> parameters = new HashMap<String, String>();
		
		String user = properties.getProperty("services.user");
		parameters.put("user", user);

		String password = properties.getProperty("services.password");
		parameters.put("password", password);
		
		String validate = new ParametersValidate().parametersValidate(parameters);
		if (validate.contains("Parametro")) {
			throw new IllegalArgumentException("Erro na definicao dos parametros obrigatorios: "
					.concat(validate));
		}
		
		byte[] credentials = (user.concat(":").concat(password)).getBytes();
		String encodedAuthorization = javax.xml.bind.DatatypeConverter.printBase64Binary(credentials);
		
		return encodedAuthorization;
	}

}
