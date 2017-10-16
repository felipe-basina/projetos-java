package spring.jpa.eclipselink;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spring.jpa.eclipselink.context.SpringContext;
import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.domain.Processing;
import spring.jpa.eclipselink.service.ProcessingInterfaceImpl;

public class ProcessingTest {

	private static SpringContext context;

	private static ProcessingInterfaceImpl processingService;

	private static final SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = SpringContext.getInstance();

		processingService = context.getBean(ProcessingInterfaceImpl.class);

		System.out.println(" >>>>>> Inicio :: " + SDF.format(new Date()));
	}

	@AfterClass
	public static void setDownAfterClass() {
		System.out.println(" >>>>>> Fim :: " + SDF.format(new Date()));
	}

	@Test
	public void recuperarEmpresaPorNome() {
		CompanyGroup companyGroup = new CompanyGroup();
		companyGroup.setId(49L);
		
		List<Processing> mensagens = processingService.recuperarPorGrupoEmpresa(companyGroup);
		System.out.println("\tTotal de registros = ".concat(String.valueOf(mensagens.size())));
		for (Processing mensagem : mensagens) {
			System.out.println("###\t".concat(mensagem.toString()));
		}
	}
	
	@Test
	public void recuperarPorCdTransacaoSms() {
		List<Processing> mensagens = processingService.recuperarPorCdTransacaoSms(1443204756676L);
		System.out.println("\tTotal de registros = ".concat(String.valueOf(mensagens.size())));
		for (Processing mensagem : mensagens) {
			System.out.println("###\t".concat(mensagem.toString()));
		}
	}
	
	@Test
	public void recuperarPorCdTransacaoSmsEMsisdn() {
		List<Processing> mensagens = processingService.recuperarPorCdTransacaoSmsEMsisdn(1443204756676L, "1199887762");
		System.out.println("\tTotal de registros = ".concat(String.valueOf(mensagens.size())));
		for (Processing mensagem : mensagens) {
			System.out.println("###\t".concat(mensagem.toString()));
		}
	}
}