package br.com.poc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DefaultController {

	private static String JSP = "fb";
	
	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		String indexJsp = "index";
		
		System.out.println("Preparando para redirecionar para tela... " + indexJsp);
		
		mav.setViewName(indexJsp);
		
		return mav;
	}

	@RequestMapping(value = { "/fb1" }, method = RequestMethod.GET)
	public ModelAndView fb1(ModelAndView mav) {
		System.out.println("Preparando para redirecionar para tela... " + (JSP + 1));
		
		mav.setViewName((JSP + 1));
		
		return mav;
	}

	@RequestMapping(value = { "/fb2" }, method = RequestMethod.GET)
	public ModelAndView fb2(ModelAndView mav) {
		System.out.println("Preparando para redirecionar para tela... " + (JSP + 2));
		
		mav.setViewName((JSP + 2));
		
		return mav;
	}
	
	@RequestMapping(value = { "/resposta" }, method = RequestMethod.GET)
	public ModelAndView resposta(ModelAndView mav) {
		String respostaJsp = "resposta";
		
		mav.setViewName(respostaJsp);
		
		return mav;
	}
	
}
