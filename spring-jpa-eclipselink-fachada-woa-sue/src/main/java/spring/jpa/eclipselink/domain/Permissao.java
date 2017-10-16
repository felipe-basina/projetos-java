package spring.jpa.eclipselink.domain;

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

@Entity(name = "permissao")
@Table(name = "SUE_ACAO")
public class Permissao extends AbstractPersistent<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_ACAO")
	@SequenceGenerator(name = "ID_SEQ_ACAO", sequenceName = "SUE_ACAO_SQ01", allocationSize = 1)
	@Column(name = "CD_ACAO")
	private Long id;

	@OneToMany(mappedBy = "permissao", fetch = FetchType.LAZY)
	private List<PerfilPermissao> matrizPermissoesPerfil;

	@OneToMany(mappedBy = "permissao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<PermissaoParametro> permissaoParametros;

	@Column(name = "CD_USUARIO_CRIACAO")
	private String usuarioCriacao;

	@Column(name = "CD_USUARIO_MANUT")
	private String usuarioManutencao;

	@Column(name = "NO_ACAO")
	private String nomeAcao;

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

	public String getNomeAcao() {
		return nomeAcao;
	}

	public void setNomeAcao(String nomeAcao) {
		this.nomeAcao = nomeAcao;
	}

	public List<PerfilPermissao> getMatrizPermissoesPerfil() {
		return matrizPermissoesPerfil;
	}

	public void setMatrizPermissoesPerfil(
			List<PerfilPermissao> matrizPermissoesPerfil) {
		this.matrizPermissoesPerfil = matrizPermissoesPerfil;
	}

	public List<PermissaoParametro> getPermissaoParametros() {
		return permissaoParametros;
	}

	public void setPermissaoParametros(
			List<PermissaoParametro> permissaoParametros) {
		this.permissaoParametros = permissaoParametros;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Permissao [id=");
		builder.append(id);
		builder.append(", usuarioCriacao=");
		builder.append(usuarioCriacao);
		builder.append(", usuarioManutencao=");
		builder.append(usuarioManutencao);
		builder.append(", nomeAcao=");
		builder.append(nomeAcao);
		builder.append("]");
		return builder.toString();
	}
}
