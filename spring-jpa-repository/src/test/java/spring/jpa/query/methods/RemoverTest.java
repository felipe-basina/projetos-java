package spring.jpa.query.methods;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.jpa.query.methods.model.Usuario;
import spring.jpa.query.methods.repository.UsuarioRepository;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RemoverTest {

	private static UsuarioRepository repository;

	private static AbstractApplicationContext context;

	@BeforeClass
	public static void setUp() {
		context = new ClassPathXmlApplicationContext("application-context.xml");
		repository = context.getBean(UsuarioRepository.class);
	}

	@AfterClass
	public static void setDown() {
		context.close();
	}

	@Test
	public void deleteUsers() {
		List<Usuario> usuarios = repository.findAll();
		for (Usuario usuario : usuarios) {
			repository.delete(usuario.getId());
		}
	}
}
