package spring.sgvas.repository.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CicloAssinaturaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2054031286329587779L;

	@Column(name = "CD_CICLO_ASSINATURA")
	private String cdCicloAssinatura;

	@Column(name = "CD_MOTIVO_ATUALIZACAO", insertable = false, updatable = false)
	private String cdMotivoAtualizacao;

	@Column(name = "CD_ASSINATURA", insertable = false, updatable = false)
	private String cdAssinatura;

	public String getCdCicloAssinatura() {
		return cdCicloAssinatura;
	}

	public void setCdCicloAssinatura(String cdCicloAssinatura) {
		this.cdCicloAssinatura = cdCicloAssinatura;
	}

	public String getCdMotivoAtualizacao() {
		return cdMotivoAtualizacao;
	}

	public void setCdMotivoAtualizacao(String cdMotivoAtualizacao) {
		this.cdMotivoAtualizacao = cdMotivoAtualizacao;
	}

	public String getCdAssinatura() {
		return cdAssinatura;
	}

	public void setCdAssinatura(String cdAssinatura) {
		this.cdAssinatura = cdAssinatura;
	}

}
