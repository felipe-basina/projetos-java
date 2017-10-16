package spring.sgvas.repository.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the elegibilidade database table.
 * 
 */
@Embeddable
public class ElegibilidadePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "CD_ELEGIBILIDADE")
	private int cdElegibilidade;

	@Column(name = "CD_PRODUTO", insertable = false, updatable = false)
	private int cdProduto;

	public ElegibilidadePK() {
	}

	public int getCdElegibilidade() {
		return this.cdElegibilidade;
	}

	public void setCdElegibilidade(int cdElegibilidade) {
		this.cdElegibilidade = cdElegibilidade;
	}

	public int getCdProduto() {
		return this.cdProduto;
	}

	public void setCdProduto(int cdProduto) {
		this.cdProduto = cdProduto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ElegibilidadePK)) {
			return false;
		}
		ElegibilidadePK castOther = (ElegibilidadePK) other;
		return (this.cdElegibilidade == castOther.cdElegibilidade)
				&& (this.cdProduto == castOther.cdProduto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdElegibilidade;
		hash = hash * prime + this.cdProduto;

		return hash;
	}
}