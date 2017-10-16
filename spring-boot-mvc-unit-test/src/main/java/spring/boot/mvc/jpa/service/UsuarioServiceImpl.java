package spring.boot.mvc.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.mvc.jpa.form.model.UsuarioForm;
import spring.boot.mvc.jpa.model.Usuario;
import spring.boot.mvc.jpa.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

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
	public void remove(Usuario usuario) {
		usrRepo.delete(usuario.getId());
	}

	@Override
	public Usuario findByNome(String name) {
		List<Usuario> usuarios = usrRepo.findByNome(name);
		if (usuarios != null
				&& usuarios.size() > 0) {
			return usuarios.get(0);
		}
		return null;
	}
	
}
