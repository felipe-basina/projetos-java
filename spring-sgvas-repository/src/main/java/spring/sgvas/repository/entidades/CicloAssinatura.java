package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ciclo_assinatura")
public class CicloAssinatura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6849995800113114111L;

	@EmbeddedId
	private CicloAssinaturaPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "USUARIO_CRIACAO")
	private String usuarioCriacao;

	@Column(name = "INFO_TRANSACAO")
	private String infoTransacao;

	@Column(name = "CANAL")
	private String canal;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CD_ASSINATURA", insertable = false, updatable = false),
		@JoinColumn(name = "CD_ASSINANTE", insertable = false, updatable = false),
		@JoinColumn(name = "CD_PRODUTO", insertable = false, updatable = false),
		@JoinColumn(name = "CD_MOTIVO_STATUS", insertable = false, updatable = false)
	})
	private Assinatura assinatura;

	@ManyToOne
	@JoinColumn(name = "CD_MOTIVO_ATUALIZACAO", insertable = false, updatable = false)
	private MotivoAtualizacao motivoAtualizacao;

	public CicloAssinaturaPK getId() {
		return id;
	}

	public void setId(CicloAssinaturaPK id) {
		this.id = id;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public void setUsuarioCriacao(String usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

	public String getInfoTransacao() {
		return infoTransacao;
	}

	public void setInfoTransacao(String infoTransacao) {
		this.infoTransacao = infoTransacao;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public Assinatura getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(Assinatura assinatura) {
		this.assinatura = assinatura;
	}

	public MotivoAtualizacao getMotivoAtualizacao() {
		return motivoAtualizacao;
	}

	public void setMotivoAtualizacao(MotivoAtualizacao motivoAtualizacao) {
		this.motivoAtualizacao = motivoAtualizacao;
	}

}
