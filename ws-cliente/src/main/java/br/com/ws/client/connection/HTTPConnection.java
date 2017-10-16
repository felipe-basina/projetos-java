package br.com.ws.client.connection;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.ws.client.component.Authorization;
import br.com.ws.client.model.AddressModel;


public abstract class HTTPConnection implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(HTTPConnection.class);
	
	protected Properties properties;
	
	protected AddressModel address;
	
	protected Authorization authorization;
	
	protected String httpRequestMethod = "GET";
	
	private final void invokeHttpOperation() throws Exception {
		this.run();
	}
	
	private String showStringLogger(String status,
			Long time,
			Integer httpResponseCode, 
			String separator) {
		separator = " ".concat(separator).concat(" ");
		
		StringBuilder sb = new StringBuilder(separator);
		sb.append(status);
		sb.append(separator);
		sb.append(httpResponseCode);
		sb.append(separator);
		sb.append(String.valueOf(time));
		sb.append(separator);
		sb.append(httpRequestMethod);
		sb.append(separator);
		sb.append(address.getAddress());
		sb.append("\n");
		
		return sb.toString();
	}
	
	public final void run() {
		HttpURLConnection urlConnection = null;
		try {
			
			URL url = new URL(address.getAddress());
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod(httpRequestMethod);
			
			if (authorization != null
					&& !"".equals(authorization.getEncodedAuthorization())
					&& authorization.getEncodedAuthorization().length() > 0) {
				urlConnection.setRequestProperty("Authorization", "Basic "
						.concat(authorization.getEncodedAuthorization()));
			}
			
			long init = System.currentTimeMillis();			
			
			Integer responseCode = urlConnection.getResponseCode();
			
			long end = System.currentTimeMillis();
			
			if (responseCode <= 0
					|| (responseCode != 200
					&& responseCode != 403
					&& responseCode != 405)) {
				
				LOGGER.error(this.showStringLogger("ERRO", (end - init), responseCode, ";"));
				
			} else if (responseCode == 405) {
				
				/* 
				 * Caso ocorra esse código de erro, executa mais uma vez
				 * alterando o método da solicitação HTTP
				 */
				if ("GET".equalsIgnoreCase(httpRequestMethod)) {
					httpRequestMethod = "POST";
					this.invokeHttpOperation();
				} else if ("POST".equalsIgnoreCase(httpRequestMethod)) {
					httpRequestMethod = "GET";
					this.invokeHttpOperation();
				}

			} else {
				
				LOGGER.debug(this.showStringLogger("SUCC", (end - init), responseCode, ";"));
				
			}
			
		} catch (Exception e) {
			
			LOGGER.error("Erro ao invocar operacao [" 
					+ address.getAddress() + "] = "
					+ e.toString(), e);
			LOGGER.error("\n");
			
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect();
			}
		}
	}
}