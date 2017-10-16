package spring.jpa.eclipselink.repository;

import java.util.List;

import spring.jpa.eclipselink.domain.UsuarioPerfilRecurso;
import spring.jpa.eclipselink.exception.DataException;

public interface UsuarioPerfilRecursoRepository extends
		GenericRepository<UsuarioPerfilRecurso> {

	UsuarioPerfilRecurso cadastrarMatrizAutorizacoes(UsuarioPerfilRecurso matriz)
			throws DataException;

	void removerMatrizAutorizacoes(UsuarioPerfilRecurso matriz)
			throws DataException;

	List<UsuarioPerfilRecurso> recuperarMatrizAutorizacoesPorIdRecurso(
			Long idRecurso) throws DataException;
}
