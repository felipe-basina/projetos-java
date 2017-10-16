package br.com.spring.schedule.jms.process;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Felipe
 *
 * Classe que executa em intervalo pré definido de acordo com a configuração
 * do arquivo spring-schedule.xml
 * 
 */
@Service
public class StartProcess {

	private static final Logger LOGGER = Logger.getLogger(StartProcess.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");

	private static final String LOAD_QUEUE_NAME = "jms/LoadMessageJmsQueue";

	@Autowired
	private JmsTemplate jmsTemplate;

	private static Integer contador = 0;

	public void execute() {
		LOGGER.info("------------------------------------------------------------------");
		LOGGER.info("\tIniciando processamento {" + ++contador
				+ "} em [  ".concat(sdf.format(new Date())).concat("  ]"));
		LOGGER.info("------------------------------------------------------------------");
		try {
			Long appointmentId = 301L;

			// Envia para fila de recuperação + atualização da agenda
			jmsTemplate.convertAndSend(LOAD_QUEUE_NAME, appointmentId);
		} catch (Exception ex) {
			LOGGER.error("\n\tErro ao enviar objeto para fila: ".concat(ex
					.getMessage()), ex);
		}
	}
}