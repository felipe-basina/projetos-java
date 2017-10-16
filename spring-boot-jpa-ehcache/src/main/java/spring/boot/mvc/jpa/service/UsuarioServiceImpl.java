package spring.boot.mvc.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import spring.boot.mvc.jpa.form.model.UsuarioForm;
import spring.boot.mvc.jpa.model.Usuario;
import spring.boot.mvc.jpa.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	private UsuarioRepository usrRepo;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usrRepo) {
		this.usrRepo = usrRepo;
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usrRepo.save(usuario);
	}

	@Override
	public List<UsuarioForm> getAll() {
		List<UsuarioForm> usuarios = new ArrayList<UsuarioForm>();
		for (Usuario usuario : usrRepo.findAll()) {
			usuarios.add(usuario.getUsuarioFormFromUsuarioEntity());
		}
		return usuarios;
	}

	@Override
	public Usuario update(Usuario usuario) {
		return usrRepo.save(usuario);
	}

	@Override
	@CacheEvict(beforeInvocation = true, value = {"findByNomeCache", "findUsuario"}, key = "#p0.nome")
	public void remove(Usuario usuario) {
		usrRepo.delete(usuario.getId());
	}

	@Override
	public Usuario findByNome(String name) {
		LOGGER.debug("... running consult at data base...");
		List<Usuario> usuarios = usrRepo.findByNome(name);
		System.out.println(usuarios == null);
		System.out.println(usuarios != null ? usuarios.size() : -1);
		LOGGER.debug(" --> User found? " + (usuarios != null && usuarios.size() > 0));
		if (usuarios != null
				&& usuarios.size() > 0) {
			return usuarios.get(0);
		}
		return null;
		//return usrRepo.findUsuario(name);
	}
	
}
