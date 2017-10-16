package br.com.spring.framework.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.framework.web.form.SeSenaoForm;

@Controller
public class SeSenaoJSPController {

	@RequestMapping(value = "/exibese", method = RequestMethod.GET)
	public ModelAndView exibeSe(@ModelAttribute("sesenao") SeSenaoForm form) {
		form.setName("EXIBE_IF");
		return new ModelAndView("seSenaoJsp", "command", form);
	}

	@RequestMapping(value = "/exibesenao", method = RequestMethod.POST)
	public ModelAndView exibeSenao(HttpServletRequest request,
			@ModelAttribute("sesenao") SeSenaoForm form) {

		String numero = request.getParameter("numero");

		int nro = -1;
		try {
			nro = Integer.parseInt(numero);
		} catch (Exception ex) {
			nro = 1;
		}

		System.out.println(" ####### Numero selecionado: " + nro);

		String condicao = "EXIBE_IF";

		if (nro == 2) {
			condicao = "EXIBE_ELSE_01";
		} else if (nro == 3) {
			condicao = "EXIBE_ELSE_02";
		}

		form.setName(condicao);

		return new ModelAndView("seSenaoJsp", "command", form);
	}

}
