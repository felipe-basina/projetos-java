package br.com.sample.without.spring.boot;

import java.util.Date;

/**
 * POJO responsável por definir o objeto de retorno da consulta nativa
 * sem necessariamente ter que ser uma entidade (@Entity)
 */
public class ObjectReturn {

	private Long idT;
	
	private String descriptionT;
	
	private Date dateT;
	
	private Long idO;
	
	private String descriptionO;
	
	private Date dateO;

	public Long getIdT() {
		return idT;
	}

	public void setIdT(Long idT) {
		this.idT = idT;
	}

	public String getDescriptionT() {
		return descriptionT;
	}

	public void setDescriptionT(String descriptionT) {
		this.descriptionT = descriptionT;
	}

	public Date getDateT() {
		return dateT;
	}

	public void setDateT(Date dateT) {
		this.dateT = dateT;
	}

	public Long getIdO() {
		return idO;
	}

	public void setIdO(Long idO) {
		this.idO = idO;
	}

	public String getDescriptionO() {
		return descriptionO;
	}

	public void setDescriptionO(String descriptionO) {
		this.descriptionO = descriptionO;
	}

	public Date getDateO() {
		return dateO;
	}

	public void setDateO(Date dateO) {
		this.dateO = dateO;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ObjectReturn [idT=");
		builder.append(idT);
		builder.append(", descriptionT=");
		builder.append(descriptionT);
		builder.append(", dateT=");
		builder.append(dateT);
		builder.append(", idO=");
		builder.append(idO);
		builder.append(", descriptionO=");
		builder.append(descriptionO);
		builder.append(", dateO=");
		builder.append(dateO);
		builder.append("]");
		return builder.toString();
	}

	public ObjectReturn(Long idT, String descriptionT, Date dateT, Long idO,
			String descriptionO, Date dateO) {
		this.idT = idT;
		this.descriptionT = descriptionT;
		this.dateT = dateT;
		this.idO = idO;
		this.descriptionO = descriptionO;
		this.dateO = dateO;
	}
	
	public ObjectReturn() {
		super();
	}
	
}
