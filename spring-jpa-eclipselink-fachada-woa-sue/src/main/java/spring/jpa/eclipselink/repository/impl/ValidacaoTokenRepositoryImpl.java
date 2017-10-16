package spring.jpa.eclipselink.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.ValidacaoToken;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.repository.ValidacaoTokenRepository;

@Repository("validacaoTokenRepositoryImpl")
public class ValidacaoTokenRepositoryImpl extends
		GenericRepositoryImpl<ValidacaoToken> implements
		ValidacaoTokenRepository {

	@Override
	public ValidacaoToken gerarTokenAutorizacao(ValidacaoToken token)
			throws DataException {
		return this.create(token);
	}

	@Override
	public ValidacaoToken validarTokenAutorizacao(ValidacaoToken token)
			throws DataException {

		EntityManager em = this.getEntityManager();

		Query consulta = em.createNativeQuery(
				this.getMessage("recupera.token.autororizacao").getText(),
				ValidacaoToken.class);
		consulta.setParameter(1, token.getChaveToken());

		try {
			@SuppressWarnings("unchecked")
			List<ValidacaoToken> lista = consulta.getResultList();

			if (lista != null && lista.size() > 0) {
				return lista.get(0);
			}
		} catch (Exception ex) {
			throw new DataException(ex);
		}

		return null;
	}

	@Override
	public void atualizarTokenAutorizacao(ValidacaoToken token,
			boolean atualizarDtExpiracao) throws DataException {

		EntityManager em = this.getEntityManager();

		String atualizacao = this.getMessage("atualiza.token.autorizacao")
				.getText();

		if (atualizarDtExpiracao) {
			atualizacao = this.getMessage(
					"atualiza.token.autorizacao.dataexpiracao").getText();
		}

		Query operacao = em.createNativeQuery(atualizacao);
		operacao.setParameter(1, token.getQtTentativasIncorreta());
		operacao.setParameter(2, token.getDtExpiracao());
		operacao.setParameter(3, token.getChaveToken());

		try {
			operacao.executeUpdate();
		} catch (Exception ex) {
			throw new DataException(ex);
		}
	}

}
