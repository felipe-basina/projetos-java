package spring.jpa.query.methods;

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
public class AppTest {

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
	public void createUser() {
		Usuario usuario = new Usuario("Felipe", "Nascimento", "11973008408");
		repository.save(usuario);
	}

	@Test
	public void fetchUser() {
		String nome = "Felipe";
		System.out.println(" ## " + repository.findByNome(nome));
	}

	@Test
	public void removeUser() {
		repository.delete(repository.findByNome("Felipe"));
	}
}
