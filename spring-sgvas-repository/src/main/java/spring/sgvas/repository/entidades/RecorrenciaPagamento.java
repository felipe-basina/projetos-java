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
 * The persistent class for the recorrencia_pagamento database table.
 * 
 */
@Entity
@Table(name = "recorrencia_pagamento")
@NamedQuery(name = "RecorrenciaPagamento.findAll", query = "SELECT r FROM RecorrenciaPagamento r")
public class RecorrenciaPagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_RECORRENCIA_PAGAMENTO")
	private int cdRecorrenciaPagamento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "MAX_TARIFACAO_INTERVALO")
	private String maxTarifacaoIntervalo;

	@Column(name = "QTY_MAX_TARIFACAO")
	private String qtyMaxTarifacao;

	@Column(name = "TIPO_INTERVALO_TARIFACAO")
	private String tipoIntervaloTarifacao;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	// bi-directional many-to-one association to Pagamento
	@OneToMany(mappedBy = "recorrenciaPagamento")
	private List<Pagamento> pagamentos;

	public RecorrenciaPagamento() {
	}

	public int getCdRecorrenciaPagamento() {
		return this.cdRecorrenciaPagamento;
	}

	public void setCdRecorrenciaPagamento(int cdRecorrenciaPagamento) {
		this.cdRecorrenciaPagamento = cdRecorrenciaPagamento;
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

	public String getMaxTarifacaoIntervalo() {
		return this.maxTarifacaoIntervalo;
	}

	public void setMaxTarifacaoIntervalo(String maxTarifacaoIntervalo) {
		this.maxTarifacaoIntervalo = maxTarifacaoIntervalo;
	}

	public String getQtyMaxTarifacao() {
		return this.qtyMaxTarifacao;
	}

	public void setQtyMaxTarifacao(String qtyMaxTarifacao) {
		this.qtyMaxTarifacao = qtyMaxTarifacao;
	}

	public String getTipoIntervaloTarifacao() {
		return this.tipoIntervaloTarifacao;
	}

	public void setTipoIntervaloTarifacao(String tipoIntervaloTarifacao) {
		this.tipoIntervaloTarifacao = tipoIntervaloTarifacao;
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
		pagamento.setRecorrenciaPagamento(this);

		return pagamento;
	}

	public Pagamento removePagamento(Pagamento pagamento) {
		getPagamentos().remove(pagamento);
		pagamento.setRecorrenciaPagamento(null);

		return pagamento;
	}*/

}