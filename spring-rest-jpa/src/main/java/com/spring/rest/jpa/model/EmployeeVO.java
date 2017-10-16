package com.spring.rest.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "employee")
@Table(name = "EMPLOYEE_TBL", schema = "springrest")
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.NONE)
public class EmployeeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENTIFICADOR")
	@XmlAttribute
	private Integer id;

	@Column(name = "PRIMEIRO_NOME")
	@XmlElement
	@NotNull(message = "error.firstname.notnull")
	@Size(message = "erro.firstname.size.param", min = 3, max = 20)
	private String firstName;

	@Column(name = "ULTIMO_NOME")
	@XmlElement
	private String lastName;

	@Column(name = "ENDERECO_EMAIL")
	@XmlElement
	private String email;

	public EmployeeVO(Integer id, String firstName, String lastName,
			String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public EmployeeVO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmployeeVO [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + "]";
	}
}