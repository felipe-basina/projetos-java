package spring.boot.mvc.jpa.controller;

import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.boot.mvc.jpa.form.model.UsuarioForm;
import spring.boot.mvc.jpa.model.Usuario;
import spring.boot.mvc.jpa.service.UsuarioService;

@Controller
public class CRUDController {

	private static Logger log = LoggerFactory.getLogger(CRUDController.class);

	private static final String PAGE_CONTEXT = "usuario/form";

	private MessageSource message;
	
	private UsuarioService usuarioService;
	
	//private UService usuarioService;
	
	private CacheManager cacheManager;
	
	@Autowired
	public CRUDController(MessageSource message, 
			UsuarioService usuarioService/*UService usuarioService*/,
			CacheManager cacheManager) {
		this.message = message;
		this.usuarioService = usuarioService;
		this.cacheManager = cacheManager;
	}

	static {
		log.debug("# Log inicializado!");
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Map<String, Object> map) {
		return this.init(map);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ModelAndView init(Map<String, Object> map) {
		map.put("usuarios", usuarioService.getAll());
		map.put("command", new UsuarioForm());
		return new ModelAndView(PAGE_CONTEXT, map);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView salvar(
			@Valid @ModelAttribute("command") UsuarioForm form,
			BindingResult result, Map<String, Object> map) {
		log.debug("# Operacao: salvar");
		
		if (result.hasErrors()) {
			map.put("mensagem", message.getMessage(
					"spring.boot.controller.save.msg.campos", null,
					Locale.getDefault()));
			map.put("usuarios", usuarioService.getAll());
			map.put("command", form);
			return new ModelAndView(PAGE_CONTEXT, map);
		}

		Usuario usrTemp = usuarioService.findByNome(form.getNome());

		this.printCacheInfo(form.getNome());
		
		// Verifica se existe o usuario, se sim, atualiza as informacoes
		if (usrTemp != null) {
			usrTemp.setUltimoNome(form.getUltimoNome());
			usrTemp.setTelefone(form.getTelefone());
			usuarioService.save(usrTemp);
		} else {
			usuarioService.save(form.getUsuarioEntityFromUsuarioForm());
		}

		return this.init(map);
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ModelAndView getUser(@RequestParam("nome") String nome,
			Map<String, Object> map) {
		log.debug("# Operacao: getUser [name] = " + nome);
		
		Usuario usuario = usuarioService.findByNome(nome.toUpperCase());

		this.printCacheInfo(nome);
		
		if (usuario == null) {
			map.put("mensagem", message.getMessage(
					"spring.boot.controller.getuser.msg.campos",
					new Object[] { nome }, Locale.getDefault()));
			return this.init(map);
		}

		map.put("usuarios", usuarioService.getAll());
		map.put("command", usuario.getUsuarioFormFromUsuarioEntity());

		return new ModelAndView(PAGE_CONTEXT, map);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("nome") String nome,
			Map<String, Object> map) {
		log.debug("# Operacao: remove");
		
		Usuario usuario = usuarioService.findByNome(nome);

		if (usuario == null) {
			map.put("mensagem", message.getMessage(
					"spring.boot.controller.remove.msg.campos",
					new Object[] { nome }, Locale.getDefault()));
			return this.init(map);
		}

		usuarioService.remove(usuario);

		return this.init(map);
	}
	
	private void printCacheInfo(String name) {
		log.debug(" -> Check caching... : " + name);
		Cache cache = cacheManager.getCache("findUsuario");
		log.debug(" -> Cache is null? " + (cache == null));
		SimpleValueWrapper wrapper = (SimpleValueWrapper) cache.get(name);
		log.debug(" -> Wrapper is null? " + (wrapper == null));
		log.debug(" -> Wrapper get is null? " + (wrapper != null ? wrapper.get() == null : "Wrapper nulo!"));
		if (wrapper != null
				&& wrapper.get() != null) {
			log.debug(" --> Cache: " + cache.getName() 
					+ "\n --> " + wrapper
					+ "\n --> " + (Usuario) wrapper.get());
		}
	}
}