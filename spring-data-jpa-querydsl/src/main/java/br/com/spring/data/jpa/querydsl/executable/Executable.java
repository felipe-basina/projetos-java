package br.com.spring.data.jpa.querydsl.executable;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.spring.data.jpa.querydsl.beans.RegistrationBean;
import br.com.spring.data.jpa.querydsl.domain.Address;
import br.com.spring.data.jpa.querydsl.domain.Employee;
import br.com.spring.data.jpa.querydsl.domain.QAddress;
import br.com.spring.data.jpa.querydsl.domain.QEmployee;

import com.mysema.query.types.Predicate;

public class Executable {
	public static RegistrationBean registrationBean;
	public static ClassPathXmlApplicationContext context;

	static {
		// Acquire Context
		context = new ClassPathXmlApplicationContext("spring-context.xml");
		// Get RegistrationBean That Defined
		registrationBean = (RegistrationBean) context.getBean("registrationBean");
	}

	public static void main(String[] args) {
		if (args == null
				|| args.length <= 0
				|| args[0].equals("employee")) {
			createEmployee();
	
			// Inquiry Employees
			inquiryByEmployeeId(2);
			inquiryByEmployeeName("Susa Geraurd");
			inquiryByNameContaining("Susa");
		} else if (args[0].equals("employee-join")) {
			inquiryByEmployeeIdJoin(2);
		} else {
			inquiryByAddressId(1);
		}
		
		System.exit(1);
	}

	public static void inquiryByEmployeeIdJoin(Integer id) {
		/*Employee employee = registrationBean.getEmployeeReposigtory().getEmployeeAndAddressByEmployeeId(id);
		System.out.println("---> By JOIN: ".concat(employee.toString()));*/
		registrationBean.inquiryByEmployeeIdJoin(id);
	}
	
	public static void inquiryByAddressId(Integer id) {
		QAddress addressEquation = QAddress.address;
		Predicate addr = addressEquation.addressId.eq(id);
		
		Address address = registrationBean.getAddressRepository().findOne(addr);
		System.out.println("\nBy Address ID :: Address Inquired :: " + address);
		if (address != null
				&& address.getEmployee() != null) {
			System.out.println("Employee from (address-id): " + address.getEmployee());
		}
	}

	public static void inquiryByEmployeeId(Integer id) {
		QEmployee employeeEquation = QEmployee.employee;
		Predicate emp = employeeEquation.employeeId.eq(id);
		
		Employee employee = registrationBean.getEmployeeReposigtory().findOne(emp);
		System.out.println("By Employee ID :: Employee Inquired :: " + employee.toString());
	}

	public static void inquiryByEmployeeName(String name) {
		QEmployee employeeEquation = QEmployee.employee;
		Predicate emp = employeeEquation.employeeName.eq(name);
		Employee employee = registrationBean.getEmployeeReposigtory().findOne(emp);
		System.out.println("By Employee Name :: Employee Inquired :: " + employee.toString());
	}

	public static void inquiryByNameContaining(String name) {
		QEmployee employeeEquation = QEmployee.employee;
		Predicate emp = employeeEquation.employeeName.contains(name);
		Iterable<Employee> employees = registrationBean
				.getEmployeeReposigtory().findAll(emp);
		for (Employee e : employees) {
			System.out.println("By Employee Name Containing :: Employee Inquired :: " + e.toString());
		}
	}

	public static void createEmployee() {

		// Create Employee
		Employee employee = new Employee();
		employee.setEmployeeId(2);
		employee.setEmployeeName("Susa Geraurd");

		// Create Address
		Address address = new Address();
		address.setAddressId(1);
		address.setAddressCountry("United Kingdom");
		address.setAddressCity("London");
		address.setEmployee(employee);
		employee.setAddress(address);

		// Persist Using EmployeeRepository
		registrationBean.getEmployeeReposigtory().save(employee);
	}
}