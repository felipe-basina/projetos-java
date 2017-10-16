package spring.sgvas.repository.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the pagamento database table.
 * 
 */
@Embeddable
public class PagamentoPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "CD_PAGAMENTO")
	private long cdPagamento;
	
	@Column(name = "CD_TIPO_PAGAMENTO", insertable = false, updatable = false)
	private String cdTipoPagamento;

	@Column(name = "CD_PRODUTO", insertable = false, updatable = false)
	private long cdProduto;

	@Column(name = "CD_RECORRENCIA_PAGAMENTO", insertable = false, updatable = false)
	private long cdRecorrenciaPagamento;

	public PagamentoPK() {
	}

	public String getCdTipoPagamento() {
		return this.cdTipoPagamento;
	}

	public void setCdTipoPagamento(String cdTipoPagamento) {
		this.cdTipoPagamento = cdTipoPagamento;
	}

	public long getCdProduto() {
		return this.cdProduto;
	}

	public void setCdProduto(long cdProduto) {
		this.cdProduto = cdProduto;
	}

	public long getCdRecorrenciaPagamento() {
		return this.cdRecorrenciaPagamento;
	}

	public void setCdRecorrenciaPagamento(long cdRecorrenciaPagamento) {
		this.cdRecorrenciaPagamento = cdRecorrenciaPagamento;
	}
}