package spring.boot.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.boot.mvc.model.Usuario;

@Controller
public class CRUDController {

    private static Logger log = LoggerFactory.getLogger(CRUDController.class);

    private static List<Usuario> usuarios = new ArrayList<Usuario>();

    private static final String PAGE_CONTEXT = "usuario/form";

    @Autowired
    private MessageSource message;

    static {
	log.debug("\n\n Log inicializado! \n\n");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(Map<String, Object> map) {
	return this.init(map);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ModelAndView init(Map<String, Object> map) {
	map.put("usuarios", usuarios);
	map.put("command", new Usuario());
	return new ModelAndView(PAGE_CONTEXT, map);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView salvar(
	    @Valid @ModelAttribute("command") Usuario usuario,
	    BindingResult result, Map<String, Object> map) {

	if (result.hasErrors()) {
	    map.put("mensagem", message.getMessage(
		    "spring.boot.controller.save.msg.campos", null,
		    Locale.getDefault()));
	    map.put("usuarios", usuarios);
	    map.put("command", usuario);
	    return new ModelAndView(PAGE_CONTEXT, map);
	}

	Usuario usrTemp = this.getUsuarioPorNome(usuario.getNome());

	// Verifica se existe o usuario, se sim, atualiza as informacoes
	if (usrTemp != null) {
	    usrTemp.setUltimoNome(usuario.getUltimoNome());
	    usrTemp.setTelefone(usuario.getTelefone());
	} else {
	    // Caso contrario, adiciona na lista existente
	    usuarios.add(usuario);
	}

	return this.init(map);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ModelAndView getUser(@RequestParam("nome") String nome,
	    Map<String, Object> map) {

	Usuario usuario = this.getUsuarioPorNome(nome);

	if (usuario == null) {
	    map.put("mensagem", message.getMessage(
		    "spring.boot.controller.getuser.msg.campos",
		    new Object[] { nome }, Locale.getDefault()));
	    return this.init(map);
	}

	map.put("usuarios", usuarios);
	map.put("command", usuario);

	return new ModelAndView(PAGE_CONTEXT, map);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam("nome") String nome,
	    Map<String, Object> map) {

	Usuario usuario = this.getUsuarioPorNome(nome);

	if (usuario == null) {
	    map.put("mensagem", message.getMessage(
		    "spring.boot.controller.remove.msg.campos",
		    new Object[] { nome }, Locale.getDefault()));
	    return this.init(map);
	}

	usuarios.remove(usuario);

	return this.init(map);
    }

    private Usuario getUsuarioPorNome(String nome) {

	log.debug("\n\n Recuperando usuario [" + nome + "]\n\n");

	for (Usuario usr : usuarios) {
	    if (usr.getNome().equalsIgnoreCase(nome)) {
		return usr;
	    }
	}

	return null;
    }
}