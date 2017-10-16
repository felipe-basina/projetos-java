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

@Entity(name = "recurso")
@Table(name = "SUE_RECURSO")
public class Recurso extends AbstractPersistent<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_RECURSO")
	@SequenceGenerator(name = "ID_SEQ_RECURSO", sequenceName = "SUE_RECURSO_SQ01", allocationSize = 1)
	@Column(name = "CD_RECURSO")
	private Long id;

	@OneToMany(mappedBy = "recurso", fetch = FetchType.LAZY)
	private List<UsuarioPerfilRecurso> matrizAutorizacoes;

	@OneToMany(mappedBy = "recurso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RecursoParametro> recursoParametros;

	@Column(name = "CD_USUARIO_CRIACAO")
	private String usuarioCriacao;

	@Column(name = "CD_USUARIO_MANUT")
	private String usuarioManutencao;

	@Column(name = "NO_RECURSO")
	private String nomeRecurso;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public List<UsuarioPerfilRecurso> getMatrizAutorizacoes() {
		return matrizAutorizacoes;
	}

	public void setMatrizAutorizacoes(
			List<UsuarioPerfilRecurso> matrizAutorizacoes) {
		this.matrizAutorizacoes = matrizAutorizacoes;
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

	public String getNomeRecurso() {
		return nomeRecurso;
	}

	public void setNomeRecurso(String nomeRecurso) {
		this.nomeRecurso = nomeRecurso;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recurso [id=");
		builder.append(id);
		builder.append(", usuarioCriacao=");
		builder.append(usuarioCriacao);
		builder.append(", usuarioManutencao=");
		builder.append(usuarioManutencao);
		builder.append(", nomeRecurso=");
		builder.append(nomeRecurso);
		builder.append("]");
		return builder.toString();
	}

	public List<RecursoParametro> getRecursoParametros() {
		return recursoParametros;
	}

	public void setRecursoParametros(List<RecursoParametro> recursoParametros) {
		this.recursoParametros = recursoParametros;
	}
}
