package spring.boot.mvc.jpa.weblogic.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import spring.boot.mvc.jpa.weblogic.model.Usuario;

@Transactional
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

	List<Usuario> findByNome(String nome);

	@Override
	// @Query(value="SELECT MAX(IDENTIFICADOR) FROM REGISTRO_TBL",
	// nativeQuery=true)
	@Query(value = "SELECT MAX(id) FROM usuario", nativeQuery = false)
	public long getLastId();
}
