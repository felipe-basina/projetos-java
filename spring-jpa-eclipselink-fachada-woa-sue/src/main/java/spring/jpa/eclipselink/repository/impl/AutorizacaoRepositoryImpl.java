package spring.jpa.eclipselink.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.UsuarioAutorizacao;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.repository.AutorizacaoRepository;

@Repository("autorizacaoRepositoryImpl")
public class AutorizacaoRepositoryImpl extends
		GenericRepositoryImpl<UsuarioAutorizacao> implements
		AutorizacaoRepository {

	@Override
	public UsuarioAutorizacao buscarUsuarioPorLogin(String login)
			throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createQuery(this.getMessage(
				"recupera.usuario.autorizacao").getText());
		consulta.setParameter("login", login);

		UsuarioAutorizacao usuario = null;
		try {
			usuario = (UsuarioAutorizacao) consulta.getSingleResult();
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return usuario;
	}

	@Override
	public UsuarioAutorizacao listarRecursosUsuario(String login)
			throws DataException {

		EntityManager em = this.getEntityManager();

		/***
		 * Para buscar pelas permissoes utilizar left-join-fetch
		 * pois nao necessariamente o perfil tera permissoes (acoes)
		 * associadas
		 */
		
		Query consulta = em.createQuery(this.getMessage(
				"recupera.recursos.autorizacao").getText());
		consulta.setHint("eclipselink.join-fetch",
				"u.matrizAutorizacoes.perfil");
		consulta.setHint("eclipselink.left-join-fetch",
				"u.matrizAutorizacoes.perfil.matrizPermissoesPerfil");
		consulta.setHint("eclipselink.left-join-fetch",
				"u.matrizAutorizacoes.perfil.matrizPermissoesPerfil.permissao");
		consulta.setHint("eclipselink.left-join-fetch",
				"u.matrizAutorizacoes.perfil.matrizPermissoesPerfil.permissao.permissaoParametros");		
		consulta.setHint("eclipselink.join-fetch",
				"u.matrizAutorizacoes.recurso");
		consulta.setHint("eclipselink.join-fetch",
				"u.matrizAutorizacoes.recurso.recursoParametros");
		consulta.setParameter("login", login);

		UsuarioAutorizacao usuario = null;
		try {
			usuario = (UsuarioAutorizacao) consulta.getResultList().get(0);
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return usuario;
	}

	@Override
	public void removerUsuario(Long idUsuario) throws DataException {
		this.delete(idUsuario);
	}
}
