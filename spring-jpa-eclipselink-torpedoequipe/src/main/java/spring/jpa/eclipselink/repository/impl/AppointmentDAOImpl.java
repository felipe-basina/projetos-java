package spring.jpa.eclipselink.repository.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.queries.CursoredStream;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import spring.jpa.eclipselink.domain.Appointment;
import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.domain.MessageModel;
import spring.jpa.eclipselink.repository.AppointmentDAO;
import spring.jpa.eclipselink.utils.ProcessStatusEnum;

/**
 * The Class AppointmentDAOImpl.
 */
@Repository("appointmentDAO")
public class AppointmentDAOImpl extends BaseDAOImpl<Appointment> implements
		AppointmentDAO {

	private boolean useJoin = true;
	
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
	public final List<Appointment> findAllAfterDateAndStatus(
			final ProcessStatusEnum... status) {

		Query query = this
				.getEntityManager()
				.createQuery(
						"select appointment from "
								.concat(getClazz().getSimpleName())
								.concat(" appointment where appointment.status.id IN :status ")
								.concat(" and (( appointment.endDateSendMessage = CURRENT_DATE and appointment.endTimeSendMessage <= FUNC('TO_CHAR', CURRENT_TIME, 'HH24')) ")
								.concat(" or appointment.endDateSendMessage < CURRENT_DATE)"));

		List<String> statusCode = new ArrayList<String>();
		for (ProcessStatusEnum processStatusEnum : status) {
			statusCode.add(processStatusEnum.getId());
		}

		query.setParameter("status", statusCode);

		if (TransactionSynchronizationManager.isActualTransactionActive()) {
			System.out.println("\n ##### Existe transacao!\n");
		} else {
			System.err.println("\n ##### NAO existe transacao!\n");
		}

		/*
		 * Para que o lock funcione é necessário que a operação (método) seja
		 * executada dentro de um contexto transacional
		 */
		query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
		return query.getResultList();
	}

	@Override
	public final Appointment findBeforeDateAndStatus(
			final ProcessStatusEnum status) {

		String join = " INNER JOIN SEMW_STATUS_PROCESSO SP ON SP.CD_STATUS_PROCESSO = AG.CD_STATUS_PROCESSO "
				.concat(" INNER JOIN SEMW_USUARIO U ON U.CD_USUARIO = AG.CD_USUARIO_AGENDOU ")
				.concat(" INNER JOIN SEMW_EMPRESA E ON E.CD_EMPRESA = U.CD_EMPRESA ")
				.concat(" INNER JOIN SEMW_PERFIL_ACESSO PA ON PA.CD_PERFIL_ACESSO = U.CD_PERFIL_ACESSO ")
				.concat(" INNER JOIN SEMW_EMPRESA EE ON EE.CD_EMPRESA = PA.CD_EMPRESA ")
				.concat(" INNER JOIN SEMW_CTL_PROCESSAMENTO_MSG MSG ON MSG.CD_AGENDA = AG.CD_AGENDA ")
				.concat(" INNER JOIN SEMW_STATUS_PROCESSO SPM ON SPM.CD_STATUS_PROCESSO = MSG.CD_STATUS_PROCESSO ")
				.concat(" LEFT OUTER JOIN SEMW_STATUS_PROCESSO SPT ON SPT.CD_STATUS_PROCESSO = MSG.IN_STATUS_TARIFACAO ");
		
		String sql = "SELECT * FROM SEMW_AGENDA AG :JOIN: "								
				.concat(" WHERE (AG.CD_STATUS_PROCESSO IN ?) ")
				.concat(" AND TO_CHAR(sysdate, 'yyyymmddhh24') >= (to_char(AG.DT_INICIAL_ENVIO_MENSAGEM, 'yyyymmdd') || trim(to_char(AG.HH_INICIAL_ENVIO_MENSAGEM, '00'))) ")
				.concat(" AND TO_CHAR(sysdate, 'yyyymmddhh24') < (to_char(AG.DT_FINAL_ENVIO_MENSAGEM, 'yyyymmdd') || trim(to_char(AG.HH_FINAL_ENVIO_MENSAGEM, '00'))) ")
				.concat(" AND ROWNUM <= 1 FOR UPDATE skip locked ");
		
		if (useJoin) {
			sql = sql.replace(":JOIN:", join);
		} else {
			sql = sql.replace(":JOIN:", "");
		}
		
		Query query = this.getEntityManager().createNativeQuery(sql, Appointment.class);
				
		query.setParameter(1, status.getId());

		@SuppressWarnings("unchecked")
		List<Appointment> appointments = query.getResultList();
		if (!appointments.isEmpty()) {
			return appointments.iterator().next();
		}
		return null;
	}

	@Override
	public final Long countByStartDateAndCompanyAndStatus(final Date startDate,
			final Long companyId, final ProcessStatusEnum... status) {
		TypedQuery<Long> query = this
				.getEntityManager()
				.createQuery(
						"select sum(app.totalMessages) from Appointment app where app.id IN ("
								.concat(" select appointment.id from Appointment appointment ")
								.concat(" where appointment.company.id = :company")
								.concat(" and appointment.startDateSendMessage between :startDate and :endDate")
								.concat(" and appointment.status.id IN :status )"),
						Long.class);

		query.setParameter("company", companyId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", Calendar.getInstance().getTime());

		List<String> statusCode = new ArrayList<String>();
		for (ProcessStatusEnum processStatusEnum : status) {
			statusCode.add(processStatusEnum.getId());
		}

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
			final CompanyGroup companyGroup, final ProcessStatusEnum status) {
		Query query = this
				.getEntityManager()
				.createQuery(
						"select distinct appointment from "
								.concat(getClazz().getSimpleName())
								.concat(" appointment JOIN FETCH appointment.processings as processings ")
								//.concat(" join appointment.processings AS processings ")
								.concat(" where processings.companyGroup.id = :companyGroup ")
								.concat(" and appointment.status.id = :status"));
		
		/** Define HINTS para diminuir acesso à base de dados **/
		if (useJoin) {
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.company");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.company");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.profile");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.profile.company");
			query.setHint("eclipselink.join-fetch", "appointment.processings.status");
			query.setHint("eclipselink.left-join-fetch", "appointment.processings.inChargeStatus"); /* Utilizando left-join */
			query.setHint("eclipselink.left-join-fetch", "appointment.processings.companyGroup");
			query.setHint("eclipselink.join-fetch", "appointment.company");
			query.setHint("eclipselink.join-fetch", "appointment.status");
			query.setHint("eclipselink.join-fetch", "appointment.user");
			query.setHint("eclipselink.join-fetch", "appointment.user.company");
			query.setHint("eclipselink.join-fetch", "appointment.user.profile");
			query.setHint("eclipselink.join-fetch", "appointment.user.profile.company");
		}
		
		query.setParameter("companyGroup", companyGroup.getId());
		query.setParameter("status", status.getId());
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<Appointment> findAllByMessageModelAndStatus(
			final MessageModel messageModel, final ProcessStatusEnum status) {
		Query query = this
				.getEntityManager()
				.createQuery(
						"select distinct appointment from "
								.concat(getClazz().getSimpleName())
								.concat(" appointment JOIN FETCH appointment.processings ")
								.concat(" where appointment.messageModel = :messageModel ")
								.concat(" and appointment.status.id = :status"));
		
		/** Define HINTS para diminuir acesso à base de dados **/
		if (useJoin) {
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.company");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.company");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.profile");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.profile.company");
			query.setHint("eclipselink.join-fetch", "appointment.processings.status");
			query.setHint("eclipselink.left-join-fetch", "appointment.processings.inChargeStatus"); /* Utilizando left-join */
			query.setHint("eclipselink.left-join-fetch", "appointment.processings.companyGroup");
			query.setHint("eclipselink.join-fetch", "appointment.company");
			query.setHint("eclipselink.join-fetch", "appointment.status");
			query.setHint("eclipselink.join-fetch", "appointment.user");
			query.setHint("eclipselink.join-fetch", "appointment.user.company");
			query.setHint("eclipselink.join-fetch", "appointment.user.profile");
			query.setHint("eclipselink.join-fetch", "appointment.user.profile.company");
		}
		
		query.setParameter("messageModel", messageModel);
		query.setParameter("status", status.getId());
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<Appointment> findAllByMsisdnAndStatus(
			final String msisdn, final ProcessStatusEnum status) {
		Query query = this
				.getEntityManager()
				.createQuery(
						"select distinct appointment from "
								.concat(getClazz().getSimpleName())
								.concat(" appointment where appointment.msisdnCreationUser = :msisdn ")
								.concat(" and appointment.status.id = :status"));
		
		// JOIN FETCH appointment.processings
				
		/** Define HINTS para diminuir acesso à base de dados **/
		if (useJoin) {
			query.setHint("eclipselink.left-join-fetch", "appointment.processings");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.company");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.company");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.profile");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.profile.company");
			query.setHint("eclipselink.join-fetch", "appointment.processings.status");
			query.setHint("eclipselink.left-join-fetch", "appointment.processings.inChargeStatus"); /* Utilizando left-join */
			query.setHint("eclipselink.left-join-fetch", "appointment.processings.companyGroup");
			query.setHint("eclipselink.join-fetch", "appointment.company");
			query.setHint("eclipselink.join-fetch", "appointment.status");
			query.setHint("eclipselink.join-fetch", "appointment.user");
			query.setHint("eclipselink.join-fetch", "appointment.user.company");
			query.setHint("eclipselink.join-fetch", "appointment.user.profile");
			query.setHint("eclipselink.join-fetch", "appointment.user.profile.company");
		}
		
		query.setParameter("msisdn", msisdn);
		query.setParameter("status", status.getId());
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
		sQuery.append(" appointment JOIN FETCH appointment.processings as processings ");
		//sQuery.append(" join appointment.processings AS processings ");
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
		
		/** Define HINTS para diminuir acesso à base de dados **/
		if (useJoin) {
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.company");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.company");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.profile");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.profile.company");
			query.setHint("eclipselink.join-fetch", "appointment.processings.status");
			query.setHint("eclipselink.left-join-fetch", "appointment.processings.inChargeStatus"); /* Utilizando left-join */
			query.setHint("eclipselink.left-join-fetch", "appointment.processings.companyGroup");
			query.setHint("eclipselink.join-fetch", "appointment.company");
			query.setHint("eclipselink.join-fetch", "appointment.status");
			query.setHint("eclipselink.join-fetch", "appointment.user");
			query.setHint("eclipselink.join-fetch", "appointment.user.company");
			query.setHint("eclipselink.join-fetch", "appointment.user.profile");
			query.setHint("eclipselink.join-fetch", "appointment.user.profile.company");
		}
		
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

	@Override
	@SuppressWarnings("unchecked")
	public List<Appointment> findAllByCompanyId(Long companyId) {
		Query query = this
				.getEntityManager()
				.createQuery(
						"select distinct appointment from "
								.concat(getClazz().getSimpleName())
								.concat(" appointment JOIN FETCH appointment.processings where appointment.company.id = :param "));
		
		/** Define HINTS para diminuir acesso à base de dados **/
		if (useJoin) {
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.company");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.company");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.profile");
			query.setHint("eclipselink.left-join-fetch", "appointment.messageModel.user.profile.company");
			query.setHint("eclipselink.join-fetch", "appointment.processings.status");
			query.setHint("eclipselink.left-join-fetch", "appointment.processings.inChargeStatus"); /* Utilizando left-join */
			query.setHint("eclipselink.left-join-fetch", "appointment.processings.companyGroup");
			query.setHint("eclipselink.join-fetch", "appointment.company");
			query.setHint("eclipselink.join-fetch", "appointment.status");
			query.setHint("eclipselink.join-fetch", "appointment.user");
			query.setHint("eclipselink.join-fetch", "appointment.user.company");
			query.setHint("eclipselink.join-fetch", "appointment.user.profile");
			query.setHint("eclipselink.join-fetch", "appointment.user.profile.company");
		}
		
		query.setParameter("param", companyId);
		return query.getResultList();
	}
}
