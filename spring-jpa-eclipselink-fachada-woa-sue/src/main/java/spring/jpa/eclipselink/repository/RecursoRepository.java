package spring.jpa.eclipselink.repository;

import java.util.List;

import spring.jpa.eclipselink.domain.Recurso;
import spring.jpa.eclipselink.domain.RecursoParametro;
import spring.jpa.eclipselink.exception.DataException;

public interface RecursoRepository extends GenericRepository<Recurso> {

	Recurso recuperarRecursoParametros(Long idRecurso) throws DataException;

	Recurso cadastrarRecursoUsuario(Recurso recurso) throws DataException;

	void removerRecursoUsuario(Recurso recurso) throws DataException;

	Recurso recuperarRecursoPorNome(String nomeRecurso) throws DataException;

	Recurso recuperarRecursoSemParametrosPorNome(String nomeRecurso)
			throws DataException;

	Recurso recuperarRecursoPorId(Long idRecurso) throws DataException;

	List<Recurso> listarUsuariosAutorizacaoPorRecursoParametro(
			RecursoParametro recursoParametro) throws DataException;
}
