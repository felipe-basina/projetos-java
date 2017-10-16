package br.com.spring.jdbc.abstrato;

import br.com.spring.jdbc.SpringContext;

public class AbstrataRun {

	public static void main(String[] args) {
		Impl01 impl01 = SpringContext.getInstance().getBean(Impl01.class);
		impl01.exibirValor();
		impl01.alterarValor(5l);
		impl01.exibirValor();
		
		Impl01 impl011 = SpringContext.getInstance().getBean(Impl01.class);
		impl011.exibirValor();
		impl011.alterarValor(7l);
		impl011.exibirValor();

		impl01.exibirValor();
	}

}
