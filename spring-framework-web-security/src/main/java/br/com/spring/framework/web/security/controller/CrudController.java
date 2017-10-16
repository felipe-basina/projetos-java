package br.com.spring.framework.web.security.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.framework.web.security.business.interfaces.ContactService;
import br.com.spring.framework.web.security.model.Contact;

@Controller
public class CrudController {

	@Autowired
	private ContactService contactService;

	@RequestMapping("/init")
	public ModelAndView showContacts() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contatos", contactService.getAll());
		map.put("command", new Contact());

		return new ModelAndView("crud", map);
	}

	@RequestMapping(value = "/getOne", method = RequestMethod.GET)
	public ModelAndView getContact(@RequestParam("id") int id) {

		// Recupera valor da base de dados
		Contact contact = contactService.find(id);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("command", contact);
		map.put("contatos", contactService.getAll());

		if (contact == null) {
			map.put("mensagem", "N�o foi poss�vel recuperar o contato!");
		}

		return new ModelAndView("crud", map);
	}

	@RequestMapping(value = "/updateOne", method = RequestMethod.POST)
	public ModelAndView mergeContact(
			@Valid @ModelAttribute("command") Contact contact,
			BindingResult result) {

		Map<String, Object> map = new HashMap<String, Object>();

		if (result.hasErrors()) {
			map.put("command", contact);
			map.put("contatos", contactService.getAll());

			return new ModelAndView("crud", map);
		}

		// Remove caracteres especiais
		contact.setTelefone(contact.getTelefone().replace("(", "")
				.replace(")", "").replace("-", "").replace(" ", ""));

		// Atualiza registro na base de dados
		contactService.update(contact);

		System.out.println(contact);

		map.put("command", new Contact());
		map.put("contatos", contactService.getAll());

		return new ModelAndView("crud", map);
	}

	@RequestMapping(value = "/removeOne", method = RequestMethod.GET)
	public ModelAndView deleteContact(@RequestParam("id") int id) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("command", new Contact());

		// Recupera registro da base de dados
		Contact contact = contactService.find(id);

		if (contact == null) {
			map.put("mensagem", "N�o foi poss�vel remover o contato!");
			map.put("contatos", contactService.getAll());

			return new ModelAndView("crud", map);
		}

		contactService.delete(id);

		map.put("contatos", contactService.getAll());

		return new ModelAndView("crud", map);
	}

}
