package br.com.spring.framework.web.security.business.interfaces;

import java.util.List;

import br.com.spring.framework.web.security.model.Contact;

public interface ContactService {
	Contact create(Contact t);

	void delete(Object id);

	Contact find(Object id);

	Contact update(Contact t);

	List<Contact> getAll();

	Contact recuperarPorNome(String nome);
}
