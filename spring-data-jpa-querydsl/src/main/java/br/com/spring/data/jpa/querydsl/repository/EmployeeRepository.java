package br.com.spring.data.jpa.querydsl.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.spring.data.jpa.querydsl.domain.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>,
		QueryDslPredicateExecutor<Employee> {
	public Employee findByEmployeeId(Integer id);
	
	@Query(name = "getEmployeeAndAddressByEmployeeId", value = "SELECT e FROM Employee e JOIN FETCH e.address WHERE e.employeeId = :id")
	public Employee getEmployeeAndAddressByEmployeeId(@Param(value = "id") Integer id);
}