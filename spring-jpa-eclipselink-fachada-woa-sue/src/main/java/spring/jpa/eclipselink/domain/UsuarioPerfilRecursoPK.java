package spring.jpa.eclipselink.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioPerfilRecursoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "CD_USUARIO", insertable = false, updatable = false)
	private long cdUsuario;

	@Column(name = "CD_PERFIL", insertable = false, updatable = false)
	private long cdPerfil;

	@Column(name = "CD_RECURSO", insertable = false, updatable = false)
	private long cdRecurso;

	public long getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(long cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public long getCdPerfil() {
		return cdPerfil;
	}

	public void setCdPerfil(long cdPerfil) {
		this.cdPerfil = cdPerfil;
	}

	public long getCdRecurso() {
		return cdRecurso;
	}

	public void setCdRecurso(long cdRecurso) {
		this.cdRecurso = cdRecurso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cdPerfil ^ (cdPerfil >>> 32));
		result = prime * result + (int) (cdRecurso ^ (cdRecurso >>> 32));
		result = prime * result + (int) (cdUsuario ^ (cdUsuario >>> 32));
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
		UsuarioPerfilRecursoPK other = (UsuarioPerfilRecursoPK) obj;
		if (cdPerfil != other.cdPerfil)
			return false;
		if (cdRecurso != other.cdRecurso)
			return false;
		if (cdUsuario != other.cdUsuario)
			return false;
		return true;
	}

}
