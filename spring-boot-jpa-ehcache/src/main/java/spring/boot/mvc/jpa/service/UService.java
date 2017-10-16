package spring.boot.mvc.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.mvc.jpa.form.model.UsuarioForm;
import spring.boot.mvc.jpa.model.Usuario;
import spring.boot.mvc.jpa.repository.UsuarioRepository;

@Service
public class UService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UService.class);
	
	private UsuarioRepository usuarioRepository;

	@Autowired
	public UService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public List<UsuarioForm> getAll() {
		List<UsuarioForm> usuarios = new ArrayList<UsuarioForm>();
		for (Usuario usuario : usuarioRepository.findAll()) {
			usuarios.add(usuario.getUsuarioFormFromUsuarioEntity());
		}
		return usuarios;
	}

	public Usuario update(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public void remove(Usuario usuario) {
		usuarioRepository.delete(usuario.getId());
	}

	//@Cacheable(value = "findUsuario", unless="#result == null")
	public Usuario findByNome(String name) {
		LOGGER.debug("... running consult at data base...");
		/*List<Usuario> usuarios = usuarioRepository.findByNome(name);
		LOGGER.debug(" --> User found? " + (usuarios != null && usuarios.size() > 0));
		if (usuarios != null
				&& usuarios.size() > 0) {
			return usuarios.get(0);
		}*/
		
		//return usuarioRepository.findByNome(name);
		return usuarioRepository.findUsuario(name);
	}
	
}
