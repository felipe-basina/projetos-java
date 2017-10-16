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
 * The persistent class for the sql_out database table.
 * 
 */
@Entity
@Table(name = "sql_out")
@NamedQuery(name = "SqlOut.findAll", query = "SELECT s FROM SqlOut s")
public class SqlOut implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SqlOutPK id;

	@Column(name = "SQL_BODY")
	private String sqlBody;

	@Column(name = "SQL_HEADER")
	private String sqlHeader;

	// bi-directional many-to-one association to Job
	@ManyToOne
	@JoinColumn(name = "CD_JOB", insertable = false, updatable = false)
	private Job job;

	public SqlOut() {
	}

	public SqlOutPK getId() {
		return this.id;
	}

	public void setId(SqlOutPK id) {
		this.id = id;
	}

	public String getSqlBody() {
		return this.sqlBody;
	}

	public void setSqlBody(String sqlBody) {
		this.sqlBody = sqlBody;
	}

	public String getSqlHeader() {
		return this.sqlHeader;
	}

	public void setSqlHeader(String sqlHeader) {
		this.sqlHeader = sqlHeader;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

}