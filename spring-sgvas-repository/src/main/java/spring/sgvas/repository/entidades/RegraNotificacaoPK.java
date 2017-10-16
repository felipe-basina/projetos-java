package spring.sgvas.repository.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the regra_notificacao database table.
 * 
 */
@Embeddable
public class RegraNotificacaoPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "CD_MOTIVO_ATUALIZACAO", insertable = false, updatable = false)
	private String cdMotivoAtualizacao;

	@Column(name = "CD_PRODUTO", insertable = false, updatable = false)
	private int cdProduto;

	@Column(name = "CD_SISTEMA", insertable = false, updatable = false)
	private String cdSistema;

	public RegraNotificacaoPK() {
	}

	public String getCdMotivoAtualizacao() {
		return this.cdMotivoAtualizacao;
	}

	public void setCdMotivoAtualizacao(String cdMotivoAtualizacao) {
		this.cdMotivoAtualizacao = cdMotivoAtualizacao;
	}

	public int getCdProduto() {
		return this.cdProduto;
	}

	public void setCdProduto(int cdProduto) {
		this.cdProduto = cdProduto;
	}

	public String getCdSistema() {
		return this.cdSistema;
	}

	public void setCdSistema(String cdSistema) {
		this.cdSistema = cdSistema;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RegraNotificacaoPK)) {
			return false;
		}
		RegraNotificacaoPK castOther = (RegraNotificacaoPK) other;
		return this.cdMotivoAtualizacao.equals(castOther.cdMotivoAtualizacao)
				&& (this.cdProduto == castOther.cdProduto)
				&& this.cdSistema.equals(castOther.cdSistema);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdMotivoAtualizacao.hashCode();
		hash = hash * prime + this.cdProduto;
		hash = hash * prime + this.cdSistema.hashCode();

		return hash;
	}
}