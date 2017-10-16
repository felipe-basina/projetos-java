package spring.jpa.query.methods.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "tipoEvento")
@Table(name = "SUE_TIPO_EVENTO")
public class TipoEvento extends AbstractPersistent<Long> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_TIPO_EVENT")
	@SequenceGenerator(name = "ID_SEQ_TIPO_EVENT", sequenceName = "SUE_TIPO_EVENTO_SQ01", allocationSize = 1)
	@Column(name = "CD_TIPO_EVENTO")
	private Long id;

	@Column(name = "CD_USUARIO_CRIACAO")
	private String usuarioCriacao;

	@Column(name = "CD_USUARIO_MANUT")
	private String usuarioManutencao;

	@Column(name = "DS_TIPO_EVENTO")
	private String dsTipoEvento;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public TipoEvento() {
	}

	public TipoEvento(Long cdTipoEvento) {
		super();
		this.id = cdTipoEvento;
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

	public String getDsTipoEvento() {
		return dsTipoEvento;
	}

	public void setDsTipoEvento(String dsTipoEvento) {
		this.dsTipoEvento = dsTipoEvento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TipoEvento [id=");
		builder.append(id);
		builder.append(", usuarioCriacao=");
		builder.append(usuarioCriacao);
		builder.append(", usuarioManutencao=");
		builder.append(usuarioManutencao);
		builder.append(", dsTipoEvento=");
		builder.append(dsTipoEvento);
		builder.append("]");
		return builder.toString();
	}

}
