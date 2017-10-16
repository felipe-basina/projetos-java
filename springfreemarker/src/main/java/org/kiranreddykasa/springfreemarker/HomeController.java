package org.kiranreddykasa.springfreemarker;

import org.kiranreddykasa.springfreemarker.model.Employee;
import org.kiranreddykasa.springfreemarker.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping(value = "/")
	public String loadHomePage(Model model) {

		model.addAttribute("employees", employeeRepository.findAll());

		return "index";
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute("employee") Employee employee,
			Model model) {

		employeeRepository.save(employee);

		return "redirect:/";
	}
}
