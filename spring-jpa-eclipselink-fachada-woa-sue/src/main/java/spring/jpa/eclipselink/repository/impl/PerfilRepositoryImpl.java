package spring.jpa.eclipselink.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.Perfil;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.repository.PerfilRepository;

@Repository("perfilRepositoryImpl")
public class PerfilRepositoryImpl extends GenericRepositoryImpl<Perfil>
		implements PerfilRepository {

	@Override
	public Perfil recuperarPermissoesPerfil(Perfil perfil) throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createQuery(this.getMessage(
				"recupera.permissoes.perfil").getText());
		consulta.setHint("eclipselink.join-fetch",
				"p.matrizPermissoesPerfil.permissao");
		consulta.setHint("eclipselink.join-fetch",
				"p.matrizPermissoesPerfil.permissao.permissaoParametros");
		consulta.setParameter("nomePerfil", perfil.getNomePerfil()
				.toUpperCase());

		try {
			perfil = (Perfil) consulta.getResultList().get(0);
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return perfil;
	}

	@Override
	public Perfil recuperarPerfilPorNome(String nomePerfil)
			throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createQuery(this.getMessage(
				"recupera.perfil.pornome").getText());
		consulta.setParameter("nomePerfil", nomePerfil.toUpperCase());

		Perfil perfil = null;
		try {
			perfil = (Perfil) consulta.getResultList().get(0);
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return perfil;
	}
}
