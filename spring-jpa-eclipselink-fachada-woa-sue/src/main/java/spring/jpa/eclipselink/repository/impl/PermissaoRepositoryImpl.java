package spring.jpa.eclipselink.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.Permissao;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.repository.PermissaoRepository;

@Repository("permissaoRepositoryImpl")
public class PermissaoRepositoryImpl extends GenericRepositoryImpl<Permissao>
		implements PermissaoRepository {

	@Override
	public Permissao recuperarPermissaoParametros(Long idPermissao)
			throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createQuery(this.getMessage(
				"recupera.permissao.parametros").getText());
		consulta.setParameter("idPermissao", idPermissao);

		Permissao permissao = null;
		try {
			permissao = (Permissao) consulta.getSingleResult();
		} catch (Exception ex) {
			this.logException(ex);
			return null;
		}

		return permissao;
	}

}
