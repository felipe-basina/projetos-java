package spring.jpa.eclipselink.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.UsuarioPerfilRecurso;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.repository.UsuarioPerfilRecursoRepository;

@Repository("matrizAutorizacoesRepositoryImpl")
public class UsuarioPerfilRecursoRepositoryImpl extends
		GenericRepositoryImpl<UsuarioPerfilRecurso> implements
		UsuarioPerfilRecursoRepository {

	@Override
	public UsuarioPerfilRecurso cadastrarMatrizAutorizacoes(
			UsuarioPerfilRecurso matriz) throws DataException {
		return this.create(matriz);
	}

	@Override
	public void removerMatrizAutorizacoes(UsuarioPerfilRecurso matriz)
			throws DataException {
		this.delete(matriz.getId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioPerfilRecurso> recuperarMatrizAutorizacoesPorIdRecurso(
			Long idRecurso) throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createQuery(this.getMessage(
				"recupera.matriz.autorizacao.poridrecurso").getText());
		consulta.setHint("eclipselink.join-fetch", "m.perfil");
		consulta.setHint("eclipselink.join-fetch", "m.usuarioAutorizacao");
		consulta.setHint("eclipselink.join-fetch", "m.recurso");
		consulta.setParameter("idRecurso", idRecurso);

		List<UsuarioPerfilRecurso> matriz = null;
		try {
			matriz = consulta.getResultList();
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return matriz;
	}
}
