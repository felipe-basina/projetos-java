package spring.boot.mvc.jpa.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.boot.mvc.jpa.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Cacheable(value = "findByNomeCache", unless = "#result.size() <= 0", key="#p0")
	public List<Usuario> findByNome(String nome);

	/**
	 * Realiza o cache somente para retornos diferente de nulo
	 **/
	@Cacheable(value = "findUsuario", unless = "#result == null")
	@Query(value = "SELECT u FROM usuario u WHERE UPPER(u.nome) = UPPER(:nome)")
	public Usuario findUsuario(@Param("nome") String nome);

	public Usuario findByUltimoNome(String ultimoNome);

}