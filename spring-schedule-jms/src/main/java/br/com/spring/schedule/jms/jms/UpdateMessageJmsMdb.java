package br.com.spring.schedule.jms.jms;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import br.com.spring.schedule.jms.domain.Appointment;
import br.com.spring.schedule.jms.service.GenericService;

/**
 * 
 * @author Felipe
 *
 * Classe listener da fila jms/UpdateMessageJmsQueue definida no arquivo
 * spring-jms.xml
 *
 */
@Service
public class UpdateMessageJmsMdb implements MessageListener {
	
	private static final Logger LOGGER = Logger.getLogger(UpdateMessageJmsMdb.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private GenericService service;
	
	@Override
	public void onMessage(final Message message) {
		LOGGER.info("\tFila de atualizacao de agendamento...");
		try {
			Appointment appointment = (Appointment) jmsTemplate.getMessageConverter().fromMessage(message);
			LOGGER.info("\tObjeto recebido: " + appointment);
			
			// Atualiza o registro na base de dados
			service.updateAppointment(appointment);
		} catch (Exception ex) {
			LOGGER.error("\t\tErro ao enviar objeto para atualizacao: "
					.concat(ex.getMessage()), ex);
		}
	}
}