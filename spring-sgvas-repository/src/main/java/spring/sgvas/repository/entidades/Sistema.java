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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the sistema database table.
 * 
 */
@Entity
@NamedQuery(name = "Sistema.findAll", query = "SELECT s FROM Sistema s")
public class Sistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_SISTEMA")
	private String cdSistema;

	private String cnpj;

	@Column(name = "COD_SAP")
	private int codSap;

	@Column(name = "DESC_PARCEIRO")
	private String descParceiro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "NO_SISTEMA")
	private String noSistema;

	private String status;

	@Column(name = "TIPO_SISTEMA")
	private String tipoSistema;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	// bi-directional many-to-one association to RegraNotificacao
	@OneToMany(mappedBy = "sistema")
	private List<RegraNotificacao> regraNotificacaos;

	// bi-directional many-to-one association to SistemaProduto
	@OneToMany(mappedBy = "sistema")
	private List<SistemaProduto> sistemaProdutos;

	public Sistema() {
	}

	public String getCdSistema() {
		return this.cdSistema;
	}

	public void setCdSistema(String cdSistema) {
		this.cdSistema = cdSistema;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public int getCodSap() {
		return this.codSap;
	}

	public void setCodSap(int codSap) {
		this.codSap = codSap;
	}

	public String getDescParceiro() {
		return this.descParceiro;
	}

	public void setDescParceiro(String descParceiro) {
		this.descParceiro = descParceiro;
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

	public String getNoSistema() {
		return this.noSistema;
	}

	public void setNoSistema(String noSistema) {
		this.noSistema = noSistema;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipoSistema() {
		return this.tipoSistema;
	}

	public void setTipoSistema(String tipoSistema) {
		this.tipoSistema = tipoSistema;
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
		regraNotificacao.setSistema(this);

		return regraNotificacao;
	}

	public RegraNotificacao removeRegraNotificacao(
			RegraNotificacao regraNotificacao) {
		getRegraNotificacaos().remove(regraNotificacao);
		regraNotificacao.setSistema(null);

		return regraNotificacao;
	}

	public List<SistemaProduto> getSistemaProdutos() {
		return this.sistemaProdutos;
	}

	public void setSistemaProdutos(List<SistemaProduto> sistemaProdutos) {
		this.sistemaProdutos = sistemaProdutos;
	}

	public SistemaProduto addSistemaProduto(SistemaProduto sistemaProduto) {
		getSistemaProdutos().add(sistemaProduto);
		sistemaProduto.setSistema(this);

		return sistemaProduto;
	}

	public SistemaProduto removeSistemaProduto(SistemaProduto sistemaProduto) {
		getSistemaProdutos().remove(sistemaProduto);
		sistemaProduto.setSistema(null);

		return sistemaProduto;
	}

}