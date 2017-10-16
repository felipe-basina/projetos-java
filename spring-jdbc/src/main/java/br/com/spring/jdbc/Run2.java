package br.com.spring.jdbc;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;

public class Run2 {

	private static final Logger LOGGER = Logger.getLogger(Run2.class);

	public static void main(String[] args) {
		ContatoRepository contatoRepository = SpringContext.getInstance()
				.getBean(ContatoRepository.class);

		//String numeroTelefone = "1197300840";
		String numeroTelefone = "1198765432";
		
		int contador = 0, MAX = 4;
		while (contador++ < MAX) {

			checkCache();

			numeroTelefone = numeroTelefone.concat(String.valueOf(contador));
			
			LOGGER.debug(" ### Iteracao numero = " + contador);
			LOGGER.debug(" ### .... "
					+ numeroTelefone
					+ "\n ### full: "
					+ contatoRepository.getContatoPorMsisdn(numeroTelefone));


			LOGGER.debug("-------------------------------------------------------------");
			LOGGER.debug("-------------------------------------------------------------");

			try {
				Thread.sleep(5 * 1000);
			} catch (Exception ex) {
				LOGGER.error(ex);
			}
		}
	}

	private static void checkCache() {

		CacheManager cacheManager = SpringContext.getInstance().getBean(
				EhCacheCacheManager.class);

		// Verifica os registros que estão no cache
		for (String cacheName : cacheManager.getCacheNames()) {
			Ehcache cache = (Ehcache) cacheManager.getCache(cacheName)
					.getNativeCache();
			if (cache != null) {
				try {
					for (Object key : cache.getKeys()) {
						Element element = cache.get(key);
						if (element != null) {
							LOGGER.debug(" :: cache name = " + cacheName
									+ " :: key = " + key + " ---------> "
									+ (Contato) element.getObjectValue());
						}
					}
				} catch (Exception ex) {
					LOGGER.error("Erro ao verificar cache: " + ex.getMessage(),
							ex);
				}
			}
		}
	}

}