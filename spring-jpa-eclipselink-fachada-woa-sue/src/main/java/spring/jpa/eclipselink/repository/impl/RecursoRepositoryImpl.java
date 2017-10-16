package spring.jpa.eclipselink.repository.impl;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.Recurso;
import spring.jpa.eclipselink.domain.RecursoParametro;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.repository.RecursoRepository;

@Repository("recursoRepositoryImpl")
public class RecursoRepositoryImpl extends GenericRepositoryImpl<Recurso>
		implements RecursoRepository {

	@Override
	public Recurso cadastrarRecursoUsuario(Recurso recurso)
			throws DataException {
		return this.create(recurso);
	}

	@Override
	public void removerRecursoUsuario(Recurso recurso) throws DataException {
		this.delete(recurso.getId());
	}

	@Override
	public Recurso recuperarRecursoParametros(Long idRecurso)
			throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createQuery(this.getMessage(
				"recupera.recurso.parametros").getText());
		consulta.setParameter("idRecurso", idRecurso);

		Recurso recurso = null;
		try {
			recurso = (Recurso) consulta.getSingleResult();
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return recurso;
	}

	@Override
	public Recurso recuperarRecursoPorNome(String nomeRecurso)
			throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createQuery(this.getMessage(
				"recupera.recurso.pornome").getText());
		consulta.setParameter("nomeRecurso", nomeRecurso.toUpperCase());

		Recurso recurso = null;
		try {
			recurso = (Recurso) consulta.getResultList().get(0);
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return recurso;
	}

	@Override
	public Recurso recuperarRecursoSemParametrosPorNome(String nomeRecurso)
			throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createQuery(this.getMessage(
				"recupera.recurso.pornome.semparametros").getText());
		consulta.setParameter("nomeRecurso", nomeRecurso.toUpperCase());

		Recurso recurso = null;
		try {
			recurso = (Recurso) consulta.getResultList().get(0);
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return recurso;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recurso> listarUsuariosAutorizacaoPorRecursoParametro(
			RecursoParametro recursoParametro) throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createQuery(this.getMessage(
				"recupera.recursos.autorizacao.recursoparametro").getText());
		consulta.setHint("eclipselink.join-fetch",
				"r.matrizAutorizacoes.perfil");
		consulta.setHint("eclipselink.join-fetch",
				"r.matrizAutorizacoes.usuarioAutorizacao");
		consulta.setParameter("nomeParametroRecurso", recursoParametro
				.getNoParametroRecurso().toUpperCase());
		consulta.setParameter("valorParametroRecurso", recursoParametro
				.getVlParametroRecurso().trim());

		List<Recurso> recursos = null;
		try {
			recursos = (Vector<Recurso>) consulta.getResultList();
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return recursos;
	}

	@Override
	public Recurso recuperarRecursoPorId(Long idRecurso) throws DataException {
		return this.find(idRecurso);
	}
}
