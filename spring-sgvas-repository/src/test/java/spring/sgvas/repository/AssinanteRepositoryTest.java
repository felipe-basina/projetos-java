package spring.sgvas.repository;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import spring.sgvas.repository.context.SpringContext;
import spring.sgvas.repository.entidades.Assinante;

public class AssinanteRepositoryTest {

	private static AssinanteRepository assinanteRepository;

	@BeforeClass
	public static void setUp() {
		assinanteRepository = SpringContext.getInstance().getBean(AssinanteRepository.class);
	}

	@AfterClass
	public static void setDown() {
	}

	@Test
	public void testFindAll() {
		assinanteRepository.findAll();
	}

	@Test
	public void recuperarTodosAssinantes() {
		List<Assinante> assinanteLista = assinanteRepository.recuperarTodosAssinantes();
		Assert.assertNotNull(assinanteLista);
	}
	
}
