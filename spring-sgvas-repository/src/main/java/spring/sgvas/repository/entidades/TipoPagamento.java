package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tipo_pagamento database table.
 * 
 */
@Entity
@Table(name = "tipo_pagamento")
@NamedQuery(name = "TipoPagamento.findAll", query = "SELECT t FROM TipoPagamento t")
public class TipoPagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_TIPO_PAGAMENTO", columnDefinition = "VARCHAR(45)")
	private String cdTipoPagamento;

	@Column(name = "DESC_TIPO_PAGAMENTO")
	private String descTipoPagamento;

	@Column(name = "DT_ATUALIZACAO")
	private String dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	// bi-directional many-to-one association to Pagamento
	@OneToMany(mappedBy = "tipoPagamento")
	private List<Pagamento> pagamentos;

	public TipoPagamento() {
	}

	public String getCdTipoPagamento() {
		return this.cdTipoPagamento;
	}

	public void setCdTipoPagamento(String cdTipoPagamento) {
		this.cdTipoPagamento = cdTipoPagamento;
	}

	public String getDescTipoPagamento() {
		return this.descTipoPagamento;
	}

	public void setDescTipoPagamento(String descTipoPagamento) {
		this.descTipoPagamento = descTipoPagamento;
	}

	public String getDtAtualizacao() {
		return this.dtAtualizacao;
	}

	public void setDtAtualizacao(String dtAtualizacao) {
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

	public List<Pagamento> getPagamentos() {
		return this.pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	/*public Pagamento addPagamento(Pagamento pagamento) {
		getPagamentos().add(pagamento);
		pagamento.setTipoPagamento(this);

		return pagamento;
	}

	public Pagamento removePagamento(Pagamento pagamento) {
		getPagamentos().remove(pagamento);
		pagamento.setTipoPagamento(null);

		return pagamento;
	}*/

}