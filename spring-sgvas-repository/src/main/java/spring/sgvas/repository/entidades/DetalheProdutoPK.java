package spring.sgvas.repository.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the detalhe_produto database table.
 * 
 */
@Embeddable
public class DetalheProdutoPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "CD_DETALHE_PRODUTO")
	private int cdDetalheProduto;

	@Column(name = "CD_PRODUTO", insertable = false, updatable = false)
	private int cdProduto;

	@Column(name = "NO_PARAMETRO_PRODUTO", insertable = false, updatable = false)
	private String noParametroProduto;

	public DetalheProdutoPK() {
	}

	public int getCdDetalheProduto() {
		return this.cdDetalheProduto;
	}

	public void setCdDetalheProduto(int cdDetalheProduto) {
		this.cdDetalheProduto = cdDetalheProduto;
	}

	public int getCdProduto() {
		return this.cdProduto;
	}

	public void setCdProduto(int cdProduto) {
		this.cdProduto = cdProduto;
	}

	public String getNoParametroProduto() {
		return this.noParametroProduto;
	}

	public void setNoParametroProduto(String noParametroProduto) {
		this.noParametroProduto = noParametroProduto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetalheProdutoPK)) {
			return false;
		}
		DetalheProdutoPK castOther = (DetalheProdutoPK) other;
		return (this.cdDetalheProduto == castOther.cdDetalheProduto)
				&& (this.cdProduto == castOther.cdProduto)
				&& this.noParametroProduto.equals(castOther.noParametroProduto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdDetalheProduto;
		hash = hash * prime + this.cdProduto;
		hash = hash * prime + this.noParametroProduto.hashCode();

		return hash;
	}
}