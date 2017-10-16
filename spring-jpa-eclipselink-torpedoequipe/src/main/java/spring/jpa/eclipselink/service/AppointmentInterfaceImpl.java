package spring.jpa.eclipselink.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import spring.jpa.eclipselink.domain.Appointment;
import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.domain.MessageModel;
import spring.jpa.eclipselink.domain.Report;
import spring.jpa.eclipselink.domain.User;
import spring.jpa.eclipselink.repository.AppointmentDAO;
import spring.jpa.eclipselink.utils.AppointmentData;
import spring.jpa.eclipselink.utils.ProcessStatusEnum;

@Service
public class AppointmentInterfaceImpl implements AppointmentInterface {

	@Autowired
	private AppointmentDAO apDao;

	@Autowired
	private AppointmentData apData;
	
	@Override
	public List<Appointment> recuperarAgendasFinalizadas() {
		return apDao
				.findAllAfterDateAndStatus(ProcessStatusEnum.PROCESSADO);
	}

	@Override
	public Appointment recuperarAntesDataEStatus(ProcessStatusEnum status) {
		return apDao.findBeforeDateAndStatus(status);
	}
	
	@Override
	public Long contabilizarPorDataInicioEEmpresaEStatus(Date startDate,
			Long companyId, ProcessStatusEnum... status) {
		return apDao.countByStartDateAndCompanyAndStatus(startDate, companyId, status);
	}
	
	@Override
	public List<Appointment> recuperarAgendasPorGrupoEmpresaEStatus(
			CompanyGroup companyGroup, ProcessStatusEnum status) {
		return apDao.findAllByCompanyGroupAndStatus(companyGroup, status);
	}

	@Override
	public List<Appointment> recuperarAgendasPorMensagemModeloEStatus(
			MessageModel messageModel, ProcessStatusEnum status) {
		return apDao.findAllByMessageModelAndStatus(messageModel, status);
	}
	
	@Override
	public List<Appointment> recuperarAgendasPorMsisdnEStatus(String msisdn,
			ProcessStatusEnum status) {
		return apDao.findAllByMsisdnAndStatus(msisdn, status);
	}
	
