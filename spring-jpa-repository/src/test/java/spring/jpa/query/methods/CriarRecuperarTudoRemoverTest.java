package spring.jpa.query.methods;

import java.util.HashSet;
import java.util.List;

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
public class CriarRecuperarTudoRemoverTest {

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
		System.out.println(" ### Salvo: " + usuario);

		usuario.setUltimoNome("B. S. do Nascimento");

		usuario = repository.save(usuario);
		System.out.println(" ### Atualizado: " + usuario);
	}

	@Test
	public void getAllUserInformation() {
		Usuario usuario = repository.findFullInformation("felipe");
		System.err.println(" ### " + usuario);
		for (Endereco endereco : usuario.getEnderecos()) {
			System.out.println(" ### " + endereco);
		}
	}

	@Test
	public void removeUsers() {
		List<Usuario> usuarios = repository.findAll();
		for (Usuario usuario : usuarios) {
			repository.delete(usuario.getId());
		}
	}
}
