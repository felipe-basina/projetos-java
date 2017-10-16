package spring.jpa.eclipselink;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spring.jpa.eclipselink.context.SpringContext;
import spring.jpa.eclipselink.domain.Company;
import spring.jpa.eclipselink.service.CompanyInterfaceImpl;

public class CompanyTest {

	private static SpringContext context;

	private static CompanyInterfaceImpl companyService;

	private static final SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = SpringContext.getInstance();

		companyService = context.getBean(CompanyInterfaceImpl.class);

		System.out.println(" >>>>>> Inicio :: " + SDF.format(new Date()));
	}

	@AfterClass
	public static void setDownAfterClass() {
		System.out.println(" >>>>>> Fim :: " + SDF.format(new Date()));
	}

	@Test
	public void recuperarEmpresaPorNome() {
		Company empresa = companyService.recuperarPorNome("Company-4", true);
		System.out.println("###\t".concat(empresa.toString()));
	}
	
	@Test
	public void recuperarEmpresasPorUsuarioMaster() {
		List<Company> empresas = companyService.recuperarEmpresasPorUsuarioMaster();
		this.printCompanies(empresas);
	}
	
	@Test 
	public void recuperarTodasAtivas() {
		List<Company> empresas = companyService.recuperarTodasAtivas();
		this.printCompanies(empresas);
	}
	
	private void printCompanies(List<Company> empresas) {
		for (Company empresa : empresas) {
			System.out.println("###\t".concat(empresa.toString()));	
		}
	}
	
	@Test
	public void criarEmpresas() {
		List<Company> companies = new ArrayList<Company>();
		
		for (int indice = 0; indice < 10; indice++) {
			Date now = new Date();
			
			String user = "system";
			
			Company company = new Company();
			company.setCreationDate(now);
			company.setUpdateDate(now);
			company.setMsisdnCreationUser(user);
			company.setMsisdnUpdateUser(user);
			company.setActiveRegister(true);
			company.setCompanyName("Company-".concat(String.valueOf(indice)));
			
			companies.add(company);
		}
		
		companyService.criarEmpresas(companies);
	}
}