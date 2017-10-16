package br.com.spring.schedule.jms.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.queries.CursoredStream;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import br.com.spring.schedule.jms.domain.Appointment;
import br.com.spring.schedule.jms.domain.CompanyGroup;
import br.com.spring.schedule.jms.domain.MessageModel;

/**
 * The Class AppointmentDAOImpl.
 */
@Repository("appointmentDAO")
public class AppointmentDAOImpl extends BaseDAOImpl<Appointment> implements
		AppointmentDAO {

	/**
	 * Instantiates a new appointment dao impl.
	 */
	public AppointmentDAOImpl() {
		super(Appointment.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	// Garante, sempre, que o método será executado dentro de um contexto
	// transacional
	public final List<Appointment> findAllAfterDateAndStatus() {

		Query query = this
				.getEntityManager()
				.createQuery(
						"select appointment from "
								.concat(getClazz().getSimpleName())
								.concat(" appointment where appointment.status.id IN :status ")
								.concat(" and (( appointment.endDateSendMessage = CURRENT_DATE and appointment.endTimeSendMessage <= FUNC('TO_CHAR', CURRENT_TIME, 'HH24')) ")
								.concat(" or appointment.endDateSendMessage < CURRENT_DATE)"));

		List<String> statusCode = new ArrayList<String>();
		query.setParameter("status", statusCode);

		if (TransactionSynchronizationManager.isActualTransactionActive()) {
			System.out.println("\n ##### Existe transacao!\n");
		} else {
			System.err.println("\n ##### NAO existe transacao!\n");
		}

		return query.getResultList();
	}

	@Override
	public final Appointment findBeforeDateAndStatus() {

		Query query = this
				.getEntityManager()
				.createNativeQuery(
						"SELECT * FROM SEMW_AGENDA "
								.concat(" WHERE (CD_STATUS_PROCESSO IN ?) ")
								.concat(" AND TO_CHAR(sysdate, 'yyyymmddhh24') >= (to_char(DT_INICIAL_ENVIO_MENSAGEM, 'yyyymmdd') || trim(to_char(HH_INICIAL_ENVIO_MENSAGEM, '00'))) ")
								.concat(" AND TO_CHAR(sysdate, 'yyyymmddhh24') < (to_char(DT_FINAL_ENVIO_MENSAGEM, 'yyyymmdd') || trim(to_char(HH_FINAL_ENVIO_MENSAGEM, '00'))) ")
								.concat(" AND ROWNUM <= 1 FOR UPDATE skip locked "),
						Appointment.class);

		@SuppressWarnings("unchecked")
		List<Appointment> appointments = query.getResultList();
		if (!appointments.isEmpty()) {
			return appointments.iterator().next();
		}
		return null;
	}

	@Override
	public final Long countByStartDateAndCompanyAndStatus(final Date startDate,
			final Long companyId) {
		@SuppressWarnings("unchecked")
		TypedQuery<Long> query = (TypedQuery<Long>) this
				.getEntityManager()
				.createQuery(
						"select sum(app.totalMessages) from Appointment app where app.id IN ("
								.concat(" select appointment.id from Appointment appointment ")
								.concat(" where appointment.company.id = :company")
								.concat(" and appointment.startDateSendMessage between :startDate and :endDate")
								.concat(" and appointment.status.id IN :status )"));

		query.setParameter("company", companyId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", Calendar.getInstance().getTime());

		List<String> statusCode = new ArrayList<String>();
		query.setParameter("status", statusCode);

		Long count = query.getSingleResult();
		if (count == null) {
			return 0L;
		} else {
			return count;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<Appointment> findAllByCompanyGroupAndStatus(
			final CompanyGroup companyGroup) {
		Query query = this
				.getEntityManager()
				.createQuery(
						"select distinct appointment from "
								.concat(getClazz().getSimpleName())
								.concat(" appointment JOIN FETCH appointment.processings ")
								.concat(" join appointment.processings AS processings ")
								.concat(" where processings.companyGroup.id = :companyGroup ")
								.concat(" and appointment.status.id = :status"));
		query.setParameter("companyGroup", companyGroup.getId());
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<Appointment> findAllByMessageModelAndStatus(
			final MessageModel messageModel) {
		Query query = this
				.getEntityManager()
				.createQuery(
						"select distinct appointment from "
								.concat(getClazz().getSimpleName())
								.concat(" appointment  ")
								.concat(" where appointment.messageModel = :messageModel ")
								.concat(" and appointment.status.id = :status"));
		query.setParameter("messageModel", messageModel);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<Appointment> findAllByMsisdnAndStatus(
			final String msisdn) {
		Query query = this
				.getEntityManager()
				.createQuery(
						"select distinct appointment from "
								.concat(getClazz().getSimpleName())
								.concat(" appointment where appointment.msisdnCreationUser = :msisdn ")
								.concat(" and appointment.status.id = :status"));
		query.setParameter("msisdn", msisdn);
		return query.getResultList();
	}

	@Override
	public final List<Appointment> getReportData(final Date startDate,
			final Date endDate, final String status,
			final List<String> appointmentId, final Long userId,
			final Long companyId, final int pageSize) {
		StringBuilder sQuery = new StringBuilder(
				"select distinct appointment from ");
		sQuery.append(getClazz().getSimpleName());
		sQuery.append(" appointment JOIN FETCH appointment.processings ");
		sQuery.append(" join appointment.processings AS processings ");
		sQuery.append(" where 1 = 1 ");

		if (startDate != null) {
			sQuery.append(" and appointment.startDateSendMessage >= :startDate");
		}

		if (endDate != null) {
			sQuery.append(" and appointment.endDateSendMessage <= :endDate");
		}

		if (status != null && !status.isEmpty()) {
			sQuery.append(" and appointment.status.id = :status");
		}

		if (appointmentId != null && !appointmentId.isEmpty()) {
			sQuery.append(" and appointment.id IN :appointmentId");
		}

		if (companyId != null) {
			sQuery.append(" and appointment.company.id = :companyId ");
		}

		if (userId != null) {
			sQuery.append(" and appointment.user.id = :userId ");
		}

		Query query = getEntityManager().createQuery(sQuery.toString());

		if (startDate != null) {
			query.setParameter("startDate", startDate);
		}

		if (endDate != null) {
			query.setParameter("endDate", endDate);
		}

		if (status != null && !status.isEmpty()) {
			query.setParameter("status", status);
		}

		if (appointmentId != null && !appointmentId.isEmpty()) {
			query.setParameter("appointmentId", appointmentId);
		}

		if (companyId != null) {
			query.setParameter("companyId", companyId);
		}

		if (userId != null) {
			query.setParameter("userId", userId);
		}

		query.setHint("eclipselink.cursor", true);
		query.setHint("eclipselink.cursor.page-size", pageSize);
		CursoredStream stream = (CursoredStream) query.getSingleResult();
		List<Appointment> results = new ArrayList<Appointment>();

		while (!stream.atEnd()) {
			Appointment appointment = (Appointment) stream.read();
			results.add(appointment);
			stream.releasePrevious();
		}
		stream.close();

		return results;
	}

}
