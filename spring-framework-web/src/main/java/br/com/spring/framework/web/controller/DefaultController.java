package br.com.spring.framework.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping("/index")
	public ModelAndView index() {
		logger.debug("DefaultController.login()");
		return new ModelAndView("default");
	}

}
