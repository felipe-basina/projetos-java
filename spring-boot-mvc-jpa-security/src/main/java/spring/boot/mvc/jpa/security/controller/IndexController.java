package spring.boot.mvc.jpa.security.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value = {"/", "/login"})
	public ModelAndView index(Principal principal) {
		System.out.println("--> index");
		
		if (principal != null
				&& principal.getName() != null) {
			System.out.println("---> Principal:\n" + principal.toString());
			return new ModelAndView("redirect:/init");
		}
		
		return new ModelAndView("login");
	}
	
	/*@RequestMapping(value = {"/login"})
	public String login(Principal principal) {
		System.out.println("--> login");
		
		String redirect = "redirect:/";
		
		if (principal != null
				&& principal.getName() != null) {
			System.out.println("---> Principal:" + principal.toString());
			redirect = "redirect:/init";
		}
		return redirect;
	}*/
}
