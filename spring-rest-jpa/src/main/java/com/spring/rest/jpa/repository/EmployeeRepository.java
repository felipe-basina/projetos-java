package com.spring.rest.jpa.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.jpa.model.EmployeeVO;

@Transactional
@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeVO, Integer> {

	public EmployeeVO findByFirstName(String firstName);

	// like '% param %'
	public EmployeeVO findByFirstNameContaining(String firstName);

}
