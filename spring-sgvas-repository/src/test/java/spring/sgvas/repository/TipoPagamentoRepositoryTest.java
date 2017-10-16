package spring.sgvas.repository;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import spring.sgvas.repository.context.SpringContext;
import spring.sgvas.repository.entidades.TipoPagamento;

public class TipoPagamentoRepositoryTest {

	private static TipoPagamentoRepository tipoPagamentoRepository;
	
	@Before
	public void setUp() throws Exception {
		tipoPagamentoRepository = SpringContext.getInstance().getBean(TipoPagamentoRepository.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() {
		List<TipoPagamento> tipoPagamentoLista = (List<TipoPagamento>) tipoPagamentoRepository.findAll();
		Assert.assertNotNull(tipoPagamentoLista);
	}

}
