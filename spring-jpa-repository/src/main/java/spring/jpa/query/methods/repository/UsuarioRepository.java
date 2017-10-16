package spring.jpa.query.methods.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.jpa.query.methods.model.Usuario;

public interface UsuarioRepository extends BaseRepository<Usuario, Long> {

	List<Usuario> findByNome(String nome);

	List<Usuario> findByUltimoNome(String ultimoNome);

	@Query("SELECT u FROM usuario u WHERE UPPER(u.ultimoNome) like UPPER(:ultimoNome)")
	List<Usuario> find(@Param("ultimoNome") String ultimoNome);

	@Query("select u from usuario u JOIN FETCH u.enderecos e WHERE UPPER(u.nome) = UPPER(:nome)")
	Usuario findFullInformation(@Param("nome") String nome);

	Usuario getByIdFromNativeQuery(@Param("id") Long id);

}