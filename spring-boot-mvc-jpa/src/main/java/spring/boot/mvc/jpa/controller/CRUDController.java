package spring.boot.mvc.jpa.controller;

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

import spring.boot.mvc.jpa.form.model.UsuarioForm;
import spring.boot.mvc.jpa.model.Usuario;
import spring.boot.mvc.jpa.repository.UsuarioRepository;

@Controller
public class CRUDController {

    private static Logger log = LoggerFactory.getLogger(CRUDController.class);

    private static final String PAGE_CONTEXT = "usuario/form";

    @Autowired
    private MessageSource message;

    @Autowired
    private UsuarioRepository usrRepository;

    static {
	log.debug("\n\n Log inicializado! \n\n");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(Map<String, Object> map) {
	return this.init(map);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ModelAndView init(Map<String, Object> map) {
	map.put("usuarios", this.getAll());
	map.put("command", new UsuarioForm());
	return new ModelAndView(PAGE_CONTEXT, map);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView salvar(
	    @Valid @ModelAttribute("command") UsuarioForm form,
	    BindingResult result, Map<String, Object> map) {

	if (result.hasErrors()) {
	    map.put("mensagem", message.getMessage(
		    "spring.boot.controller.save.msg.campos", null,
		    Locale.getDefault()));
	    map.put("usuarios", this.getAll());
	    map.put("command", form);
	    return new ModelAndView(PAGE_CONTEXT, map);
	}

	Usuario usrTemp = this.getUsuarioPorNome(form.getNome());

	// Verifica se existe o usuario, se sim, atualiza as informacoes
	if (usrTemp != null) {
	    usrTemp.setUltimoNome(form.getUltimoNome());
	    usrTemp.setTelefone(form.getTelefone());
	    usrRepository.save(usrTemp);
	} else {
	    usrRepository.save(form.getUsuarioEntityFromUsuarioForm());
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

	map.put("usuarios", this.getAll());
	map.put("command", usuario.getUsuarioFormFromUsuarioEntity());

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

	usrRepository.delete(usuario);

	return this.init(map);
    }

    private Usuario getUsuarioPorNome(String nome) {

	log.debug("\n\n Recuperando usuario [" + nome + "]\n\n");

	List<Usuario> usuarios = usrRepository.findByNome(nome);
	if (usuarios != null && usuarios.size() > 0) {
	    return usuarios.get(0);
	}

	return null;
    }

    private List<UsuarioForm> getAll() {
	List<UsuarioForm> usuarios = new ArrayList<UsuarioForm>();
	for (Usuario usuario : usrRepository.findAll()) {
	    // log.debug(" ### Usuario ::: " + usuario + "\n");
	    usuarios.add(usuario.getUsuarioFormFromUsuarioEntity());
	}
	return usuarios;
    }
}