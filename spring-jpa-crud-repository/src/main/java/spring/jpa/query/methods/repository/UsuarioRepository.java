package spring.jpa.query.methods.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import spring.jpa.query.methods.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	List<Usuario> findByNome(String nome);

}
