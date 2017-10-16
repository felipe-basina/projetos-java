package spring.jpa.eclipselink;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spring.jpa.eclipselink.context.SpringContext;
import spring.jpa.eclipselink.domain.Receipt;
import spring.jpa.eclipselink.service.ReceiptInterfaceImpl;

public class ReceiptTest {

	private static SpringContext context;

	private static ReceiptInterfaceImpl receiptService;

	private static final SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = SpringContext.getInstance();

		receiptService = context.getBean(ReceiptInterfaceImpl.class);

		System.out.println(" >>>>>> Inicio :: " + SDF.format(new Date()));
	}

	@AfterClass
	public static void setDownAfterClass() {
		System.out.println(" >>>>>> Fim :: " + SDF.format(new Date()));
	}

	@Test
	public void recuperarTodosPorFaixa() {
		List<Receipt> recibos = receiptService.recuperarTodosPorFaixa(100);
		for (Receipt recibo : recibos) {
			System.out.println("###\t" + recibo);
		}
	}
}