package spring.jpa.query.methods.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PerfilPermissaoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CD_PERFIL", insertable = false, updatable = false)
	private long cdPerfil;

	@Column(name = "CD_ACAO", insertable = false, updatable = false)
	private long cdPermissao;

	public long getCdPerfil() {
		return cdPerfil;
	}

	public void setCdPerfil(long cdPerfil) {
		this.cdPerfil = cdPerfil;
	}

	public long getCdPermissao() {
		return cdPermissao;
	}

	public void setCdPermissao(long cdPermissao) {
		this.cdPermissao = cdPermissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cdPerfil ^ (cdPerfil >>> 32));
		result = prime * result + (int) (cdPermissao ^ (cdPermissao >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilPermissaoPK other = (PerfilPermissaoPK) obj;
		if (cdPerfil != other.cdPerfil)
			return false;
		if (cdPermissao != other.cdPermissao)
			return false;
		return true;
	}

}
