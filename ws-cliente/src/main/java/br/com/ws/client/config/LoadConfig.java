package br.com.ws.client.config;

import java.io.InputStream;
import java.util.Properties;

public class LoadConfig {
	
	private static Integer MAX_RETRY = 3;
	
	private Integer count = 0;
	
	public Properties loadPropertiesByName(String propertyFileName, String path) throws IllegalArgumentException {
		if (propertyFileName == null
				|| "".equals(propertyFileName)) {
			throw new IllegalArgumentException("Obrigatorio definir nome do arquivo de propriedades");
		}
		
		Properties prop = null;
		InputStream input = null;
		
		++count;
		
		try {		
			prop = new Properties();
			
			if (path != null
					&& !"".equals(path)) {
				propertyFileName = path.concat(propertyFileName);
			}
			
			input = this.getClass().getClassLoader().getResourceAsStream(propertyFileName);

			prop.load(input);
		} catch (Exception ex) {
			if (count < MAX_RETRY) {
				this.loadPropertiesByName(propertyFileName, "../");
			} else {
				throw new IllegalArgumentException("Erro ao recuperar arquivo ["
						.concat(propertyFileName)
						.concat("] -\nErro: ")
						+ ex.getMessage());
			}
		} finally {
			
			PropertiesUtil.getInstance().addPropertiesFile(propertyFileName,
					prop);
		}

		return prop;
	}
}
