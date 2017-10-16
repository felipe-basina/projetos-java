package org.kiranreddykasa.springfreemarker.repository;

import org.kiranreddykasa.springfreemarker.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

}
