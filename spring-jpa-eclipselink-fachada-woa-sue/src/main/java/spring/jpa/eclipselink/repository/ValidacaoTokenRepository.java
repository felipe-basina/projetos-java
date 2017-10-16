package spring.jpa.eclipselink.repository;

import spring.jpa.eclipselink.domain.ValidacaoToken;
import spring.jpa.eclipselink.exception.DataException;

public interface ValidacaoTokenRepository extends
		GenericRepository<ValidacaoToken> {

	ValidacaoToken gerarTokenAutorizacao(ValidacaoToken token)
			throws DataException;

	ValidacaoToken validarTokenAutorizacao(ValidacaoToken token)
			throws DataException;

	void atualizarTokenAutorizacao(ValidacaoToken token,
			boolean atualizarDtExpiracao) throws DataException;
}
