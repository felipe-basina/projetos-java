package com.howtodoinjava.demo.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@XmlRootElement(name = "employees")
public class EmployeeListVO {
    
    @Autowired
    private List<EmployeeVO> employees;

    public List<EmployeeVO> getEmployees() {
	return employees;
    }

    public void setEmployees(List<EmployeeVO> employees) {
	this.employees = employees;
    }
}