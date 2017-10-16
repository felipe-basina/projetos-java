package spring.ws.jms.sample.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import spring.ws.jms.sample.QueueUtil;
import spring.ws.jms.sample.pojo.Pessoa;

@Service(value = "prox")
public class ProcessScheduler {

	/** The jms template. */
	@Autowired
	private JmsTemplate jmsTemplate;

	/** The queueUtil. */
	@Autowired
	private QueueUtil queueUtil;

	private static final String QUEUE_NAME = "jms/TestJmsQueue";

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void proc() {

		System.out
				.println("PROCESSANDO....\nMetodo executando... Data/Hora atual :: "
						+ new Date());

		for (int iteracao = 1; iteracao < 4; iteracao++) {
			try {

				queueUtil.verificarSituacaoFila(QUEUE_NAME);

				Random aleatorio = new Random();
				int indice = aleatorio.nextInt(5);

				System.out.println(" Iteracao [" + iteracao + "], indice ["
						+ indice + "]...");

				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.YEAR, -indice);

				Pessoa pessoa = new Pessoa("nome", sdf.format(calendar
						.getTime()));
				System.out
						.println("\n\n\n ##### [ProcessScheduler] Enviado objeto pessoa: "
								+ pessoa + " para fila!");

				jmsTemplate.convertAndSend(QUEUE_NAME, pessoa);

			} catch (Exception ex) {
				System.out.println("\n Erro: " + ex.getMessage());
			}
		}
	}
}