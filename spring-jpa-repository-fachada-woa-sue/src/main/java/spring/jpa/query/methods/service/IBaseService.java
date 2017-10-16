package spring.jpa.query.methods.service;

import java.util.List;

import spring.jpa.query.methods.model.UsuarioAutenticacao;
import spring.jpa.query.methods.model.UsuarioAutorizacao;

public interface IBaseService {

	UsuarioAutenticacao findByLogin(String login);

	List<UsuarioAutorizacao> findByEmail(String email);
}
