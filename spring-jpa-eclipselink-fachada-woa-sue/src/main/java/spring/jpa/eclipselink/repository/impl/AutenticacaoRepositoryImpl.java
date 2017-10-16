package spring.jpa.eclipselink.repository.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.UsuarioAutenticacao;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.repository.AutenticacaoRepository;

@Repository("autenticacaoRepositoryImpl")
public class AutenticacaoRepositoryImpl extends
		GenericRepositoryImpl<UsuarioAutenticacao> implements
		AutenticacaoRepository {

	@Override
	public UsuarioAutenticacao buscarUsuarioPorLogin(String login)
			throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createQuery(this.getMessage(
				"recupera.usuario.autenticacao").getText());
		consulta.setParameter("login", login);

		UsuarioAutenticacao usuario = null;
		try {
			usuario = (UsuarioAutenticacao) consulta.getSingleResult();
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioAutenticacao> buscarUsuariosPorLogin(Set<String> logins)
			throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createQuery(this.getMessage(
				"recupera.usuarios.autenticacao.porlogins").getText());
		consulta.setParameter("logins", logins);

		List<UsuarioAutenticacao> usuarios = null;
		try {
			usuarios = (Vector<UsuarioAutenticacao>) consulta.getResultList();
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return usuarios;
	}

	@Override
	public UsuarioAutenticacao buscarUsuarioTemporarioPorLogin(String loginTemp)
			throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createQuery(this.getMessage(
				"recupera.usuario.autenticacao.temporario").getText());
		consulta.setParameter("loginTemp", loginTemp);
		consulta.setParameter("dtExpiracaoSenhaTemp", new Date());

		UsuarioAutenticacao usuario = null;
		try {
			usuario = (UsuarioAutenticacao) consulta.getSingleResult();
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return usuario;
	}
}