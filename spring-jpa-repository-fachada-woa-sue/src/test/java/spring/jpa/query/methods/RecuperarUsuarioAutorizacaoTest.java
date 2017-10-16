package spring.jpa.query.methods;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.jpa.query.methods.model.Recurso;
import spring.jpa.query.methods.model.RecursoParametro;
import spring.jpa.query.methods.model.UsuarioAutorizacao;
import spring.jpa.query.methods.model.UsuarioPerfilRecurso;
import spring.jpa.query.methods.service.BaseService;
import spring.jpa.query.methods.service.IBaseService;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecuperarUsuarioAutorizacaoTest {

	private static IBaseService service;

	private static AbstractApplicationContext context;

	@BeforeClass
	public static void setUp() {
		context = new ClassPathXmlApplicationContext("application-context.xml");
		service = context.getBean(BaseService.class);
	}

	@AfterClass
	public static void setDown() {
		context.close();
	}

	@Test
	public void getByLogin() {
		String email = "paulo.marcondes@claro.com.br";
		// String email = "eliezer.santos@claro.com.br";

		List<UsuarioAutorizacao> usuarios = service.findByEmail(email);
		for (UsuarioAutorizacao usuario : usuarios) {
			System.out.println(usuario);
			this.printMatrizAutorizacao(usuario);
		}
	}

	private String printRecursoParametro(Recurso recurso) {
		StringBuilder sb = new StringBuilder();

		for (RecursoParametro rp : recurso.getRecursoParametros()) {
			sb.append("\n" + rp);
		}

		return sb.toString();
	}

	private void printMatrizAutorizacao(UsuarioAutorizacao usuario) {
		int indice = 0;

		for (UsuarioPerfilRecurso upr : usuario.getMatrizAutorizacoes()) {
			System.out.println(" ###### " + ++indice);
			System.out
					.println(upr.getUsuarioAutorizacao()
							+ "\n"
							+ upr.getRecurso()
							+ this.printRecursoParametro(upr.getRecurso())
							+ "\n"
							+ upr.getPerfil()
							+ "\n/*-------------------------------------------------------------------*/");
		}
	}
}
