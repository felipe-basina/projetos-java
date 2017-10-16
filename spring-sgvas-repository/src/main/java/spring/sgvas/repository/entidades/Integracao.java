package spring.sgvas.repository.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the integracao database table.
 * 
 */
@Entity
@NamedQuery(name = "Integracao.findAll", query = "SELECT i FROM Integracao i")
public class Integracao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IntegracaoPK id;

	private String ativo;

	@Column(name = "BLACKLIST_PARAMETRO")
	private String blacklistParametro;

	@Column(name = "CD_MOTIVO_ATUALIZACAO")
	private String cdMotivoAtualizacao;

	@Column(name = "DT_ATUALIZACAO")
	private String dtAtualizacao;

	@Column(name = "DT_CRIACAO")
	private String dtCriacao;

	@Column(name = "INTERVALO_RETENTATIVA")
	private String intervaloRetentativa;

	@Column(name = "MAX_RETENTATIVA")
	private String maxRetentativa;

	@Column(name = "TIME_OUT")
	private int timeOut;

	@Column(name = "TIPO_NOTIFICACAO")
	private String tipoNotificacao;

	private String url;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	@Column(name = "VERSAO_INTERFACE")
	private String versaoInterface;

	// bi-directional many-to-one association to SistemaProduto
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CD_PRODUTO", referencedColumnName = "CD_PRODUTO", insertable = false, updatable = false),
			@JoinColumn(name = "CD_SISTEMA", referencedColumnName = "CD_SISTEMA", insertable = false, updatable = false) })
	private SistemaProduto sistemaProduto;

	public Integracao() {
	}

	public IntegracaoPK getId() {
		return this.id;
	}

	public void setId(IntegracaoPK id) {
		this.id = id;
	}

	public String getAtivo() {
		return this.ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getBlacklistParametro() {
		return this.blacklistParametro;
	}

	public void setBlacklistParametro(String blacklistParametro) {
		this.blacklistParametro = blacklistParametro;
	}

	public String getCdMotivoAtualizacao() {
		return this.cdMotivoAtualizacao;
	}

	public void setCdMotivoAtualizacao(String cdMotivoAtualizacao) {
		this.cdMotivoAtualizacao = cdMotivoAtualizacao;
	}

	public String getDtAtualizacao() {
		return this.dtAtualizacao;
	}

	public void setDtAtualizacao(String dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public String getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(String dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getIntervaloRetentativa() {
		return this.intervaloRetentativa;
	}

	public void setIntervaloRetentativa(String intervaloRetentativa) {
		this.intervaloRetentativa = intervaloRetentativa;
	}

	public String getMaxRetentativa() {
		return this.maxRetentativa;
	}

	public void setMaxRetentativa(String maxRetentativa) {
		this.maxRetentativa = maxRetentativa;
	}

	public int getTimeOut() {
		return this.timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public String getTipoNotificacao() {
		return this.tipoNotificacao;
	}

	public void setTipoNotificacao(String tipoNotificacao) {
		this.tipoNotificacao = tipoNotificacao;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsuarioAtualizacao() {
		return this.usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public String getVersaoInterface() {
		return this.versaoInterface;
	}

	public void setVersaoInterface(String versaoInterface) {
		this.versaoInterface = versaoInterface;
	}

	public SistemaProduto getSistemaProduto() {
		return this.sistemaProduto;
	}

	public void setSistemaProduto(SistemaProduto sistemaProduto) {
		this.sistemaProduto = sistemaProduto;
	}

}