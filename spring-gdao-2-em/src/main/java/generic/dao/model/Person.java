package generic.dao.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="people", schema="jpa")
@NamedQueries({
	@NamedQuery(name="getPeopleById", query="select p from Person p JOIN FETCH p.phones WHERE p.id = :id"),
	@NamedQuery(name="getListOfPeople", query="select p from Person p"),
	@NamedQuery(name="getListOfPeopleByNameJoinFetch", query="select p from Person p JOIN FETCH p.phones WHERE p.name LIKE :name"),
	@NamedQuery(name="getListOfPeopleByName", query="select p from Person p WHERE p.name LIKE :name")
})
public class Person implements Serializable {

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
	
	@Column(name="ENDERECO")
	private String address;
	
	@Column(name="CIDADE")
	private String city;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="person", orphanRemoval=true)
	private Set<Phone> phones;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public Set<Phone> getPhones() {
		return phones;
	}
	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", address=" + address
				+ ", city=" + city + ", phones=" + phones + "]";
	}
	
}
