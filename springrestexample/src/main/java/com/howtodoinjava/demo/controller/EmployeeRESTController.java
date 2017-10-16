package com.howtodoinjava.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.howtodoinjava.demo.model.EmployeeListVO;
import com.howtodoinjava.demo.model.EmployeeVO;

@RestController
public class EmployeeRESTController {

    @Autowired
    private EmployeeListVO employees;

    @RequestMapping(value = "/employees")
    public @ResponseBody EmployeeListVO getAllEmployees() {

	employees.getEmployees().clear();

	EmployeeVO empOne = new EmployeeVO(1, "Lokesh", "Gupta",
		"howtodoinjava@gmail.com");
	EmployeeVO empTwo = new EmployeeVO(2, "Amit", "Singhal",
		"asinghal@yahoo.com");
	EmployeeVO empThree = new EmployeeVO(3, "Kirti", "Mishra",
		"kmishra@gmail.com");

	employees.getEmployees().add(empOne);
	employees.getEmployees().add(empTwo);
	employees.getEmployees().add(empThree);

	return employees;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "/employees/{id}")
    @ResponseBody
    public ResponseEntity<EmployeeVO> getEmployeeById(@PathVariable("id") int id) {
	if (id <= 3) {
	    EmployeeVO employee = employees.getEmployees().get(id - 1);
	    return new ResponseEntity<EmployeeVO>(employee, HttpStatus.OK);
	}
	return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}