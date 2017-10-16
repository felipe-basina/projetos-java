package spring.sgvas.repository.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the param_job database table.
 * 
 */
@Entity
@Table(name = "param_job")
@NamedQuery(name = "ParamJob.findAll", query = "SELECT p FROM ParamJob p")
public class ParamJob implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ParamJobPK id;

	@Column(name = "DEFAULT_VALUE")
	private String defaultValue;

	private String descricao;

	@Column(name = "IS_HEADER")
	private String isHeader;

	@Column(name = "IS_OBRIGATORIO")
	private String isObrigatorio;

	@Column(name = "LENGHT_MIN_MAX")
	private String lenghtMinMax;

	private int posicao;

	private String regex;

	// bi-directional many-to-one association to Job
	@ManyToOne
	@JoinColumn(name = "CD_JOB", insertable = false, updatable = false)
	private Job job;

	public ParamJob() {
	}

	public ParamJobPK getId() {
		return this.id;
	}

	public void setId(ParamJobPK id) {
		this.id = id;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIsHeader() {
		return this.isHeader;
	}

	public void setIsHeader(String isHeader) {
		this.isHeader = isHeader;
	}

	public String getIsObrigatorio() {
		return this.isObrigatorio;
	}

	public void setIsObrigatorio(String isObrigatorio) {
		this.isObrigatorio = isObrigatorio;
	}

	public String getLenghtMinMax() {
		return this.lenghtMinMax;
	}

	public void setLenghtMinMax(String lenghtMinMax) {
		this.lenghtMinMax = lenghtMinMax;
	}

	public int getPosicao() {
		return this.posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public String getRegex() {
		return this.regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}