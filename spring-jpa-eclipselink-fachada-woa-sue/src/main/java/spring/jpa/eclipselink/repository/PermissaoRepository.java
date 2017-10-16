package spring.jpa.eclipselink.repository;

import spring.jpa.eclipselink.domain.Permissao;
import spring.jpa.eclipselink.exception.DataException;

public interface PermissaoRepository extends GenericRepository<Permissao> {

	Permissao recuperarPermissaoParametros(Long idPermissao)
			throws DataException;
}
