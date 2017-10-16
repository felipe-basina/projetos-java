package spring.sgvas.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import spring.sgvas.repository.context.SpringContext;
import spring.sgvas.repository.entidades.Assinatura;

public class AssinaturaRepositoryTest {
	
	private static AssinaturaRepository assinaturaRepository;
	private static EntityManager em;

	@BeforeClass
	public static void setUp() {
		assinaturaRepository = SpringContext.getInstance().getBean(AssinaturaRepository.class);
		em = SpringContext.getInstance().getBean(EntityManager.class);
	}

	@AfterClass
	public static void setDown() {
	}

	@Test
	public void recuperarEntityManager() {
		Assert.assertNotNull(em);
	}
	
	@Test
	public void recuperarTodasAssinaturas() {
		List<Assinatura> assinaturaLista = assinaturaRepository.recuperarTodasAssinaturas();
		Assert.assertNotNull(assinaturaLista);
		Assert.assertTrue(assinaturaLista.size() > 0);
		Assert.assertTrue(assinaturaLista.size() == 2);
	}

	@Test
	public void recuperarTodasAssinaturasJoinPorEntityManagerObjeto() {
		String sql = "SELECT A.* FROM ASSINATURA A "
				+ " LEFT OUTER JOIN ASSINANTE ASS "
				+ " ON ASS.CD_ASSINANTE = A.CD_ASSINANTE "
				+ " LEFT OUTER JOIN  MOTIVO_STATUS MS "
				+ " ON MS.CD_MOTIVO_STATUS = A.CD_MOTIVO_STATUS "
				+ " LEFT OUTER JOIN PRODUTO P "
				+ " ON P.CD_PRODUTO = A.CD_PRODUTO ";
		
		Query consulta = em.createNativeQuery(sql);
		
		@SuppressWarnings("unchecked")
		List<Object> objetoLista = (ArrayList<Object>) consulta.getResultList();
		Assert.assertNotNull(objetoLista);
		Assert.assertTrue(objetoLista.size() > 0);
		Assert.assertTrue(objetoLista.size() == 2);
	}

	@Test
	public void recuperarTodasAssinaturasJoinPorEntityManager() {
		String sql = "SELECT A.* FROM ASSINATURA A "
				+ " LEFT OUTER JOIN ASSINANTE ASS "
				+ " ON ASS.CD_ASSINANTE = A.CD_ASSINANTE "
				+ " LEFT OUTER JOIN  MOTIVO_STATUS MS "
				+ " ON MS.CD_MOTIVO_STATUS = A.CD_MOTIVO_STATUS "
				+ " LEFT OUTER JOIN PRODUTO P "
				+ " ON P.CD_PRODUTO = A.CD_PRODUTO ";
		
		Query consulta = em.createNativeQuery(sql, Assinatura.class);
		
		@SuppressWarnings("unchecked")
		List<Assinatura> assinaturaLista = (ArrayList<Assinatura>) consulta.getResultList();
		assinaturaLista.size();
		
		Assert.assertNotNull(assinaturaLista);
		Assert.assertTrue(assinaturaLista.size() > 0);
		Assert.assertTrue(assinaturaLista.size() == 2);
		
		this.imprimirAssinaturas(assinaturaLista);
	}

	@Test
	public void recuperarTodasAssinaturasJoin() {
		List<Assinatura> assinaturaLista = assinaturaRepository.recuperarTodasAssinaturasJoin();
		Assert.assertNotNull(assinaturaLista);
		Assert.assertTrue(assinaturaLista.size() > 0);
		Assert.assertTrue(assinaturaLista.size() == 2);
		
		this.imprimirAssinaturas(assinaturaLista);
	}

	@Test
	public void recuperarTodasAssinaturasJoinNativeQuery() {
		List<Assinatura> assinaturaLista = assinaturaRepository.recuperarTodasAssinaturasJoinNativeQuery();
		Assert.assertNotNull(assinaturaLista);
		Assert.assertTrue(assinaturaLista.size() > 0);
		Assert.assertTrue(assinaturaLista.size() == 2);
		
		this.imprimirAssinaturas(assinaturaLista);
	}

	private void imprimirAssinaturas(List<Assinatura> assinaturaLista) {
		for (Assinatura assinatura : assinaturaLista) {
			System.out.println("\n... " + assinatura
					+ "\n... " + assinatura.getId()
					+ "\n... " + assinatura.getProduto()
					+ "\n... " + assinatura.getAssinante()
					+ "\n... " + assinatura.getMotivoStatus()
					+ "\n... cdPagamento: " + assinatura.getCdPagamento()
					+ "\n... Lista parâmetros = " + assinatura.getListaParametros());
			
			// Imprime os detalhes do produto
			/*for (DetalheProduto detalheProduto : assinatura.getProduto()
					.getDetalheProdutos()) {
				
			}*/
		}
	}
	
}
