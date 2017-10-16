package spring.jpa.eclipselink.domain;

import java.util.Date;

public class Report {

	private long cdEmpresa;

	private String nomeEmpresa;

	private Date dataCriacaoRegistro;

	private long cdAgenda;

	private long totalMensagens;

	private String descStatus;

	public Report(long cdEmpresa, String nomeEmpresa, Date dataCriacaoRegistro,
			long cdAgenda, long totalMensagens) {
		this.cdEmpresa = cdEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.dataCriacaoRegistro = dataCriacaoRegistro;
		this.cdAgenda = cdAgenda;
		this.totalMensagens = totalMensagens;
	}

	public Report(long cdEmpresa, String nomeEmpresa, Date dataCriacaoRegistro,
			long cdAgenda, long totalMensagens, String descStatus) {
		super();
		this.cdEmpresa = cdEmpresa;
		this.nomeEmpresa = nomeEmpresa;
		this.dataCriacaoRegistro = dataCriacaoRegistro;
		this.cdAgenda = cdAgenda;
		this.totalMensagens = totalMensagens;
		this.descStatus = descStatus;
	}

	public long getCdEmpresa() {
		return cdEmpresa;
	}

	public void setCdEmpresa(long cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public Date getDataCriacaoRegistro() {
		return dataCriacaoRegistro;
	}

	public void setDataCriacaoRegistro(Date dataCriacaoRegistro) {
		this.dataCriacaoRegistro = dataCriacaoRegistro;
	}

	public long getCdAgenda() {
		return cdAgenda;
	}

	public void setCdAgenda(long cdAgenda) {
		this.cdAgenda = cdAgenda;
	}

	public long getTotalMensagens() {
		return totalMensagens;
	}

	public void setTotalMensagens(long totalMensagens) {
		this.totalMensagens = totalMensagens;
	}

	public String getDescStatus() {
		return descStatus;
	}

	public void setDescStatus(String descStatus) {
		this.descStatus = descStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Report [cdEmpresa=");
		builder.append(cdEmpresa);
		builder.append(", nomeEmpresa=");
		builder.append(nomeEmpresa);
		builder.append(", dataCriacaoRegistro=");
		builder.append(dataCriacaoRegistro);
		builder.append(", cdAgenda=");
		builder.append(cdAgenda);
		builder.append(", totalMensagens=");
		builder.append(totalMensagens);
		builder.append(", descStatus=");
		builder.append(descStatus);
		builder.append("]");
		return builder.toString();
	}
}
