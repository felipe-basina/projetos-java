package spring.sgvas.repository.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ControleNotificacaoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1811433791661409205L;

	@Column(name = "CD_CONTROLE_NOTIFICACAO")
	private String cdControleNotificacao;

	@Column(name = "CD_ASSINATURA", insertable = false, updatable = false)
	private String cdAssinatura;

	public String getCdControleNotificacao() {
		return cdControleNotificacao;
	}

	public void setCdControleNotificacao(String cdControleNotificacao) {
		this.cdControleNotificacao = cdControleNotificacao;
	}

	public String getCdAssinatura() {
		return cdAssinatura;
	}

	public void setCdAssinatura(String cdAssinatura) {
		this.cdAssinatura = cdAssinatura;
	}

}
