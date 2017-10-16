package spring.sgvas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import spring.sgvas.repository.entidades.Assinatura;
import spring.sgvas.repository.entidades.AssinaturaPK;

public interface AssinaturaRepository extends
		CrudRepository<Assinatura, AssinaturaPK> {

	@Query(name = "recuperarTodasAssinaturas", value = "SELECT a FROM Assinatura a")
	public List<Assinatura> recuperarTodasAssinaturas();

	@Query(name = "recuperarTodasAssinaturas", 
			value = "SELECT a FROM Assinatura a "
					+ " JOIN FETCH a.assinante "
					+ " JOIN FETCH a.motivoStatus "
					+ " LEFT JOIN FETCH a.produto ")
	public List<Assinatura> recuperarTodasAssinaturasJoin();
	
	@Query(name = "recuperarTodasAssinaturasJoinNativeQuery",
			value = "SELECT * FROM ASSINATURA A "
					+ " LEFT JOIN ASSINANTE ASS "
					+ " ON ASS.CD_ASSINANTE = A.CD_ASSINANTE "
					+ " LEFT JOIN MOTIVO_STATUS MS "
					+ " ON MS.CD_MOTIVO_STATUS = A.CD_MOTIVO_STATUS "
					+ " LEFT JOIN PRODUTO P "
					+ " ON P.CD_PRODUTO = A.CD_PRODUTO ", nativeQuery = true)
	public List<Assinatura> recuperarTodasAssinaturasJoinNativeQuery();
}
