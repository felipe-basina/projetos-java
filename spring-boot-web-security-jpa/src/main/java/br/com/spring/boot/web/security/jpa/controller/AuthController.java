package br.com.spring.boot.web.security.jpa.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

	@RequestMapping(value = {"/", "/home"})
	public ModelAndView home() {
		System.out.println("--> home");
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView login() {
		System.out.println("--> login");
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/hello")
	public ModelAndView hello(ModelAndView mv) {
		System.out.println("--> hello");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("now", new Date());
		
		mv.addAllObjects(map);
		mv.setViewName("hello");
		
		return mv;
	}
	
	@RequestMapping(value = "/auth")
	public ModelAndView auth() {
		System.out.println("--> auth");
		return new ModelAndView("home");
	}
}
