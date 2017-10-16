package spring.sgvas.repository.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the param_job database table.
 * 
 */
@Embeddable
public class ParamJobPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "NO_PARAMETRO")
	private String noParametro;

	@Column(name = "CD_JOB", insertable = false, updatable = false)
	private int cdJob;

	public ParamJobPK() {
	}

	public String getNoParametro() {
		return this.noParametro;
	}

	public void setNoParametro(String noParametro) {
		this.noParametro = noParametro;
	}

	public int getCdJob() {
		return this.cdJob;
	}

	public void setCdJob(int cdJob) {
		this.cdJob = cdJob;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ParamJobPK)) {
			return false;
		}
		ParamJobPK castOther = (ParamJobPK) other;
		return this.noParametro.equals(castOther.noParametro)
				&& (this.cdJob == castOther.cdJob);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.noParametro.hashCode();
		hash = hash * prime + this.cdJob;

		return hash;
	}
}