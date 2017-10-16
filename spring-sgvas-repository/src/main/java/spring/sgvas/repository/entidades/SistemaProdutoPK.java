package spring.sgvas.repository.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the sistema_produto database table.
 * 
 */
@Embeddable
public class SistemaProdutoPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "CD_SISTEMA", insertable = false, updatable = false)
	private String cdSistema;

	@Column(name = "CD_PRODUTO", insertable = false, updatable = false)
	private int cdProduto;

	public SistemaProdutoPK() {
	}

	public String getCdSistema() {
		return this.cdSistema;
	}

	public void setCdSistema(String cdSistema) {
		this.cdSistema = cdSistema;
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
		if (!(other instanceof SistemaProdutoPK)) {
			return false;
		}
		SistemaProdutoPK castOther = (SistemaProdutoPK) other;
		return this.cdSistema.equals(castOther.cdSistema)
				&& (this.cdProduto == castOther.cdProduto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdSistema.hashCode();
		hash = hash * prime + this.cdProduto;

		return hash;
	}
}