package br.com.spring.data.jpa.querydsl.beans;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.data.jpa.querydsl.domain.Employee;
import br.com.spring.data.jpa.querydsl.domain.QAddress;
import br.com.spring.data.jpa.querydsl.domain.QEmployee;
import br.com.spring.data.jpa.querydsl.repository.AddressRepository;
import br.com.spring.data.jpa.querydsl.repository.EmployeeRepository;

import com.mysema.query.jpa.impl.JPAQuery;

@Service
public class RegistrationBean {

	@Autowired
	private EmployeeRepository eRepository;

	@Autowired
	private AddressRepository aRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	public EmployeeRepository getEmployeeReposigtory() {
		return this.eRepository;
	}

	public AddressRepository getAddressRepository() {
		return this.aRepository;
	}

	public void inquiryByEmployeeIdJoin(Integer id) {		
		QEmployee employee = QEmployee.employee;	
		QAddress address = QAddress.address;

		JPAQuery query = new JPAQuery(em);
		
		Employee result = query.from(employee)
				.innerJoin(employee.address, address)
				.where(employee.employeeId.eq(id))
				.singleResult(employee);

		System.out.println("result: " + result);
	}
}