package br.com.spring.framework.web.business.interfaces.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.spring.framework.web.business.interfaces.ContactService;
import br.com.spring.framework.web.dao.interfaces.ContactDao;
import br.com.spring.framework.web.model.Contact;

@Service("contactServiceImpl")
@Scope("prototype")
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;

	public Contact create(Contact t) {
		return contactDao.create(t);
	}

	public void delete(Object id) {
		contactDao.delete(id);
	}

	public Contact find(Object id) {
		return contactDao.find(id);
	}

	public Contact update(Contact t) {
		return contactDao.update(t);
	}

	public List<Contact> getAll() {
		return contactDao.getAll();
	}

	@Override
	public Contact recuperarPorNome(String nome) {
		return contactDao.recuperarPorNome(nome);
	}

}
