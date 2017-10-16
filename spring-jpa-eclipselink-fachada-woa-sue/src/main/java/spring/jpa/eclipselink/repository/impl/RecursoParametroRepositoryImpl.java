package spring.jpa.eclipselink.repository.impl;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.RecursoParametro;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.repository.RecursoParametroRepository;

@Repository("recursoParametroRepositoryImpl")
public class RecursoParametroRepositoryImpl extends
		GenericRepositoryImpl<RecursoParametro> implements
		RecursoParametroRepository {

	@Override
	public void removerRecursoParametro(RecursoParametro recursoParametro)
			throws DataException {
		this.delete(recursoParametro.getId());
	}
}
