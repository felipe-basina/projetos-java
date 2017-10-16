package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the pagamento database table.
 * 
 */
@Entity
@Table(name = "pagamento")
@NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p")
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@MapsId(value = "CD_PAGAMENTO")
	private PagamentoPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	// bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name = "CD_PRODUTO", insertable = false, updatable = false)
	private Produto produto;

	// bi-directional many-to-one association to TipoPagamento
	@ManyToOne
	@JoinColumn(name = "CD_TIPO_PAGAMENTO", insertable = false, updatable = false)
	private TipoPagamento tipoPagamento;

	// bi-directional many-to-one association to RecorrenciaPagamento
	@ManyToOne
	@JoinColumn(name = "CD_RECORRENCIA_PAGAMENTO", insertable = false, updatable = false)
	private RecorrenciaPagamento recorrenciaPagamento;
	
	public Pagamento() {
	}

	public PagamentoPK getId() {
		return this.id;
	}

	public void setId(PagamentoPK id) {
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

	public String getUsuarioAtualizacao() {
		return this.usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoPagamento getTipoPagamento() {
		return this.tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public RecorrenciaPagamento getRecorrenciaPagamento() {
		return this.recorrenciaPagamento;
	}

	public void setRecorrenciaPagamento(
			RecorrenciaPagamento recorrenciaPagamento) {
		this.recorrenciaPagamento = recorrenciaPagamento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pagamento [id=");
		builder.append(id);
		builder.append(", dtAtualizacao=");
		builder.append(dtAtualizacao);
		builder.append(", dtCriacao=");
		builder.append(dtCriacao);
		builder.append(", usuarioAtualizacao=");
		builder.append(usuarioAtualizacao);
		builder.append(", produto=");
		builder.append(produto);
		builder.append(", tipoPagamento=");
		builder.append(tipoPagamento);
		builder.append(", recorrenciaPagamento=");
		builder.append(recorrenciaPagamento);
		builder.append("]");
		return builder.toString();
	}

}