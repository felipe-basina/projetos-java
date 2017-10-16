package spring.sgvas.repository.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the job database table.
 * 
 */
@Entity
@NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CD_JOB")
	private int cdJob;

	private String delimitador;

	private String diretorio;

	@Column(name = "IS_ENABLE")
	private String isEnable;

	@Column(name = "NO_ARQUIVO")
	private String noArquivo;

	@Column(name = "NO_JOB")
	private String noJob;

	private String servidor;

	private String tipo;

	// bi-directional many-to-one association to ParamJob
	@OneToMany(mappedBy = "job")
	private List<ParamJob> paramJobs;

	// bi-directional many-to-one association to SqlOut
	@OneToMany(mappedBy = "job")
	private List<SqlOut> sqlOuts;

	public Job() {
	}

	public int getCdJob() {
		return this.cdJob;
	}

	public void setCdJob(int cdJob) {
		this.cdJob = cdJob;
	}

	public String getDelimitador() {
		return this.delimitador;
	}

	public void setDelimitador(String delimitador) {
		this.delimitador = delimitador;
	}

	public String getDiretorio() {
		return this.diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

	public String getIsEnable() {
		return this.isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getNoArquivo() {
		return this.noArquivo;
	}

	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}

	public String getNoJob() {
		return this.noJob;
	}

	public void setNoJob(String noJob) {
		this.noJob = noJob;
	}

	public String getServidor() {
		return this.servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<ParamJob> getParamJobs() {
		return this.paramJobs;
	}

	public void setParamJobs(List<ParamJob> paramJobs) {
		this.paramJobs = paramJobs;
	}

	public ParamJob addParamJob(ParamJob paramJob) {
		getParamJobs().add(paramJob);
		paramJob.setJob(this);

		return paramJob;
	}

	public ParamJob removeParamJob(ParamJob paramJob) {
		getParamJobs().remove(paramJob);
		paramJob.setJob(null);

		return paramJob;
	}

	public List<SqlOut> getSqlOuts() {
		return this.sqlOuts;
	}

	public void setSqlOuts(List<SqlOut> sqlOuts) {
		this.sqlOuts = sqlOuts;
	}

	public SqlOut addSqlOut(SqlOut sqlOut) {
		getSqlOuts().add(sqlOut);
		sqlOut.setJob(this);

		return sqlOut;
	}

	public SqlOut removeSqlOut(SqlOut sqlOut) {
		getSqlOuts().remove(sqlOut);
		sqlOut.setJob(null);

		return sqlOut;
	}

}