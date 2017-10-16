package br.com.spring.framework.web.security.dao.interfaces;

import br.com.spring.framework.web.security.model.Contact;

public interface ContactDao extends GenericDao<Contact> {

	Contact recuperarPorNome(String nome);
}
