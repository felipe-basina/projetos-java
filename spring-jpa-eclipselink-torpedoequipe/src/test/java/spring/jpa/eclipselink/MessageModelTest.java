package spring.jpa.eclipselink;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spring.jpa.eclipselink.context.SpringContext;
import spring.jpa.eclipselink.domain.Appointment;
import spring.jpa.eclipselink.domain.MessageModel;
import spring.jpa.eclipselink.service.MessageModelInterface;
import spring.jpa.eclipselink.service.MessageModelService;

public class MessageModelTest {

	private static SpringContext context;

	private static MessageModelInterface messageModelService;

	private static final SimpleDateFormat SDF = new SimpleDateFormat("hh:mm:ss");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = SpringContext.getInstance();

		messageModelService = context.getBean(MessageModelService.class);

		System.out.println(" >>>>>> Inicio :: " + SDF.format(new Date()));
	}

	@AfterClass
	public static void setDownAfterClass() {
		System.out.println(" >>>>>> Fim :: " + SDF.format(new Date()));
	}

	@Test
	public void recuperarMensagensPorIdEmpresa() {
		Long idEmpresa = 21L;
		
		List<MessageModel> messages = messageModelService.recuperarMensagensPorIdEmpresa(idEmpresa);
		for (MessageModel mm : messages) {
			System.out.println("###\tModelo mensagem: " + mm);
			System.out.println("###\tModelo.usuario: " + mm.getUser());
			for (Appointment agenda : mm.getAppointment()) {
				System.out.println("###\tAgenda: " + agenda);
			}
		}
	}
}