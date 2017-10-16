package br.com.ws.client;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.ws.client.config.PropertiesFileName;
import br.com.ws.client.config.PropertiesUtil;
import br.com.ws.client.connection.Addresses;
import br.com.ws.client.model.AddressModel;

public abstract class CheckSystem {

	private static final Integer MAX_THREADS = 10;

	protected static ExecutorService executor = null;
	//protected static ThreadPoolExecutor executor = null;

	protected Properties properties;

	protected final List<AddressModel> getAddresses() {
		return new Addresses(properties).getAddress();
	}

	protected abstract void process();

	protected final void check() throws Exception {

		try {

			String maxThreads = PropertiesUtil.getInstance()
					.getPropertyByPropertyNameFromPropertyFile(
							"application.max.threads",
							PropertiesFileName.APPLICATION_PROPERTY_FILES
									.getName());

			if (maxThreads != null && !"".equals(maxThreads)) {
				try {
					executor = Executors.newFixedThreadPool(Integer
							.parseInt(maxThreads));
				} catch (Exception ex) {
					executor = Executors.newFixedThreadPool(MAX_THREADS);
				}
			}

			this.process();

			executor.shutdown();

			// Aguarda até que todas as threads finalize o processamento
			while (!executor.isTerminated()) {

			}

		} catch (Exception ex) {
			throw new Exception(ex);
		}

	}
}