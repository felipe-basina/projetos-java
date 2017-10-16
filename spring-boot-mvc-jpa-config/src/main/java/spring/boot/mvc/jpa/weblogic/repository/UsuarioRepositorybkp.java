package spring.boot.mvc.jpa.weblogic.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import spring.boot.mvc.jpa.weblogic.model.Usuario;

@Transactional
public interface UsuarioRepositorybkp extends CrudRepository<Usuario, Long> {

	List<Usuario> findByNome(String nome);

}
