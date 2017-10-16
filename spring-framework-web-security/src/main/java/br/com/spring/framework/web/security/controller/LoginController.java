package br.com.spring.framework.web.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	/** The message source. */
	@Autowired
	private MessageSource messageSource;

	/**
	 * Iniciar a operação
	 * 
	 * @param request
	 *            the request
	 * @return the string
	 */
	@RequestMapping("/login")
	public final ModelAndView login(final HttpServletRequest request) {
		System.out.println("\n ######## Entrou em [login -> login.do]");
		return new ModelAndView("login");
	}

	/**
	 * Denied.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the string
	 */
	@RequestMapping(value = "/denied")
	public final ModelAndView denied(final HttpServletRequest request,
			final HttpServletResponse response) {
		System.out.println("\n ######## Entrou em [denied -> denied.do]");
		String forward = "denied";
		return new ModelAndView(forward);
	}

	/**
	 * Apos usuario autenticado validar detalhes do usuario.
	 * 
	 * @param request
	 *            the request
	 * @return the string
	 */
	@RequestMapping("/index")
	public final String index(final HttpServletRequest request) {
		System.out.println("\n ######## Entrou em [index -> index.do]");
		return "default";
	}
}
