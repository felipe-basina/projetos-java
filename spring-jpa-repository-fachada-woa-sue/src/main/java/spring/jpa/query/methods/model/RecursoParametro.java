package spring.jpa.query.methods.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "recursoParametro")
@Table(name = "SUE_PARAMETRO_RECURSO")
public class RecursoParametro extends AbstractPersistent<Long> implements
		Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_PARAM_RECURSO")
	@SequenceGenerator(name = "ID_SEQ_PARAM_RECURSO", sequenceName = "SUE_PARAMETRO_RECURSO_SQ01", allocationSize = 1)
	@Column(name = "CD_PARAMETRO_RECURSO")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_RECURSO")
	private Recurso recurso;

	@Column(name = "CD_USUARIO_CRIACAO")
	private String usuarioCriacao;

	@Column(name = "CD_USUARIO_MANUT")
	private String usuarioManutencao;

	@Column(name = "NO_PARAMETRO_RECURSO")
	private String noParametroRecurso;

	@Column(name = "VL_PARAMETRO_RECURSO")
	private String vlParametroRecurso;

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

	public String getNoParametroRecurso() {
		return noParametroRecurso;
	}

	public void setNoParametroRecurso(String noParametroRecurso) {
		this.noParametroRecurso = noParametroRecurso;
	}

	public String getVlParametroRecurso() {
		return vlParametroRecurso;
	}

	public void setVlParametroRecurso(String vlParametroRecurso) {
		this.vlParametroRecurso = vlParametroRecurso;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecursoParametro [id=");
		builder.append(id);
		builder.append(", usuarioCriacao=");
		builder.append(usuarioCriacao);
		builder.append(", usuarioManutencao=");
		builder.append(usuarioManutencao);
		builder.append(", noParametroRecurso=");
		builder.append(noParametroRecurso);
		builder.append(", vlParametroRecurso=");
		builder.append(vlParametroRecurso);
		builder.append("]");
		return builder.toString();
	}
}
