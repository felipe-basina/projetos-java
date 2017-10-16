package spring.sgvas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import spring.sgvas.repository.entidades.Pagamento;
import spring.sgvas.repository.entidades.PagamentoPK;

public interface PagamentoRepository extends
		CrudRepository<Pagamento, PagamentoPK> {

	@Query(name = "recuperarTodosPagamentos", value = "SELECT p FROM Pagamento p")
	public List<Pagamento> recuperarTodosPagamentos();

	@Query(name = "recuperarTodosPagamentosJoin", value = "SELECT p FROM Pagamento p "
			+ " JOIN FETCH p.produto "
			+ " JOIN FETCH p.recorrenciaPagamento "
			+ " JOIN FETCH p.tipoPagamento ")
	public List<Pagamento> recuperarTodosPagamentosJoin();
	
}
