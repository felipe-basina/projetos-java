package br.com.ws.client.config;

public enum PropertiesFileName {
	
	APPLICATION_PROPERTY_FILES ("config-application.properties"),
	SOA_CONFIG_PROPERTY_FILES ("config-soa-services.properties"),
	WOA_CONFIG_PROPERTY_FILES ("config-woa-services.properties");
	
	public String name;
	
	private PropertiesFileName(String n) {
		this.name = n;
	}

	public String getName() {
		return name;
	}
}
