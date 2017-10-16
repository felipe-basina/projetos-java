package br.com.spring.framework.web.security.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@RequestMapping("/test")
	public ModelAndView test() {

		System.out.println("test");
		return new ModelAndView("test");
	}

	@RolesAllowed({ "appGrpAdmin" })
	@RequestMapping("/test2")
	public ModelAndView test2() {

		System.out.println("test2");
		return new ModelAndView("test");
	}
}
