package spring.sgvas.repository.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AssinaturaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5016585780563870701L;

	@Column(name = "CD_ASSINATURA")
	private String cdAssinatura;

	@Column(name = "CD_ASSINANTE", insertable = false, updatable = false)
	private String cdAssinante;

	@Column(name = "CD_PRODUTO", insertable = false, updatable = false)
	private long cdProduto;

	@Column(name = "CD_MOTIVO_STATUS", insertable = false, updatable = false)
	private long cdMotivoStatus;

	public String getCdAssinatura() {
		return cdAssinatura;
	}

	public void setCdAssinatura(String cdAssinatura) {
		this.cdAssinatura = cdAssinatura;
	}

	public String getCdAssinante() {
		return cdAssinante;
	}

	public void setCdAssinante(String cdAssinante) {
		this.cdAssinante = cdAssinante;
	}

	public long getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(long cdProduto) {
		this.cdProduto = cdProduto;
	}

	public long getCdMotivoStatus() {
		return cdMotivoStatus;
	}

	public void setCdMotivoStatus(long cdMotivoStatus) {
		this.cdMotivoStatus = cdMotivoStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AssinaturaPK [cdAssinatura=");
		builder.append(cdAssinatura);
		builder.append(", cdAssinante=");
		builder.append(cdAssinante);
		builder.append(", cdProduto=");
		builder.append(cdProduto);
		builder.append(", cdMotivoStatus=");
		builder.append(cdMotivoStatus);
		builder.append("]");
		return builder.toString();
	}

}
