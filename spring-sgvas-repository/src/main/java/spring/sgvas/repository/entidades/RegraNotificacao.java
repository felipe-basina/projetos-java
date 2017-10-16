package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the regra_notificacao database table.
 * 
 */
@Entity
@Table(name = "regra_notificacao")
@NamedQuery(name = "RegraNotificacao.findAll", query = "SELECT r FROM RegraNotificacao r")
public class RegraNotificacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RegraNotificacaoPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "IS_ATUALIZA_ASSINATURA")
	private String isAtualizaAssinatura;

	@Column(name = "IS_NOTIFICAR")
	private String isNotificar;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	// bi-directional many-to-one association to MotivoAtualizacao
	@ManyToOne
	@JoinColumn(name = "CD_MOTIVO_ATUALIZACAO", insertable = false, updatable = false)
	private MotivoAtualizacao motivoAtualizacao;

	// bi-directional many-to-one association to Sistema
	@ManyToOne
	@JoinColumn(name = "CD_SISTEMA", insertable = false, updatable = false)
	private Sistema sistema;

	// bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name = "CD_PRODUTO", insertable = false, updatable = false)
	private Produto produto;

	public RegraNotificacao() {
	}

	public RegraNotificacaoPK getId() {
		return this.id;
	}

	public void setId(RegraNotificacaoPK id) {
		this.id = id;
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

	public String getIsAtualizaAssinatura() {
		return this.isAtualizaAssinatura;
	}

	public void setIsAtualizaAssinatura(String isAtualizaAssinatura) {
		this.isAtualizaAssinatura = isAtualizaAssinatura;
	}

	public String getIsNotificar() {
		return this.isNotificar;
	}

	public void setIsNotificar(String isNotificar) {
		this.isNotificar = isNotificar;
	}

	public String getUsuarioAtualizacao() {
		return this.usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public MotivoAtualizacao getMotivoAtualizacao() {
		return this.motivoAtualizacao;
	}

	public void setMotivoAtualizacao(MotivoAtualizacao motivoAtualizacao) {
		this.motivoAtualizacao = motivoAtualizacao;
	}

	public Sistema getSistema() {
		return this.sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}