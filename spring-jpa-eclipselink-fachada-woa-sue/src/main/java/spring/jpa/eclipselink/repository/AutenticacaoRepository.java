package spring.jpa.eclipselink.repository;

import java.util.List;
import java.util.Set;

import spring.jpa.eclipselink.domain.UsuarioAutenticacao;
import spring.jpa.eclipselink.exception.DataException;

public interface AutenticacaoRepository extends
		GenericRepository<UsuarioAutenticacao> {

	UsuarioAutenticacao buscarUsuarioPorLogin(String login)
			throws DataException;

	List<UsuarioAutenticacao> buscarUsuariosPorLogin(Set<String> logins)
			throws DataException;

	UsuarioAutenticacao buscarUsuarioTemporarioPorLogin(String loginTemp)
			throws DataException;
}
