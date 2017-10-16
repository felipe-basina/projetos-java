package br.com.spring.data.jpa.querydsl.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "address")
public class Address {
	@Id
	private Integer addressId;
	private String addressCountry;
	private String addressCity;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "address", fetch = FetchType.LAZY)
	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [addressId=");
		builder.append(addressId);
		builder.append(", addressCountry=");
		builder.append(addressCountry);
		builder.append(", addressCity=");
		builder.append(addressCity);
		builder.append("]");
		return builder.toString();
	}
	
}