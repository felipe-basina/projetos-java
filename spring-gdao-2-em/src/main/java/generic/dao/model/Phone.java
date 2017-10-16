package generic.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="telephone", schema="jpa")
@NamedQueries({
	@NamedQuery(name="deletePhoneByUserId", query="delete from Phone WHERE person.id = :id"),
	@NamedQuery(name="getPeopleFromPhone", query="select ph from Phone ph WHERE ph.id = :id")
})
public class Phone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2522030189790476492L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FONE_ID")
	private Integer id;
	
	@Column(name="NRO_TELEFONE")
	private String phone;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDENTIFICADOR", nullable=false)
	private Person person;
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Person getPerson() {
		return person;
	}



	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "\nPhone [id=" + id + ", phone=" + phone + "]";
	}	
}
