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
 * The persistent class for the motivo_atualizacao database table.
 * 
 */
@Entity
@Table(name = "motivo_atualizacao")
@NamedQuery(name = "MotivoAtualizacao.findAll", query = "SELECT m FROM MotivoAtualizacao m")
public class MotivoAtualizacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_MOTIVO_ATUALIZACAO")
	private String cdMotivoAtualizacao;

	@Column(name = "DES_MOTIVO_ATUALIZACAO")
	private String desMotivoAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	// bi-directional many-to-one association to RegraNotificacao
	@OneToMany(mappedBy = "motivoAtualizacao")
	private List<RegraNotificacao> regraNotificacaos;

	@OneToMany(mappedBy = "motivoAtualizacao")
	private List<CicloAssinatura> cicloAssinatura;

	public MotivoAtualizacao() {
	}

	public String getCdMotivoAtualizacao() {
		return this.cdMotivoAtualizacao;
	}

	public void setCdMotivoAtualizacao(String cdMotivoAtualizacao) {
		this.cdMotivoAtualizacao = cdMotivoAtualizacao;
	}

	public String getDesMotivoAtualizacao() {
		return this.desMotivoAtualizacao;
	}

	public void setDesMotivoAtualizacao(String desMotivoAtualizacao) {
		this.desMotivoAtualizacao = desMotivoAtualizacao;
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

	public String getUsuarioAtualizacao() {
		return this.usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public List<RegraNotificacao> getRegraNotificacaos() {
		return this.regraNotificacaos;
	}

	public void setRegraNotificacaos(List<RegraNotificacao> regraNotificacaos) {
		this.regraNotificacaos = regraNotificacaos;
	}

	public RegraNotificacao addRegraNotificacao(
			RegraNotificacao regraNotificacao) {
		getRegraNotificacaos().add(regraNotificacao);
		regraNotificacao.setMotivoAtualizacao(this);

		return regraNotificacao;
	}

	public RegraNotificacao removeRegraNotificacao(
			RegraNotificacao regraNotificacao) {
		getRegraNotificacaos().remove(regraNotificacao);
		regraNotificacao.setMotivoAtualizacao(null);

		return regraNotificacao;
	}

	public List<CicloAssinatura> getCicloAssinatura() {
		return cicloAssinatura;
	}

	public void setCicloAssinatura(List<CicloAssinatura> cicloAssinatura) {
		this.cicloAssinatura = cicloAssinatura;
	}

}