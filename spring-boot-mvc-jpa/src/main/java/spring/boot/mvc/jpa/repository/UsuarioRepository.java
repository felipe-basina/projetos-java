package spring.boot.mvc.jpa.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import spring.boot.mvc.jpa.model.Usuario;

@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    List<Usuario> findByNome(String nome);

}
