package spring.sgvas.repository;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import spring.sgvas.repository.context.SpringContext;
import spring.sgvas.repository.entidades.Pagamento;

public class PagamentoRepositoryTest {

	private static PagamentoRepository pagamentoRepository;
	
	@Before
	public void setUp() throws Exception {
		pagamentoRepository = SpringContext.getInstance().getBean(PagamentoRepository.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void recuperarTodosPagamentos() {
		List<Pagamento> pagamentoLista = pagamentoRepository.recuperarTodosPagamentos();
		Assert.assertNotNull(pagamentoLista);
	}

	@Test
	public void recuperarTodosPagamentosJoin() {
		List<Pagamento> pagamentoLista = pagamentoRepository.recuperarTodosPagamentosJoin();
		Assert.assertNotNull(pagamentoLista);
	}

}
