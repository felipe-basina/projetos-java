package br.com.sample.without.spring.boot;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppTeste {
	
	private static final SpringContext context = SpringContext.getInstance();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppTeste.class);
	
	static {
		LOGGER.debug(" # Logging iniciado com sucesso!");
	}
	
	public static void main(String[] args) {
		
		try {
			
			TesteService testeService = context.getBean(TesteService.class);

			List<Teste> testes = testeService.getAllTeste();
			LOGGER.debug(" ####### Total registers: " + testes.size());
			
			for (Teste t : testes) {
				LOGGER.debug(t.toString());
				for (OTeste o : t.getOtestes()) {
					LOGGER.debug("\t" + o.toString());	
				}
			}
			
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		} finally {
			System.exit(1);
		}
		
	}
}
