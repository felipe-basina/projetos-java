package br.com.ws.client;

import org.apache.log4j.Logger;

import br.com.ws.client.config.LoadConfig;
import br.com.ws.client.connection.WADLConnection;
import br.com.ws.client.model.AddressModel;

public class CheckWADL extends CheckSystem implements ICheckSystem {

	private static final Logger LOGGER = Logger.getLogger(CheckWADL.class);
	
	public CheckWADL(String propertyFile, String path) {
		this.properties = new LoadConfig().loadPropertiesByName(propertyFile, path);
	}
	
	public void checkSystem() {
		
		try {
			
			this.check();

		} catch (Exception ex) {
			LOGGER.error("ATENCAO: " + ex.getMessage(), ex);
		}

	}
	
	@Override
	protected void process() {
		for (AddressModel address : this.getAddresses()) {
			Runnable worker = new WADLConnection(properties, address);
			executor.execute(worker);
		}
	}
}