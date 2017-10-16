package br.com.spring.framework.web.security.servlet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spring.framework.web.security.business.interfaces.ContactService;
import br.com.spring.framework.web.security.model.Contact;

@Component
public class ContatoComponent {

	@Autowired
	private ContactService contactServiceImpl;

	public void register() {
		Contact contato = new Contact("Nteste", "Lteste",
				"nteste.lteste@teste.com", "11987654321");

		contato = contactServiceImpl.create(contato);
		System.out.println(" ### Registro cadastrado com sucesso :: ["
				+ contato + "]");
	}

	public void getAll() {
		List<Contact> lista = contactServiceImpl.getAll();
		for (Contact contato : lista) {
			System.out.println(" ### Registro :: " + contato);
		}
	}
}
