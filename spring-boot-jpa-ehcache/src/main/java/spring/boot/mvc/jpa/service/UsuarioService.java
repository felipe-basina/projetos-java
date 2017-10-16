package spring.boot.mvc.jpa.service;

import java.util.List;

import spring.boot.mvc.jpa.form.model.UsuarioForm;
import spring.boot.mvc.jpa.model.Usuario;

public interface UsuarioService {

	public Usuario save(Usuario usuario);
	
	public Usuario update(Usuario usuario);
	
	public void remove(Usuario usuario);
	
	public List<UsuarioForm> getAll();
	
	public Usuario findByNome(String name);
	
}
