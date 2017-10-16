package br.com.spring.testes.componentes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:resources/propriedades.properties" })
public class CarregaConfiguracao {

	private static final Logger LOGGER = Logger.getLogger(CarregaConfiguracao.class);
	
	@Autowired
	private Environment env;
	
	public void lerConfiguracoesClasspath() {
		LOGGER.debug("... [ sftp.usuario ] :: " + env.getProperty("sftp.usuario"));
		LOGGER.debug("... [ sftp.senha ] :: " + env.getProperty("sftp.senha"));
		LOGGER.debug("... [ sftp.servidor ] :: " + env.getProperty("sftp.servidor"));
		LOGGER.debug("... [ sftp.porta ] :: " + env.getProperty("sftp.porta"));
		LOGGER.debug("... [ sftp.diretorio.pai ] :: " + env.getProperty("sftp.diretorio.pai"));
		LOGGER.debug("... [ sftp.diretorio.destino ] :: " + env.getProperty("sftp.diretorio.destino"));
	}
	
	public void recuperarConfiguracoesClasspath() {
		final Map<String, Object> configuracoes = new HashMap<String, Object>();
		
		for (Iterator<?> it = ((AbstractEnvironment) this.env)
				.getPropertySources().iterator(); it.hasNext();) {
			
			org.springframework.core.env.PropertySource<?> propriedade = (org.springframework.core.env.PropertySource<?>) it.next();
			
			// Retorna todas as configurações do ambiente MapPropertySource
			if (propriedade instanceof ResourcePropertySource) {
				configuracoes.putAll(((ResourcePropertySource) propriedade).getSource());
			}
			
		}
		
		for (Map.Entry<String, Object> entry : configuracoes.entrySet()) {
			LOGGER.debug("CHAVE [ " + entry.getKey() 
					+ " ] --- VALOR [ " 
					+ entry.getValue() + " ]");
		}
		
	}
	
}
