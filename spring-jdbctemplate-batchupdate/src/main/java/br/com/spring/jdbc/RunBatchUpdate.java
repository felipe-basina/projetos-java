package br.com.spring.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class RunBatchUpdate {

	private static final Logger LOGGER = Logger.getLogger(RunBatchUpdate.class);
	
	public static void main(String[] args) {

		try {
			
			final int totalRegistros = 500000;
			
			List<Teste> testeLista = new ArrayList<Teste>();
			
			// Criar registros
			for (int indice = 0; indice < totalRegistros; indice++) {
				testeLista.add(new Teste("nome-" + indice));
			}
			
			TesteRepository testeRepository = SpringContext.getInstance()
					.getBean(TesteRepository.class);
			
			// Remover registros
			int totalRegistrosRemovidos = testeRepository.removerRegistros();
			LOGGER.debug("# Total de registros removidos [ " + totalRegistrosRemovidos + " ]");
			
			// Persistir registros (batch update)
//			testeRepository.salvar(testeLista, true);

			// Persistir registros (unitário)
			testeRepository.salvar(testeLista, false);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
