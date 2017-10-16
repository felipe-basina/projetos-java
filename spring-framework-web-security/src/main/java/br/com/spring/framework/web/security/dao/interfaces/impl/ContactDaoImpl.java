package br.com.spring.framework.web.security.dao.interfaces.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.spring.framework.web.security.dao.interfaces.ContactDao;
import br.com.spring.framework.web.security.model.Contact;

@Repository
public class ContactDaoImpl extends GenericDaoImpl<Contact> implements
		ContactDao {

	@Override
	public Contact recuperarPorNome(String nome) {
		System.out.println("Preparando para recuperar dados contao: " + nome);
		EntityManager em = this.getEntityManager();

		Query consulta = em
				.createQuery("SELECT c FROM Contact c WHERE c.nome = :nome ");
		consulta.setParameter("nome", nome);

		try {
			@SuppressWarnings("unchecked")
			List<Contact> lista = consulta.getResultList();

			System.out
					.println("Consulta executada com sucesso. Retornando objeto...");

			return lista.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

}
