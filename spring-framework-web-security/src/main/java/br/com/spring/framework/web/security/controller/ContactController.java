package br.com.spring.framework.web.security.controller;

import java.util.HashMap;
import java.util.List;
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
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping("/contacts")
	public ModelAndView showContacts() {
		return new ModelAndView("contact", "command", new Contact());
	}

	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public ModelAndView addContact(
			@Valid @ModelAttribute("command") Contact contact,
			BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("contact", "command", contact);
		}

		// Adiciona na base de dados
		contactService.create(contact);

		Contact novoContato = contactService
				.recuperarPorNome(contact.getNome());
		System.out.println(novoContato);

		return this.getAll();
	}

	@RequestMapping(value = "/getContacts", method = RequestMethod.GET)
	public ModelAndView getAll() {

		// Recupera valores da base de dados
		List<Contact> contacts = contactService.getAll();

		System.out.println("Total de registros encontrados -> "
				+ contacts.size());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contatos", contacts);

		return new ModelAndView("contactList", map);
	}

	@RequestMapping(value = "/getContact", method = RequestMethod.GET)
	public ModelAndView getContact(@RequestParam("id") int id) {

		// Recupera valor da base de dados
		Contact contact = contactService.find(id);

		if (contact == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mensagem", "N�o foi poss�vel recuperar o contato!");

			return new ModelAndView("contactList", map);
		}

		return new ModelAndView("editContact", "command", contact);
	}

	@RequestMapping(value = "/updateContact", method = RequestMethod.POST)
	public ModelAndView mergeContact(
			@Valid @ModelAttribute("command") Contact contact,
			BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("editContact", "command", contact);
		}

		// Atualiza registro na base de dados
		contactService.update(contact);

		System.out.println(contact);

		return this.getAll();
	}

	@RequestMapping(value = "/removeContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(@RequestParam("id") int id) {

		Contact contact = null;

		// Recupera registro da base de dados
		contact = contactService.find(id);

		if (contact == null) {
			List<Contact> contatos = contactService.getAll();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mensagem", "N�o foi poss�vel remover o contato!");
			map.put("contatos", contatos);

			return new ModelAndView("contactList", map);
		}

		contactService.delete(id);

		return this.getAll();
	}

}
