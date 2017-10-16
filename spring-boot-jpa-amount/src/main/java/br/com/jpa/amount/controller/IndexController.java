package br.com.jpa.amount.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.jpa.amount.service.PersonService;

@Controller
public class IndexController {

	private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	private PersonService personService;
	
	@Autowired
	public IndexController(PersonService personService) {
		this.personService = personService;
	}
	
	@RequestMapping("/")
	public @ResponseBody String index() {
		LOGGER.debug("INDEX");
		return "Hello!";
	}
	
	@RequestMapping("/total")
	public @ResponseBody String total() throws Exception {
		LOGGER.debug("TOTAL");
		return String.valueOf(personService.getTotalOfRegisters());
	}

	@RequestMapping("/all")
	public ModelAndView getAll(ModelAndView mav) throws Exception {
		mav.setViewName("all.jsp");
		mav.addObject("persons", personService.getPersonWithChilds());
		return mav;
	}
	
	@RequestMapping("/mav")
	public ModelAndView list(ModelAndView mav) {
		LOGGER.debug("LIST");
		
		mav.setViewName("list.jsp");
		try {
			mav.addObject("persons", personService.getPersonWithChilds());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			mav.addObject("errorMsg", ex.getMessage());
		}
		return mav;
	}
	
	@RequestMapping("/mav2")
	public ModelAndView listInner(ModelAndView mav) {
		LOGGER.debug("LIST INNER");
		
		mav.setViewName("list-inner.jsp");
		try {
			mav.addObject("persons", personService.getPersonWithChilds());
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			mav.addObject("errorMsg", ex.getMessage());
		}
		return mav;
	}
}
