package spring.sgvas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import spring.sgvas.repository.entidades.Assinante;

public interface AssinanteRepository extends CrudRepository<Assinante, String> {

	@Query(name = "recuperarTodosAssinantes", value = "SELECT a FROM Assinante a")
	public List<Assinante> recuperarTodosAssinantes();

}
