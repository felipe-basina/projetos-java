package spring.jpa.query.methods;

import java.util.HashSet;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.jpa.query.methods.model.Endereco;
import spring.jpa.query.methods.model.Usuario;
import spring.jpa.query.methods.repository.UsuarioRepository;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CriarRecuperarPorNativeQueryTest {

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
	public void createUserAndGetFromNativeQuery() {
		Usuario usuario = new Usuario("Felipe", "Nascimento", "11973008408");
		usuario.setEnderecos(new HashSet<Endereco>());

		int total = 5;
		for (int i = 0; i < total; i++) {
			Endereco endereco = new Endereco("rua ".concat(String.valueOf(i)),
					"0481101".concat(String.valueOf(i)),
					"Cidade ".concat(String.valueOf(i)));
			endereco.setUsuario(usuario);

			usuario.getEnderecos().add(endereco);
		}

		usuario = repository.save(usuario);

		usuario = repository.getByIdFromNativeQuery(usuario.getId());

		System.out.println(" ### Native query ::: " + usuario);

		repository.delete(usuario.getId());
	}
}
