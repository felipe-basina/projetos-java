package spring.jpa.eclipselink.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.jpa.eclipselink.domain.Appointment;
import spring.jpa.eclipselink.domain.Processing;
import spring.jpa.eclipselink.domain.Status;
import spring.jpa.eclipselink.domain.User;
import spring.jpa.eclipselink.repository.AppointmentDAO;

@Service
public class AppointmentData {

	@Autowired
	private AppointmentDAO aRepository;
	
	private static final String DEFAULT_CTN = "11991111111";
	
	private static final String DEFAULT_MESSAGE = "Uma mensagem padrão para envio de SMS";
	
	private static List<Integer> chargeValidStatus = new ArrayList<Integer>();
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAppointmentByCreationUser(String creationUser) throws Exception {
		
		if (creationUser == null || "".equals(creationUser.trim())) {
			throw new IllegalArgumentException(" # creationUser deve ser definido ");
		}
		
		EntityManager em = aRepository.getEntityManager();
		
		String sql = " DELETE FROM Appointment a WHERE a.msisdnCreationUser = :creationUser ";
		
		Query delete = em.createQuery(sql);
		delete.setParameter("creationUser", creationUser);
		
		try {
			
			// Mensagens
			this.deleteProcessing(creationUser);
			
			// Agenda
			delete.executeUpdate();
						
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}
	
	private void deleteProcessing(String creationUser) {
		
		HashSet<Long> ids = this.getAppointmentIds(creationUser);
		
		if (ids != null && ids.size() > 0) {
		
			EntityManager em = aRepository.getEntityManager();

			String sql = " DELETE FROM Processing p WHERE p.appointment.id in :ids ";
			
			Query delete = em.createQuery(sql);
			delete.setParameter("ids", ids);
			delete.executeUpdate();
		}
	}
	
	@SuppressWarnings("unchecked")
	private HashSet<Long> getAppointmentIds(String creationUser) {

		String sql = " SELECT distinct a.id FROM Appointment a WHERE a.msisdnCreationUser = :creationUser ";
		
		EntityManager em = aRepository.getEntityManager();
		
		Query consulta = em.createQuery(sql);
		consulta.setParameter("creationUser", creationUser);		
		
		List<Long> lista = null;
		try {
			
			lista = (Vector<Long>) consulta.getResultList();
			
			return new HashSet<Long>(lista);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public void createAppointment(Integer totalAppointment, User appointmentCreationUser) throws IllegalArgumentException {
		
		if (appointmentCreationUser == null) {
			throw new IllegalArgumentException(" # Appointment creation user deve ser definido");
		}
		
		if (totalAppointment == null || totalAppointment <= 0) {
			totalAppointment = 10;
		}
		
		for (int appCount = 0; appCount < totalAppointment; appCount++) {
			
			Date now = new Date();
			
			// Define a agenda
			Appointment appointment = new Appointment();
			appointment.setCreationDate(this.setInitDate());
			appointment.setUpdateDate(now);
			appointment.setMsisdnCreationUser(DEFAULT_CTN);
			appointment.setMsisdnUpdateUser(DEFAULT_CTN);
			appointment.setStatus(this.setAppointmentStatus());
			appointment.setDtStatusProcesso(now);
			appointment.setUser(appointmentCreationUser);
			appointment.setCompany(appointmentCreationUser.getCompany());
			appointment.setStartTimeSendMessage(8L);
			appointment.setEndTimeSendMessage(12L);
			
			Date initDate = this.setInitDate();
			Date endDate = this.setEndDate(initDate);
			
			appointment.setStartDateSendMessage(initDate);
			appointment.setEndDateSendMessage(endDate);
			
			appointment.setTxMessage(DEFAULT_MESSAGE);
			appointment.setTotalMessages(Long.parseLong(String.valueOf(totalAppointment)));
			appointment.setProcessings(new ArrayList<Processing>());
			
			Random aleatorio = new Random();
			int totalProcesscing = aleatorio.nextInt(totalAppointment);
			++totalProcesscing;
			
			// Define mensangens
			for (int procCount = 0; procCount < totalProcesscing; procCount++) {
				Processing processing = new Processing();
				processing.setCreationDate(now);
				processing.setStatus(this.setProcessingStatus());
				processing.setProcessStatusDate(now);
				processing.setMsisdn("119988776" + procCount);
				processing.setUpdateDate(now);
				processing.setSmsSendDate(now);
				processing.setCdSendSmsTransaction(System.currentTimeMillis());
				
				if (procCount % 2 == 0) {
					processing.setInChargeStatus(this.setProcessingInStatusCharge());
					processing.setCdChargeTransaction(System.currentTimeMillis());
				}
				
				processing.setAppointment(appointment);
				appointment.getProcessings().add(processing);
			}
			
			try {
				// Salva a agenda
				aRepository.save(appointment);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	/** 1	Agendado
		2	Processando
		3	Processado
	 */
	private Status setAppointmentStatus() {
		
		Random aleatorio = new Random();
		int indice = aleatorio.nextInt(4);
		
		if (indice == 0) {
			indice++;
		}
		
		Status status = new Status();
		status.setId(String.valueOf(indice));
		
		return status;
	}
	
	/** 1	Agendado
		2	Processando
		3	Processado
		4	Erro MSISDN Inválido
		5	Erro Processamento Publicidade
		6	Erro processamento
		7	Erro Envio Menssagem
		8	Não Processado
		9	Aguardando Recibo
		10	Cancelado
		11	Erro Tarifação
		12	Mensagem Tarifada
	 */
	private Status setProcessingStatus() {

		Random aleatorio = new Random();
		int indice = aleatorio.nextInt(13);
		
		if (indice == 0) {
			indice++;
		}
		
		Status status = new Status();
		status.setId(String.valueOf(indice));
		
		return status;
	}
	
	/** 13	Mensagem Entregue ao Móvel com Sucesso
		14	Tempo de Entrega da Mensagem Expirou
		15	SMSC Falhou ao Entregar a Mensagem ao Móvel
		16	Mensagem Não Entregue por Motivos Desconhecidos
		17	Mensagem Rejeitada pela SMSC
	 */
	private Status setProcessingInStatusCharge() {
		
		Random aleatorio = new Random();
		int indice = aleatorio.nextInt(6);
		
		if (indice == 0) {
			indice++;
		}
		
		this.setChargeValidStatus();
		
		Status status = new Status();
		status.setId(String.valueOf(chargeValidStatus.get(indice - 1)));
		
		return status;
	}
	
	private void setChargeValidStatus() {
		if (chargeValidStatus.size() <= 0) {
			chargeValidStatus.add(13);
			chargeValidStatus.add(14);
			chargeValidStatus.add(15);
			chargeValidStatus.add(16);
			chargeValidStatus.add(17);			
		}
	}
	
	private Date setInitDate() {
		
		Random aleatorio = new Random();
		int indice = aleatorio.nextInt(13);
		
		if (indice == 0) {
			indice++;
		}
		
		Calendar calendarObj = Calendar.getInstance();
		calendarObj.add(Calendar.MONTH, -indice);
		
		return calendarObj.getTime();
	}
	
	private Date setEndDate(Date initDate) {
		
		Calendar calendarObj = Calendar.getInstance();
		calendarObj.setTime(initDate);
		
		Integer mes = calendarObj.get(Calendar.MONTH);
		
		Calendar newCalendarObj = Calendar.getInstance();
		newCalendarObj.add(Calendar.MONTH, mes);
		
		return newCalendarObj.getTime();
	}
}