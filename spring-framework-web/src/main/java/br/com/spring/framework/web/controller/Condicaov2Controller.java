package br.com.spring.framework.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.spring.framework.web.form.Condicaov2Form;

@Controller
public class Condicaov2Controller {

	@RequestMapping("/trueorfalse")
	public ModelAndView trueOrFalse(
			@ModelAttribute("command") Condicaov2Form form,
			Map<String, Object> map) {

		if (form != null && form.getNome() != null
				&& !"".equals(form.getNome())) {
			form.setMensagem("Ol� " + form.getNome() + " Spring MVC!");
			form.setExibirMensagem(true);
		} else {
			form.setExibirMensagem(false);
		}

		map.put("command", form);

		return new ModelAndView("trueorfalse", map);
	}
}
