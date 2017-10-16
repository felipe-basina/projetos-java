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
public class RecuperarTodosTest {

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
	public void createUsers() {
		int total = 10;
		for (int i = 0; i < total; i++) {
			Usuario usuario = new Usuario("Usuario ".concat(String.valueOf(i)),
					"Ultimo Nome ".concat(String.valueOf(i)),
					"1198765432".concat(String.valueOf(i)));
			repository.save(usuario);
		}
	}

	@Test
	public void getAll() {
		List<Usuario> usuarios = repository.findAll();
		for (Usuario usuario : usuarios) {
			System.out.println("\n #### " + usuario);
		}
	}
}
