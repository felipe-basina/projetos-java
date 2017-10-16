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
@Table(name = "controle_notificacao")
public class ControleNotificacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8085702322920346237L;

	@EmbeddedId
	private ControleNotificacaoPK id;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CD_ASSINATURA", insertable = false, updatable = false),
		@JoinColumn(name = "CD_ASSINANTE", insertable = false, updatable = false),
		@JoinColumn(name = "CD_PRODUTO", insertable = false, updatable = false),
		@JoinColumn(name = "CD_MOTIVO_STATUS", insertable = false, updatable = false)
	})
	private Assinatura assinatura;

	@Column(name = "CD_INTEGRACAO")
	private long cdIntegracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_INICIO")
	private Date dtInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_FIM")
	private Date dtFim;

	@Column(name = "STATUS_NOTIFICACAO")
	private long statusNotificacao;

	@Column(name = "CONT_RETENTATIVA")
	private long contRetentativa;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ULTIMA_RETENTATIVA")
	private Date dtUltimaRetentativa;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	@Column(name = "INFO_TRANSACAO")
	private String infoTransacao;

	@Column(name = "ERROR")
	private String error;

	public ControleNotificacaoPK getId() {
		return id;
	}

	public void setId(ControleNotificacaoPK id) {
		this.id = id;
	}

	public Assinatura getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(Assinatura assinatura) {
		this.assinatura = assinatura;
	}

	public long getCdIntegracao() {
		return cdIntegracao;
	}

	public void setCdIntegracao(long cdIntegracao) {
		this.cdIntegracao = cdIntegracao;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public long getStatusNotificacao() {
		return statusNotificacao;
	}

	public void setStatusNotificacao(long statusNotificacao) {
		this.statusNotificacao = statusNotificacao;
	}

	public long getContRetentativa() {
		return contRetentativa;
	}

	public void setContRetentativa(long contRetentativa) {
		this.contRetentativa = contRetentativa;
	}

	public Date getDtUltimaRetentativa() {
		return dtUltimaRetentativa;
	}

	public void setDtUltimaRetentativa(Date dtUltimaRetentativa) {
		this.dtUltimaRetentativa = dtUltimaRetentativa;
	}

	public String getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public String getInfoTransacao() {
		return infoTransacao;
	}

	public void setInfoTransacao(String infoTransacao) {
		this.infoTransacao = infoTransacao;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
