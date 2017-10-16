package br.com.spring.jdbc.abstrato;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class Impl01 extends Abstrata {
	
	public void exibirValor() {
		System.out.println(this.getId());
	}
	
	public void alterarValor(long novoValor) {
		this.setId(novoValor);
	}
	
}
