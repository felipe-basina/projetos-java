package spring.jpa.eclipselink;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spring.jpa.eclipselink.context.SpringContext;
import spring.jpa.eclipselink.domain.SPSMessage;
import spring.jpa.eclipselink.service.SPSMessageInterfaceImpl;

public class SPSMessageTest {

	private static SpringContext context;

	private static SPSMessageInterfaceImpl spsMessageService;

	private static final SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = SpringContext.getInstance();

		spsMessageService = context.getBean(SPSMessageInterfaceImpl.class);

		System.out.println(" >>>>>> Inicio :: " + SDF.format(new Date()));
	}

	@AfterClass
	public static void setDownAfterClass() {
		System.out.println(" >>>>>> Fim :: " + SDF.format(new Date()));
	}

	@Test
	public void recuperarPorMsisdn() {
		SPSMessage spsMessage = spsMessageService.recuperarPorMsisdn("9187654321");
		System.out.println("###\t" + spsMessage);
	}
	
	@Test
	public void recuperarPorMsisdnEStatus() {
		SPSMessage spsMessage = spsMessageService.recuperarPorMsisdnEStatus("3377778885", "ATIVO");
		System.out.println("###\t" + spsMessage);
	}
}