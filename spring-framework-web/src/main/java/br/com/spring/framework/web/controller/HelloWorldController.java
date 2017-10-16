package br.com.spring.framework.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.framework.web.form.HelloWorldForm;
import br.com.spring.framework.web.servlet.ContatoComponent;
import br.com.spring.framework.web.util.Configuracao;

@Controller
public class HelloWorldController {

	@Autowired
	private Configuracao configuracao;

	@Autowired
	private ContatoComponent componente;

	@RequestMapping("/helloworld")
	public ModelAndView helloWord(@ModelAttribute("hello") HelloWorldForm form) {

		if (form != null && form.getName() != null) {
			form.setMessage("Ol�, " + form.getName() + "!");

			this.imprimirConfiguracao();
		}

		return new ModelAndView("helloworld", "command", form);
	}

	private void imprimirConfiguracao() {
		System.out.println(" >>>>>>>>>> Valor da configuracao: "
				+ configuracao.getValor());
	}

	@RequestMapping("/contatoservlet")
	public ModelAndView component() {
		componente.register();
		componente.getAll();
		return null;
	}
}
