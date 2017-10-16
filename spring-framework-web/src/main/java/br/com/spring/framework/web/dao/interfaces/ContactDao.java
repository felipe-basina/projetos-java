package br.com.spring.framework.web.dao.interfaces;

import br.com.spring.framework.web.model.Contact;

public interface ContactDao extends GenericDao<Contact> {

	Contact recuperarPorNome(String nome);
}
