package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the configuracao database table.
 * 
 */
@Entity
@NamedQuery(name = "Configuracao.findAll", query = "SELECT c FROM Configuracao c")
public class Configuracao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_CONFIGURACAO")
	private String cdConfiguracao;

	@Column(name = "DESC_CONFIGURACAO")
	private String descConfiguracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ATUALIZACAO")
	private Date dtAtualizacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@Column(name = "USUARIO_ATUALIZACAO")
	private String usuarioAtualizacao;

	@Column(name = "VL_CONFIGURACAO")
	private String vlConfiguracao;

	public Configuracao() {
	}

	public String getCdConfiguracao() {
		return this.cdConfiguracao;
	}

	public void setCdConfiguracao(String cdConfiguracao) {
		this.cdConfiguracao = cdConfiguracao;
	}

	public String getDescConfiguracao() {
		return this.descConfiguracao;
	}

	public void setDescConfiguracao(String descConfiguracao) {
		this.descConfiguracao = descConfiguracao;
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

	public String getUsuarioAtualizacao() {
		return this.usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public String getVlConfiguracao() {
		return this.vlConfiguracao;
	}

	public void setVlConfiguracao(String vlConfiguracao) {
		this.vlConfiguracao = vlConfiguracao;
	}

}