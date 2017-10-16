package spring.jpa.query.methods.repository;

import spring.jpa.query.methods.model.UsuarioAutenticacao;

public interface UsuarioAutenticacaoRepository extends
		BaseRepository<UsuarioAutenticacao, Long> {

	UsuarioAutenticacao findByLogin(String login);
}