package br.com.spring.framework.web.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.framework.web.form.ContactForm;

public class ContactController_bkp {

	public ModelAndView showContacts() {
		return new ModelAndView("contact", "command", new ContactForm());
	}

	public String addContact(@ModelAttribute("contact") ContactForm contact,
			BindingResult result) {

		System.out.println(contact);

		return "redirect:contacts.do";
	}

}
