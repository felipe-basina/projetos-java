package spring.jpa.eclipselink.repository;

import spring.jpa.eclipselink.domain.RecursoParametro;
import spring.jpa.eclipselink.exception.DataException;

public interface RecursoParametroRepository extends
		GenericRepository<RecursoParametro> {

	void removerRecursoParametro(RecursoParametro recursoParametro)
			throws DataException;

}
