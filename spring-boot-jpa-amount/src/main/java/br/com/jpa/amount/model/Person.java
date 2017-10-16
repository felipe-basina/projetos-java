package br.com.jpa.amount.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedNativeQueries(
		@NamedNativeQuery(name = "getTotalOfRegisters",
				query = "SELECT COUNT(*) FROM PERSON")
)
@NamedQueries(
		@NamedQuery(name = "getAllWithChilds", 
			query = "SELECT DISTINCT p FROM Person p LEFT JOIN FETCH p.manies JOIN FETCH p.one ORDER BY p.creationDate DESC")
)
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	private boolean active;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Many> manies = new ArrayList<Many>();
	
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private One one;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Many> getManies() {
		return manies;
	}

	public void setManies(List<Many> manies) {
		this.manies = manies;
	}

	public One getOne() {
		return one;
	}

	public void setOne(One one) {
		this.one = one;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
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
		Person other = (Person) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
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
		builder.append("Person [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}

	public Person(String name, Date creationDate, boolean active) {
		super();
		this.name = name;
		this.creationDate = creationDate;
		this.active = active;
	}

	public Person() {
		super();
	}
	
}
