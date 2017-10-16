package br.com.spring.framework.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes
public class OperationalController {

	protected final Logger logger = LoggerFactory
			.getLogger(OperationalController.class);

	@RequestMapping("/environment")
	public ModelAndView environment() {

		logger.debug("OperationalController.environment()");
		return new ModelAndView("environment");
	}

	@RequestMapping("/log")
	public ModelAndView log() {

		logger.debug("OperationalController.log()");
		return new ModelAndView("log");
	}

	@RequestMapping("/contact")
	public ModelAndView contacts() {

		logger.debug("OperationalController.contact()");
		return new ModelAndView("contact");
	}
}
