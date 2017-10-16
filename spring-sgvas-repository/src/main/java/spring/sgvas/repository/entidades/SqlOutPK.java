package spring.sgvas.repository.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the sql_out database table.
 * 
 */
@Embeddable
public class SqlOutPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "CD_SQL_OUT")
	private int cdSqlOut;

	@Column(name = "CD_JOB", insertable = false, updatable = false)
	private int cdJob;

	public SqlOutPK() {
	}

	public int getCdSqlOut() {
		return this.cdSqlOut;
	}

	public void setCdSqlOut(int cdSqlOut) {
		this.cdSqlOut = cdSqlOut;
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
		if (!(other instanceof SqlOutPK)) {
			return false;
		}
		SqlOutPK castOther = (SqlOutPK) other;
		return (this.cdSqlOut == castOther.cdSqlOut)
				&& (this.cdJob == castOther.cdJob);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cdSqlOut;
		hash = hash * prime + this.cdJob;

		return hash;
	}
}