package br.com.spring.jdbc;

import org.apache.log4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class Run {

	private static final Logger LOGGER = Logger.getLogger(Run.class);

	public static void main(String[] args) {
		ContatoRepository contatoRepository = SpringContext.getInstance()
				.getBean(ContatoRepository.class);

		int contador = 0, MAX = 10;
		while (contador++ < MAX) {

			checkCache();

			LOGGER.debug(" ### Iteracao numero = " + contador);
			for (Contato contato : contatoRepository.getContatos()) {
				LOGGER.debug(" ### .... "
						+ contato.getTelefone()
						+ "\n ### full: "
						+ contatoRepository.getContatoPorMsisdn(contato
								.getTelefone()));
			}

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