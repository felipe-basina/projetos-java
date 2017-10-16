package spring.sgvas.repository;

import org.springframework.data.repository.CrudRepository;

import spring.sgvas.repository.entidades.TipoPagamento;

public interface TipoPagamentoRepository extends
		CrudRepository<TipoPagamento, String> {
}
