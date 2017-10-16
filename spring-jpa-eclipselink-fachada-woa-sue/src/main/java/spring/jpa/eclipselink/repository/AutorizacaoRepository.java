package spring.jpa.eclipselink.repository;

import spring.jpa.eclipselink.domain.UsuarioAutorizacao;
import spring.jpa.eclipselink.exception.DataException;

public interface AutorizacaoRepository extends
		GenericRepository<UsuarioAutorizacao> {

	UsuarioAutorizacao buscarUsuarioPorLogin(String login) throws DataException;

	UsuarioAutorizacao listarRecursosUsuario(String login) throws DataException;

	void removerUsuario(Long idUsuario) throws DataException;
}
