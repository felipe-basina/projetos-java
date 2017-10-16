package spring.jpa.eclipselink.service;

import spring.jpa.eclipselink.domain.UsuarioAutenticacao;
import spring.jpa.eclipselink.domain.UsuarioAutorizacao;
import spring.jpa.eclipselink.exception.DataException;

public interface ServiceInterface {

	public UsuarioAutenticacao recuperarUsuarioAutenticacao(String login)
			throws DataException;

	public UsuarioAutorizacao listarRecursosUsuario(String login)
			throws DataException;
}
