package spring.jpa.eclipselink;

import org.junit.BeforeClass;
import org.junit.Test;

import spring.jpa.eclipselink.context.SpringContext;
import spring.jpa.eclipselink.domain.Perfil;
import spring.jpa.eclipselink.domain.PerfilPermissao;
import spring.jpa.eclipselink.domain.PermissaoParametro;
import spring.jpa.eclipselink.domain.UsuarioAutenticacao;
import spring.jpa.eclipselink.domain.UsuarioAutorizacao;
import spring.jpa.eclipselink.domain.UsuarioPerfilRecurso;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.service.ServiceImpl;
import spring.jpa.eclipselink.service.ServiceInterface;

public class AutorizacaoTest {

	private static SpringContext context;

	private static ServiceInterface service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = SpringContext.getInstance();
		service = context.getBean(ServiceImpl.class);
	}

	@Test
	public void recuperarUsuarioAutenticacao() {
		String login = "paulo.marcondes@claro.com.br";

		try {
			UsuarioAutenticacao usrAutenticacao = service
					.recuperarUsuarioAutenticacao(login);
			System.out.println(" #### " + usrAutenticacao);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void recuperarRecursosUsuario() {
		String login = "paulo.marcondes@claro.com.br";
		//String login = "denis.boquinha@claro.com.br";

		try {
			UsuarioAutorizacao usrAutorizacao = service
					.listarRecursosUsuario(login);

			if (usrAutorizacao != null) {
				System.out.println(" #### " + usrAutorizacao);
				System.out
						.println(" ------------------------------------------------ ");
				for (UsuarioPerfilRecurso upr : usrAutorizacao
						.getMatrizAutorizacoes()) {
					System.out
							.println(" ### "
									+ upr.getPerfil() + " - total de permissoes: " + upr.getPerfil().getMatrizPermissoesPerfil().size()
									+ " Imprimindo valores sobre permissoes: " + this.imprimirPermissoes(upr.getPerfil())
									+ " \n "
									+ upr.getRecurso() + " - total de parametros: " + upr.getRecurso().getRecursoParametros().size()
									+ "\n/*--------------------------------------------------------------*/");
				}
			}

		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	private String imprimirPermissoes(Perfil perfil) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n**" + perfil + "**");
		for (PerfilPermissao pp : perfil.getMatrizPermissoesPerfil()) {
			sb.append("\n--------------------");
			sb.append("\n" + pp.getPermissao());
			sb.append("\n--------------------");
			for (PermissaoParametro parametro : pp.getPermissao().getPermissaoParametros()) {
				sb.append("\n" + parametro);
			}
		}
		return sb.toString();
	}
}
