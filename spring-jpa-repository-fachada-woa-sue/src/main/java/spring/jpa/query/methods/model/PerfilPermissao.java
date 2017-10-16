package spring.jpa.query.methods.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "perfilPermissao")
@Table(name = "SUE_MATRIZ_PERMISSOES_PERFIL")
public class PerfilPermissao implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PerfilPermissaoPK id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_PERFIL")
	@MapsId("cdPerfil")
	private Perfil perfil;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_ACAO")
	@MapsId("cdPermissao")
	private Permissao permissao;

	/** The creation time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date creationDate;

	/** The update time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date updateDate;

	@Column(name = "CD_USUARIO_CRIACAO")
	private String usuarioCriacao;

	@Column(name = "CD_USUARIO_MANUT")
	private String usuarioManutencao;

	public PerfilPermissaoPK getId() {
		return id;
	}

	public void setId(PerfilPermissaoPK id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public void setUsuarioCriacao(String usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

	public String getUsuarioManutencao() {
		return usuarioManutencao;
	}

	public void setUsuarioManutencao(String usuarioManutencao) {
		this.usuarioManutencao = usuarioManutencao;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PerfilPermissao other = (PerfilPermissao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
