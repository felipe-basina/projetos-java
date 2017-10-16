package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the transacao database table.
 * 
 */
@Entity
@NamedQuery(name = "Transacao.findAll", query = "SELECT t FROM Transacao t")
public class Transacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_TRANSACAO")
	private String cdTransacao;

	@Column(name = "CD_NODE")
	private String cdNode;

	@Column(name = "CD_PRODUTO")
	private int cdProduto;

	@Column(name = "CD_SISTEMA")
	private String cdSistema;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_FIM_TRANSACAO")
	private Date dtFimTransacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_INICIO_TRANSACAO")
	private Date dtInicioTransacao;

	@Column(name = "ENDERECO_ORIGEM")
	private String enderecoOrigem;

	@Column(name = "ERRO_TRANSACAO")
	private String erroTransacao;

	@Column(name = "INFO_TRANSACAO")
	private String infoTransacao;

	@Column(name = "STATUS_TRANSACAO")
	private int statusTransacao;

	public Transacao() {
	}

	public String getCdTransacao() {
		return this.cdTransacao;
	}

	public void setCdTransacao(String cdTransacao) {
		this.cdTransacao = cdTransacao;
	}

	public String getCdNode() {
		return this.cdNode;
	}

	public void setCdNode(String cdNode) {
		this.cdNode = cdNode;
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

	public Date getDtFimTransacao() {
		return this.dtFimTransacao;
	}

	public void setDtFimTransacao(Date dtFimTransacao) {
		this.dtFimTransacao = dtFimTransacao;
	}

	public Date getDtInicioTransacao() {
		return this.dtInicioTransacao;
	}

	public void setDtInicioTransacao(Date dtInicioTransacao) {
		this.dtInicioTransacao = dtInicioTransacao;
	}

	public String getEnderecoOrigem() {
		return this.enderecoOrigem;
	}

	public void setEnderecoOrigem(String enderecoOrigem) {
		this.enderecoOrigem = enderecoOrigem;
	}

	public String getErroTransacao() {
		return this.erroTransacao;
	}

	public void setErroTransacao(String erroTransacao) {
		this.erroTransacao = erroTransacao;
	}

	public String getInfoTransacao() {
		return this.infoTransacao;
	}

	public void setInfoTransacao(String infoTransacao) {
		this.infoTransacao = infoTransacao;
	}

	public int getStatusTransacao() {
		return this.statusTransacao;
	}

	public void setStatusTransacao(int statusTransacao) {
		this.statusTransacao = statusTransacao;
	}

}