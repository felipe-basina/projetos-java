package spring.jpa.query.methods.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "perfil")
@Table(name = "SUE_PERFIL")
public class Perfil extends AbstractPersistent<Long> implements Serializable,
		Cloneable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_PERFIL")
	@SequenceGenerator(name = "ID_SEQ_PERFIL", sequenceName = "SUE_PERFIL_SQ01", allocationSize = 1)
	@Column(name = "CD_PERFIL")
	private Long id;

	@OneToMany(mappedBy = "perfil", fetch = FetchType.LAZY)
	private List<UsuarioPerfilRecurso> matrizAutorizacoes;

	@OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PerfilPermissao> matrizPermissoesPerfil;

	@Column(name = "CD_USUARIO_CRIACAO")
	private String usuarioCriacao;

	@Column(name = "CD_USUARIO_MANUT")
	private String usuarioManutencao;

	@Column(name = "NO_PERFIL")
	private String nomePerfil;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
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

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public List<UsuarioPerfilRecurso> getMatrizAutorizacoes() {
		return matrizAutorizacoes;
	}

	public void setMatrizAutorizacoes(
			List<UsuarioPerfilRecurso> matrizAutorizacoes) {
		this.matrizAutorizacoes = matrizAutorizacoes;
	}

	public List<PerfilPermissao> getMatrizPermissoesPerfil() {
		return matrizPermissoesPerfil;
	}

	public void setMatrizPermissoesPerfil(
			List<PerfilPermissao> matrizPermissoesPerfil) {
		this.matrizPermissoesPerfil = matrizPermissoesPerfil;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Perfil [id=");
		builder.append(id);
		builder.append(", usuarioCriacao=");
		builder.append(usuarioCriacao);
		builder.append(", usuarioManutencao=");
		builder.append(usuarioManutencao);
		builder.append(", nomePerfil=");
		builder.append(nomePerfil);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public Perfil clone() throws CloneNotSupportedException {
		return (Perfil) super.clone();
	}
}
