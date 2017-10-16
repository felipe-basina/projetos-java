package br.com.sample.without.spring.boot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@SqlResultSetMappings({
	@SqlResultSetMapping(name = "TesteResultMapping", classes = {
			@ConstructorResult(
					targetClass = br.com.sample.without.spring.boot.ObjectReturn.class,
					columns = {
							@ColumnResult(name = "idT", type = Long.class),
							@ColumnResult(name = "descriptionT", type = String.class),
							@ColumnResult(name = "dateT", type = Date.class),
							@ColumnResult(name = "idO", type = Long.class),
							@ColumnResult(name = "descriptionO", type = String.class),
							@ColumnResult(name = "dateO", type = Date.class)
					}
			)
	})
})
@NamedNativeQueries({
	@NamedNativeQuery(name = "getTesteAndOTeste", query = "SELECT "
			+ " t.IDENTIFICADOR as idT, t.DESCRICAO as descriptionT, t.DH_CRIACAO as dateT, "
			+ " o.IDENTIFICADOR_O as idO, o.DESCRICAO_O as descriptionO, o.DH_CRIACAO_O as dateO "
			+ " FROM Teste t LEFT JOIN OTeste o ON o.IDENTIFICADOR_O = t.IDENTIFICADOR ORDER BY t.IDENTIFICADOR DESC, t.DH_CRIACAO DESC",
			resultSetMapping = "TesteResultMapping")
})
public class Teste {

	@Id
	@Column(name = "IDENTIFICADOR")
	private Long id;
	
	@Column(name = "DESCRICAO")
	private String description;
	
	@Column(name = "DH_CRIACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Transient
	private List<OTeste> otestes = new ArrayList<OTeste>();
	
	public Teste(Long id, String description, Date date) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
	}

	public Teste() {
		super();
	}

	public List<OTeste> getOtestes() {
		return otestes;
	}

	public void setOtestes(List<OTeste> otestes) {
		this.otestes = otestes;
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
		builder.append("Teste [id=");
		builder.append(id);
		builder.append(", description=");
		builder.append(description);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teste other = (Teste) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
