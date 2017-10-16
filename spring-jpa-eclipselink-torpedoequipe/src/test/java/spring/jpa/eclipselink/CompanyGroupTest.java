package spring.jpa.eclipselink;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spring.jpa.eclipselink.context.SpringContext;
import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.domain.User;
import spring.jpa.eclipselink.domain.UserGroup;
import spring.jpa.eclipselink.exception.DataException;
import spring.jpa.eclipselink.service.CompanyGroupImplInterface;
import spring.jpa.eclipselink.service.CompanyGroupInterface;
import spring.jpa.eclipselink.service.UserInterface;
import spring.jpa.eclipselink.service.UserInterfaceImpl;

public class CompanyGroupTest {

	private static SpringContext context;

	private static UserInterface uService;

	private static CompanyGroupInterface cgService;

	private static final SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = SpringContext.getInstance();

		uService = context.getBean(UserInterfaceImpl.class);
		cgService = context.getBean(CompanyGroupImplInterface.class);

		System.out.println(" >>>>>> Inicio :: " + SDF.format(new Date()));
	}

	@AfterClass
	public static void setDownAfterClass() {
		System.out.println(" >>>>>> Fim :: " + SDF.format(new Date()));
	}

	@Test
	public void recuperarGrupoEmpresaPorGrupoId() {
		Long id = 53L;
		
		CompanyGroup cg = cgService.findAllById(id);
		System.out.println(" ### " + cg);
		for (UserGroup ug : cg.getUserGroup()) {
			System.out.println(" >>> " + ug);
		}
	}

	@Test
	public void recuperarGrupoEmpresaPorMsisdn() {
		String msisdn = "11973008408";

		try {
			User usuario = uService.recuperarUsuarioPorMsisdnJoin(msisdn);

			// List<CompanyGroup> lista = cgService.findAllByCompany(usuario);
			List<CompanyGroup> lista = cgService
					.findAllByCompanyAndUser(usuario);
			// System.out.println(" ### Total de registros :: ".concat(String.valueOf(lista.size())));
			// System.out.println(" ### Total de registros lista :: ".concat(String.valueOf(lista.get(0).getUserGroup().size())));
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void recuperarGrupoEmpresaPorMsisdnJoin() {
		// String msisdn = "11973008408";

		String msisdn = "11962968409";

		try {
			User usuario = uService.recuperarUsuarioPorMsisdnJoin(msisdn);

			List<CompanyGroup> lista = cgService.findAllByCompanyJoin(usuario);

			System.out.println("\n ### Total de registros :: ".concat(String
					.valueOf(lista.size())));
			System.out
					.println(" ### Total de registros lista :: ".concat(String
							.valueOf(lista.get(0).getUserGroup().size())));

			for (CompanyGroup cg : lista) {
				System.out.println("\n" + cg + "\n" + this.printUserGroup(cg));
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void recuperarGrupoEmpresaPorMsisdnJoin2() {
		String msisdn = "11973008408";

		try {
			User usuario = uService.recuperarUsuarioPorMsisdnJoin(msisdn);

			List<CompanyGroup> lista = cgService.findAllByCompanyJoin2(usuario);

			System.out.println("\n ### Total de registros :: ".concat(String
					.valueOf(lista.size())));
			System.out
					.println(" ### Total de registros lista :: ".concat(String
							.valueOf(lista.get(0).getUserGroup().size())));

			for (CompanyGroup cg : lista) {
				System.out.println("\n" + cg + "\n" + this.printUserGroup(cg));
			}
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	private String printUserGroup(CompanyGroup cg) {

		StringBuilder sb = new StringBuilder();

		if (cg.getUserGroup() != null) {
			System.out.println("\tTamanho lista 2 = " + cg.getUserGroup().size());
		}
		
		for (UserGroup ug : cg.getUserGroup()) {
			sb.append("\n....... " + ug.getCompanyGroup().getGroupName()
					+ "\n....... " + ug.getUser()
					+ "\n....... " + ug.getUser().getCompany()
					+ "\n....... " + ug.getUser().getProfile());
		}

		return sb.toString();
	}
	
	@Test
	public void recuperarPorGrupoEmpresaNome() {
		String nomeGrupoEmpresa = "Grupo usuário 01";
		List<CompanyGroup> companyGroups = cgService.recuperarPorGrupoEmpresaNome(nomeGrupoEmpresa);
		System.out.println("\tTamanho lista = " + companyGroups.size());
		for (CompanyGroup companyGroup : companyGroups) {
			System.out.println("###\t".concat(this.printUserGroup(companyGroup)));
		}
	}
	
	@Test
	public void recuperarPorIdEmpresa() {
		//Long id = 49L;
		Long id = 53L;
		List<CompanyGroup> companyGroups = cgService.recuperarPorGrupoEmpresaId(id);
		System.out.println("\tTamanho lista = " + companyGroups.size());
		for (CompanyGroup companyGroup : companyGroups) {
			System.out.println("###\tGrupo empresa: " + companyGroup);
			System.out.println("###\t".concat(this.printUserGroup(companyGroup)));
		}
	}
}