	@Override
	public List<Appointment> recuperarDadosRelatorios(Date startDate,
			Date endDate, String status, List<String> appointmentId,
			Long userId, Long companyId, int pageSize) {
		return apDao.getReportData(startDate, endDate, status, appointmentId, userId, companyId, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Appointment> recuperarAgendasFinalizadasJoinFetch() {

		String sql = " select distinct a from Appointment a JOIN FETCH a.processings "
				.concat(" WHERE a.endDateSendMessage = CURRENT_DATE ")
				.concat(" or (a.endTimeSendMessage <= FUNC('TO_CHAR', CURRENT_TIME, 'HH24') ")
				.concat(" or a.endDateSendMessage < CURRENT_DATE) ")
				.concat(" ORDER BY a.id DESC ");

		Query consulta = apDao.getEntityManager().createQuery(sql);
		consulta.setHint("eclipselink.join-fetch", "a.processings.status");
		consulta.setHint("eclipselink.left-join-fetch", "a.processings.inChargeStatus"); /* Utilizando left-join */
		//consulta.setHint("eclipselink.join-fetch", "a.processings.inChargeStatus"); /* Sem a utilização do left-join */
		consulta.setHint("eclipselink.left-join-fetch", "a.processings.companyGroup"); /* Utilizando left-join */
		consulta.setHint("eclipselink.join-fetch", "a.company");
		consulta.setHint("eclipselink.join-fetch", "a.status");
		consulta.setHint("eclipselink.join-fetch", "a.user");
		consulta.setHint("eclipselink.join-fetch", "a.user.company");
		consulta.setHint("eclipselink.join-fetch", "a.user.profile");
		consulta.setHint("eclipselink.join-fetch", "a.user.profile.company");

		List<Appointment> lista = null;
		try {
			lista = consulta.getResultList();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Appointment> recuperarAgendasFinalizadasJoinBatch() {

		String sql = " select distinct a from Appointment a JOIN FETCH a.processings ";

		Query consulta = apDao.getEntityManager().createQuery(sql);
		consulta.setHint("eclipselink.batch", "a.processings.status");
		// consulta.setHint("eclipselink.left-batch", "a.processings.inChargeStatus"); /* Utilizando left-batch */
		consulta.setHint("eclipselink.batch", "a.processings.inChargeStatus"); /* Sem a utilização do left-batch */
		consulta.setHint("eclipselink.batch", "a.company");
		consulta.setHint("eclipselink.batch", "a.status");
		consulta.setHint("eclipselink.batch", "a.user");
		consulta.setHint("eclipselink.batch", "a.user.company");
		consulta.setHint("eclipselink.batch", "a.user.profile");
		consulta.setHint("eclipselink.batch", "a.user.profile.company");

		List<Appointment> lista = null;
		try {
			lista = consulta.getResultList();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Report> recuperarTotalMensagensPorCliente() {

		String sql = " select new spring.jpa.eclipselink.domain.Report(a.company.id, c.companyName, a.creationDate, a.id, count(p.appointment.id)) "
				.concat(" from Processing p JOIN p.appointment AS a JOIN a.company AS c ")
				.concat(" GROUP BY a.company.id, c.companyName, a.creationDate, p.appointment.id ");

		Query consulta = apDao.getEntityManager().createQuery(sql);

		List<Report> lista = null;
		try {
			lista = consulta.getResultList();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Report> recuperarTotalMensagensPorClienteEStatus(String statusId) {

		String sql = " select new spring.jpa.eclipselink.domain.Report(a.company.id, c.companyName, a.creationDate, a.id, count(p.appointment.id)) "
				.concat(" from Processing p JOIN p.appointment AS a JOIN a.company AS c ")
				.concat(" WHERE 1 = 1 ");

		if (statusId != null) {
			sql = sql.concat(" AND p.status.id = :id");
		}

		sql = sql
				.concat(" GROUP BY a.company.id, c.companyName, a.creationDate, p.appointment.id ");

		Query consulta = apDao.getEntityManager().createQuery(sql);

		if (statusId != null) {
			consulta.setParameter("id", statusId);
		}

		List<Report> lista = null;
		try {
			lista = consulta.getResultList();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Report> recuperarTotalMensagensPorStatus(String statusId) {

		String sql = " select new spring.jpa.eclipselink.domain.Report(a.company.id, c.companyName, a.creationDate, a.id, count(p.status.id), p.status.statusDescription) "
				.concat(" from Processing p JOIN p.appointment AS a JOIN a.company AS c ")
				.concat(" WHERE 2 = 2 ");

		if (statusId != null) {
			sql = sql.concat(" AND p.status.id = :id ");
		}

		sql = sql
				.concat(" GROUP BY a.company.id, c.companyName, a.creationDate, p.appointment.id, p.status.id, p.status.statusDescription ")
				.concat(" ORDER BY a.id * 1 asc ");

		Query consulta = apDao.getEntityManager().createQuery(sql);

		if (statusId != null) {
			consulta.setParameter("id", statusId);
		}

		List<Report> lista = null;
		try {
			lista = consulta.getResultList();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> recuperarTotalMensagensPorStatusMesAno(String statusId) {

		String sql = " SELECT																											"
				.concat("   A.CD_EMPRESA AS CODIGO_EMPRESA,                                                                              ")
				.concat("   E.NO_EMPRESA AS NOME_EMPRESA,                                                                                ")
				.concat("   EXTRACT(MONTH FROM A.DT_CRIACAO) || '/' || EXTRACT(YEAR FROM A.DT_CRIACAO) AS PERIODO_REGISTRO,              ")
				.concat("   COUNT(CTL.CD_AGENDA) AS TOTAL_MENSAGENS                                                                      ")
				.concat(" FROM                                                                                                           ")
				.concat("   semw_o.SEMW_EMPRESA E,                                                                                       ")
				.concat("   semw_o.SEMW_CTL_PROCESSAMENTO_MSG CTL,                                                                       ")
				.concat("   semw_o.SEMW_AGENDA A                                                                                         ")
				.concat(" WHERE                                                                                                          ")
				.concat("   A.CD_AGENDA = CTL.CD_AGENDA                                                                                  ")
				.concat(" AND                                                                                                            ")
				.concat("   A.CD_EMPRESA = E.CD_EMPRESA                                                                                  ");

		if (statusId != null) {
			sql += " AND CTL.CD_STATUS_PROCESSO = ".concat(statusId);
		}

		sql += " GROUP BY A.CD_EMPRESA, E.NO_EMPRESA, EXTRACT(MONTH FROM A.DT_CRIACAO) || '/' || EXTRACT(YEAR FROM A.DT_CRIACAO) "
				.concat(" ORDER BY                                                                                                  ")
				.concat(" TO_DATE(EXTRACT(MONTH FROM A.DT_CRIACAO) || '/' || EXTRACT(YEAR FROM A.DT_CRIACAO), 'MM/YYYY') desc       ");

		Query consulta = apDao.getEntityManager().createNativeQuery(sql.trim());

		List<Object[]> lista = null;
		try {
			lista = (Vector<Object[]>) consulta.getResultList();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		return lista;
	}

	@Override
	public String recuperarExtract() {

		String sql = " select EXTRACT(MONTH FROM sysdate) || '/' || EXTRACT(YEAR FROM sysdate) AS PERIODO_REGISTRO "
				.concat(" from dual ");

		Query consulta = apDao.getEntityManager().createNativeQuery(sql.trim());

		if (TransactionSynchronizationManager.isActualTransactionActive()) {
			System.out.println("\n ##### Existe transacao!\n");
		} else {
			System.err.println("\n ##### NAO existe transacao!\n");
		}

		String retorno = null;
		try {
			retorno = (String) consulta.getSingleResult();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		return retorno;
	}
	
	@Override
	public void generateAppointment(int totalAppointment, User user) {
		apData.createAppointment(totalAppointment, user);
	}
	
	@Override
	public void deleteAppointments(String creationUser) throws Exception {
		apData.deleteAppointmentByCreationUser(creationUser);
	}
	
	@Override
	public List<Appointment> recuperarAgendasPorIdEmpresa(Long id) {
		return apDao.findAllByCompanyId(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Appointment> recuperarAgendasPaginacao(int paginaInicial, int totalRegistros) {

		List<Appointment> lista = new ArrayList<Appointment>();
		
		try {
			
			final EntityManager em = this.apDao.getEntityManager();
			
			String sql = " SELECT * FROM SEMW_AGENDA a "
					+ " INNER JOIN SEMW_EMPRESA e ON e.CD_EMPRESA = a.CD_EMPRESA "
					+ " INNER JOIN SEMW_STATUS_PROCESSO sp ON sp.CD_STATUS_PROCESSO = a.CD_STATUS_PROCESSO "
					+ " INNER JOIN SEMW_USUARIO u ON u.CD_USUARIO = a.CD_USUARIO_AGENDOU "
					+ " INNER JOIN SEMW_EMPRESA ee ON ee.CD_EMPRESA = u.CD_EMPRESA "
					+ " INNER JOIN SEMW_PERFIL_ACESSO pa ON pa.CD_PERFIL_ACESSO = u.CD_PERFIL_ACESSO "
					+ " INNER JOIN SEMW_EMPRESA eee ON eee.CD_EMPRESA = pa.CD_EMPRESA "
					+ " ORDER BY a.CD_AGENDA DESC ";
			
			Query consulta = em.createNativeQuery(sql, Appointment.class);
			
			/** Define hints para forçar o join **/
			consulta.setHint("eclipselink.join-fetch", "a.company");
			consulta.setHint("eclipselink.join-fetch", "a.status");
			consulta.setHint("eclipselink.join-fetch", "a.user");
			consulta.setHint("eclipselink.join-fetch", "a.user.company");
			consulta.setHint("eclipselink.join-fetch", "a.user.profile");
			consulta.setHint("eclipselink.join-fetch", "a.user.profile.company");
			
			if (paginaInicial <= 0) {
				paginaInicial = 1;
			}
			
			/** Define valores para paginação **/
			consulta.setFirstResult((paginaInicial - 1) * totalRegistros);
			consulta.setMaxResults(totalRegistros);
			
			lista = consulta.getResultList();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Appointment> recuperarMensagensAgenda(final List<Long> ids) {
		
		List<Appointment> lista = new ArrayList<Appointment>();
		
		try {
			
			String sql = " select distinct a from Appointment a JOIN FETCH a.processings "
					.concat(" WHERE a.endDateSendMessage = CURRENT_DATE ")
					.concat(" or (a.endTimeSendMessage <= FUNC('TO_CHAR', CURRENT_TIME, 'HH24') ")
					.concat(" or a.endDateSendMessage < CURRENT_DATE) ")
					.concat(" AND a.id IN :ids ")
					.concat(" ORDER BY a.id DESC ");

			Query consulta = apDao.getEntityManager().createQuery(sql);
			consulta.setHint("eclipselink.join-fetch", "a.processings.status");
			consulta.setHint("eclipselink.left-join-fetch", "a.processings.inChargeStatus"); /* Utilizando left-join */
			consulta.setHint("eclipselink.left-join-fetch", "a.processings.companyGroup"); /* Utilizando left-join */
			consulta.setHint("eclipselink.join-fetch", "a.company");
			consulta.setHint("eclipselink.join-fetch", "a.status");
			consulta.setHint("eclipselink.join-fetch", "a.user");
			consulta.setHint("eclipselink.join-fetch", "a.user.company");
			consulta.setHint("eclipselink.join-fetch", "a.user.profile");
			consulta.setHint("eclipselink.join-fetch", "a.user.profile.company");
			
			/** Define o parâmetro **/
			consulta.setParameter("ids", ids);
			
			lista = (List<Appointment>) consulta.getResultList();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return lista;
	}
}