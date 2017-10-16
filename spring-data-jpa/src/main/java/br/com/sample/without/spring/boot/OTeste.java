package br.com.sample.without.spring.boot;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OTeste {

	@Id
	@Column(name = "IDENTIFICADOR_O")
	private Long id;
	
	@Column(name = "DESCRICAO_O")
	private String description;
	
	@Column(name = "DH_CRIACAO_O")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public OTeste(Long id, String description, Date date) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
	}

	public OTeste() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OTeste [id=");
		builder.append(id);
		builder.append(", description=");
		builder.append(description);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}

}
