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

@Entity(name = "permissaoParametro")
@Table(name = "SUE_PARAMETRO_ACAO")
public class PermissaoParametro extends AbstractPersistent<Long> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_PARAM_ACAO")
	@SequenceGenerator(name = "ID_SEQ_PARAM_ACAO", sequenceName = "SUE_PARAMETRO_ACAO_SQ01", allocationSize = 1)
	@Column(name = "CD_PARAMETRO_ACAO")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_ACAO")
	private Permissao permissao;

	@Column(name = "CD_USUARIO_CRIACAO")
	private String usuarioCriacao;

	@Column(name = "CD_USUARIO_MANUT")
	private String usuarioManutencao;

	@Column(name = "NO_PARAMETRO_ACAO")
	private String noParametroAcao;

	@Column(name = "VL_PARAMETRO_ACAO")
	private String vlParametroAcao;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
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

	public String getNoParametroAcao() {
		return noParametroAcao;
	}

	public void setNoParametroAcao(String noParametroAcao) {
		this.noParametroAcao = noParametroAcao;
	}

	public String getVlParametroAcao() {
		return vlParametroAcao;
	}

	public void setVlParametroAcao(String vlParametroAcao) {
		this.vlParametroAcao = vlParametroAcao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PermissaoParametro [id=");
		builder.append(id);
		builder.append(", usuarioCriacao=");
		builder.append(usuarioCriacao);
		builder.append(", usuarioManutencao=");
		builder.append(usuarioManutencao);
		builder.append(", noParametroAcao=");
		builder.append(noParametroAcao);
		builder.append(", vlParametroAcao=");
		builder.append(vlParametroAcao);
		builder.append("]");
		return builder.toString();
	}

}
