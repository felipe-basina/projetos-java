package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the motivo_status database table.
 * 
 */
@Entity
@Table(name = "motivo_status")
@NamedQuery(name = "MotivoStatus.findAll", query = "SELECT m FROM MotivoStatus m")
public class MotivoStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_MOTIVO_STATUS")
	private int cdMotivoStatus;

	@Column(name = "DESC_MOTIVO_STATUS")
	private String descMotivoStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "MOTIVO_STATUS")
	private String motivoStatus;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	@OneToMany(mappedBy = "motivoStatus")
	private List<Assinatura> assinatura;

	public MotivoStatus() {
	}

	public int getCdMotivoStatus() {
		return this.cdMotivoStatus;
	}

	public void setCdMotivoStatus(int cdMotivoStatus) {
		this.cdMotivoStatus = cdMotivoStatus;
	}

	public String getDescMotivoStatus() {
		return this.descMotivoStatus;
	}

	public void setDescMotivoStatus(String descMotivoStatus) {
		this.descMotivoStatus = descMotivoStatus;
	}

	public Date getDtAtualizacao() {
		return this.dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getMotivoStatus() {
		return this.motivoStatus;
	}

	public void setMotivoStatus(String motivoStatus) {
		this.motivoStatus = motivoStatus;
	}

	public String getUsuarioAtualizacao() {
		return this.usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public List<Assinatura> getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(List<Assinatura> assinatura) {
		this.assinatura = assinatura;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MotivoStatus [cdMotivoStatus=");
		builder.append(cdMotivoStatus);
		builder.append(", descMotivoStatus=");
		builder.append(descMotivoStatus);
		builder.append(", dtAtualizacao=");
		builder.append(dtAtualizacao);
		builder.append(", dtCriacao=");
		builder.append(dtCriacao);
		builder.append(", motivoStatus=");
		builder.append(motivoStatus);
		builder.append(", usuarioAtualizacao=");
		builder.append(usuarioAtualizacao);
		builder.append("]");
		return builder.toString();
	}

}