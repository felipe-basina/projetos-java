package br.com.spring.framework.web.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AjaxController {

	@RequestMapping(value = "/ajax-init")
	public ModelAndView ajaxInit() {
		return new ModelAndView("ajax-response");
	}
	
	@RequestMapping(value = "/ajax-request", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<String> ajaxRequest() {
		final List<String> lista = new ArrayList<String>();
		for (int i = 0; i <= 5; i++) {
			lista.add("lista-elemento-" + i);
		}
		return lista;
	}
	
}
