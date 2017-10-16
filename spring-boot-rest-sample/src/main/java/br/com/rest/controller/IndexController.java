package br.com.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

	private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value = { "/" })
	public ModelAndView index(ModelAndView mav) {
		LOGGER.debug(" ---> IndexController.index ");
		mav.setViewName("rest.jsp");
		return mav;
	}
	
}
