package br.com.spring.testes;

import br.com.spring.testes.componentes.CarregaConfiguracao;

public class MainCarregaConfiguracao {

	public static void main(String[] args) {

		CarregaConfiguracao carregaConfiguracao = SpringContext
				.getInstance().getBean(CarregaConfiguracao.class);
		
		carregaConfiguracao.lerConfiguracoesClasspath();
		carregaConfiguracao.recuperarConfiguracoesClasspath();
		
		System.exit(1);
		
	}

}
