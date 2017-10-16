package generic.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="teste", schema="jpa2")
public class Teste implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2522030189790476492L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDENTIFICADOR")
	private Integer id;
	
	@Column(name="NOME")
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CADASTRO")
	private Date data;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Teste [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
