package br.com.spring.schedule.jms.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.schedule.jms.dao.AppointmentDAO;
import br.com.spring.schedule.jms.domain.Appointment;

/**
 * 
 * @author Felipe
 *
 * Classe intermediária para executar regras de negócio e se comunicar
 * com a camada de dados
 * 
 */
@Service
public class GenericService {

	private static final Logger LOGGER = Logger.getLogger(GenericService.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private AppointmentDAO appointmentDao;
	
	public Appointment getAppointmentById(long appointmentId) {
		LOGGER.info("\tPreparando para recuperar agendamento por id: "
				.concat(String.valueOf(appointmentId)));
		
		String sql = " SELECT a FROM Appointment a JOIN FETCH a.processings WHERE a.id = :id ";
		
		Query query = em.createQuery(sql);

		/** Força a atualização do objeto em cache **/
		query.setHint("javax.persistence.cache.storeMode", "REFRESH");
		
		/***
		 * Define os relacionamentos para geração do JOIN sql e diminuir
		 * acesso à base de dados
		 ***/
		query.setHint("eclipselink.join-fetch", "a.company");
		query.setHint("eclipselink.left-join-fetch", "a.messageModel");
		query.setHint("eclipselink.left-join-fetch", "a.messageModel.company");
		query.setHint("eclipselink.left-join-fetch", "a.status");
		query.setHint("eclipselink.left-join-fetch", "a.user");
		query.setHint("eclipselink.left-join-fetch", "a.user.profile");
		query.setHint("eclipselink.left-join-fetch", "a.user.profile.company");
		query.setHint("eclipselink.left-join-fetch", "a.user.company");
		query.setHint("eclipselink.left-join-fetch", "a.processings.inChargeStatus");
		query.setHint("eclipselink.left-join-fetch", "a.processings.companyGroup");
		query.setHint("eclipselink.left-join-fetch", "a.processings.companyGroup.company");
		query.setHint("eclipselink.left-join-fetch", "a.processings.status");

		query.setParameter("id", appointmentId);
		
		Appointment appointment = null;
		
		try {
			appointment = (Appointment) query.getSingleResult();
		} catch (Exception ex) {
			LOGGER.error("\tErro ao recuperar agendamento: "
					.concat(ex.getMessage()), ex);			
		}
		
		return appointment;
	}
	
	public void updateAppointment(Appointment appointment) {
		LOGGER.info("\tPreparando para atualizar agendamento: "
				.concat(appointment.toString()));
		
		try {
			appointmentDao.update(appointment);
		} catch (Exception ex) {
			LOGGER.error("\tErro ao atualizar agendamento: "
					.concat(ex.getMessage()), ex);
		}
	}
}