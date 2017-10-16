package br.com.spring.testes;

import java.util.Date;

import org.apache.log4j.Logger;

import br.com.spring.testes.entidade.Entidade;
import br.com.spring.testes.services.Services;

public class Main {
	
	private static final Logger LOGGER = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		try {

			Services service = SpringContext.getInstance().getBean(
					Services.class);
			
			LOGGER.debug("# Entidades removidas? " 
					+ service.removerTodasEntidades());
			
			Entidade entidade = new Entidade();
			entidade.setNome("felipe");
			entidade.setDhCriacao(new Date());

			entidade = service.persistirEntidade(entidade);
			LOGGER.debug("# ID: " + entidade.getId());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
