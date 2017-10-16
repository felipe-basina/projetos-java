package br.com.jpa.amount.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class One {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date futureDate;
	
	@OneToOne
	private Person person;

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

	public Date getFutureDate() {
		return futureDate;
	}

	public void setFutureDate(Date futureDate) {
		this.futureDate = futureDate;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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
		One other = (One) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("One [id=");
		builder.append(id);
		builder.append(", description=");
		builder.append(description);
		builder.append(", date=");
		builder.append(date);
		builder.append(", futureDate=");
		builder.append(futureDate);
		builder.append("]");
		return builder.toString();
	}

	public One(String description, Date date, Date futureDate) {
		super();
		this.description = description;
		this.date = date;
		this.futureDate = futureDate;
	}

	public One() {
		super();
	}
	
}
