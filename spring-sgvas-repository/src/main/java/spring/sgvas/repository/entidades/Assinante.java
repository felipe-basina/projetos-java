package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the assinante database table.
 * 
 */
@Entity
@NamedQuery(name = "Assinante.findAll", query = "SELECT a FROM Assinante a")
public class Assinante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_ASSINANTE")
	private String cdAssinante;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "MOB_CUSTOMER")
	private String mobCustomer;

	private String msisdn;

	private String subscriber;

	@Column(name = "TIPO_MSISDN")
	private String tipoMsisdn;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	@OneToMany(mappedBy = "assinante")
	private List<Assinatura> assinatura;

	public Assinante() {
	}

	public String getCdAssinante() {
		return this.cdAssinante;
	}

	public void setCdAssinante(String cdAssinante) {
		this.cdAssinante = cdAssinante;
	}

	public Date getDtAtualizacao() {
		return this.dtAtualizacao;
	}

	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}

	public Date getDtCriacao() {
		return this.dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getMobCustomer() {
		return this.mobCustomer;
	}

	public void setMobCustomer(String mobCustomer) {
		this.mobCustomer = mobCustomer;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getSubscriber() {
		return this.subscriber;
	}

	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}

	public String getTipoMsisdn() {
		return this.tipoMsisdn;
	}

	public void setTipoMsisdn(String tipoMsisdn) {
		this.tipoMsisdn = tipoMsisdn;
	}

	public String getUsuarioAtualizacao() {
		return this.usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public List<Assinatura> getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(List<Assinatura> assinatura) {
		this.assinatura = assinatura;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Assinante [cdAssinante=");
		builder.append(cdAssinante);
		builder.append(", dtAtualizacao=");
		builder.append(dtAtualizacao);
		builder.append(", dtCriacao=");
		builder.append(dtCriacao);
		builder.append(", mobCustomer=");
		builder.append(mobCustomer);
		builder.append(", msisdn=");
		builder.append(msisdn);
		builder.append(", subscriber=");
		builder.append(subscriber);
		builder.append(", tipoMsisdn=");
		builder.append(tipoMsisdn);
		builder.append(", usuarioAtualizacao=");
		builder.append(usuarioAtualizacao);
		builder.append("]");
		return builder.toString();
	}
}