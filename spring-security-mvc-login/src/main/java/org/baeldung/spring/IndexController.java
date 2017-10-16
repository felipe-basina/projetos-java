package org.baeldung.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public final ModelAndView index(final HttpServletRequest request) {
	System.out.println("\n ######## Entrou em [index -> index.do]. Autenticacao realizada com sucesso");
	return new ModelAndView("homepage");
    }

    // anonymous
    @RequestMapping("/anonymous")
    public final ModelAndView anonymous(final HttpServletRequest request) {
	System.out.println("\n ######## Entrou em [anonymous -> anonymous.do]");
	return new ModelAndView("anonymous");
    }

    // fromAnonymous
    @RequestMapping("/fromanonymous")
    public final ModelAndView fromAnonymous(final HttpServletRequest request) {
	System.out.println("\n ######## Entrou em [fromAnonymous -> fromAnonymous.do]");
	return new ModelAndView("login");
    }

    // loginfailed authentication-failure-url="/loginfailed.do"
    @RequestMapping("/loginfailed")
    public final ModelAndView loginFailed(final Model model) {
	System.out.println("\n ######## Entrou em [loginFailed -> loginFailed.do]. Logout realizado com sucesso");
	model.addAttribute("loginFailed", true);
	return new ModelAndView("login");
    }
}
