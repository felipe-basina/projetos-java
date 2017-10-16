package spring.jpa.query.methods.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "logEventos")
@Table(name = "SUE_LOG_EVENTOS")
public class LogEventos implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_EVENTO")
	@SequenceGenerator(name = "ID_SEQ_EVENTO", sequenceName = "SUE_LOG_EVENTOS_SQ01", allocationSize = 1)
	@Column(name = "CD_LOG_EVENTO")
	private Long id;

	@Column(name = "CD_TIPO_EVENTO")
	private long cdTipoEvento;

	@Column(name = "CD_USUARIO")
	private long cdUsuario;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_EVENTO")
	private Date dtEvento;

	@Column(name = "FG_OCORREU_FALHA")
	private char indicadorFalha;

	@Column(name = "IN_CANAL_INTERFACE_APLICACAO")
	private String aplicacao;

	@Column(name = "SG_SISTEM_ORIGEM")
	private String sistemaOrigem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getCdTipoEvento() {
		return cdTipoEvento;
	}

	public void setCdTipoEvento(long cdTipoEvento) {
		this.cdTipoEvento = cdTipoEvento;
	}

	public long getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(long cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public Date getDtEvento() {
		return dtEvento;
	}

	public void setDtEvento(Date dtEvento) {
		this.dtEvento = dtEvento;
	}

	public char getIndicadorFalha() {
		return indicadorFalha;
	}

	public void setIndicadorFalha(char indicadorFalha) {
		this.indicadorFalha = indicadorFalha;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	public String getSistemaOrigem() {
		return sistemaOrigem;
	}

	public void setSistemaOrigem(String sistemaOrigem) {
		this.sistemaOrigem = sistemaOrigem;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LogEventos [id=");
		builder.append(id);
		builder.append(", cdTipoEvento=");
		builder.append(cdTipoEvento);
		builder.append(", cdUsuario=");
		builder.append(cdUsuario);
		builder.append(", dtEvento=");
		builder.append(dtEvento);
		builder.append(", indicadorFalha=");
		builder.append(indicadorFalha);
		builder.append(", aplicacao=");
		builder.append(aplicacao);
		builder.append(", sistemaOrigem=");
		builder.append(sistemaOrigem);
		builder.append("]");
		return builder.toString();
	}

}