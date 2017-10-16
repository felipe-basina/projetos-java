package br.com.spring.schedule.jms.jms;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import br.com.spring.schedule.jms.domain.Appointment;
import br.com.spring.schedule.jms.domain.Processing;
import br.com.spring.schedule.jms.service.GenericService;

/**
 * 
 * @author Felipe
 *
 * Classe listener da fila jms/LoadMessageJmsQueue definida no arquivo
 * spring-jms.xml
 *
 */
@Service
public class LoadMessageJmsMdb implements MessageListener {
	
	private static final Logger LOGGER = Logger.getLogger(LoadMessageJmsMdb.class);

	private static final String UPDATE_QUEUE_NAME = "jms/UpdateMessageJmsQueue";
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private GenericService service;

	@Override
	public void onMessage(final Message message) {
		LOGGER.info("\tFila de recuperacao de agendamento...");
		try {
			Long appointmentId = (Long) jmsTemplate.getMessageConverter().fromMessage(message);
			
			// Recuperar objeto de agendamento
			Appointment appointment = service.getAppointmentById(appointmentId);

			if (appointment != null
					&& appointment.getProcessings() != null
					&& appointment.getProcessings().size() > 0) {
				
				this.printLastUpdateDate(appointment);
				
				Date now = new Date();
				
				appointment.setUpdateDate(now);
				
				for (Processing processing : appointment.getProcessings()) {
					processing.setUpdateDate(now);
				}
				LOGGER.info(" #### Total de mensagens = " + appointment.getProcessings().size());
				
				// Envia para fila de atualização
				jmsTemplate.convertAndSend(UPDATE_QUEUE_NAME, appointment);

			} else {
				LOGGER.error("\tNenhum agendamento foi recuperado com id = "
						.concat(String.valueOf(appointmentId)));
			}
			
		} catch (Exception ex) {
			LOGGER.error("\tErro recuperar agendamento/enviar para fila de atualizacao: "
					.concat(ex.getMessage()), ex);
		}
	}
	
	private void printLastUpdateDate(Appointment appointment) {
		LOGGER.info("====================================================================");
		LOGGER.info("\tUltima atualizacao em [  "
				.concat(sdf.format(appointment.getUpdateDate()))
				.concat("  ]"));
		LOGGER.info("====================================================================");
	}
}