package spring.jpa.eclipselink.repository.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.domain.Processing;
import spring.jpa.eclipselink.repository.ProcessingDAO;

/**
 * The Class ProcessingDAOImpl.
 */
@Repository("processingDAO")
public class ProcessingDAOImpl extends BaseDAOImpl<Processing> implements
		ProcessingDAO {

	private boolean useJoin = true;
	
	/**
	 * Instantiates a new processing dao impl.
	 */
	public ProcessingDAOImpl() {
		super(Processing.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final List<Processing> findByCompanyGroup(
			final CompanyGroup companyGroup) {
		
		StringBuilder consulta = new StringBuilder(" select p from " + getClazz().getSimpleName());
		consulta.append(" p ");
		consulta.append(" JOIN FETCH p.appointment ");
		consulta.append(" where p.companyGroup = :companyGroup ");
		
		Query query = getEntityManager().createQuery(consulta.toString());
				
		/** Define HINTS para diminuir acesso à base de dados **/
		if (useJoin) {
			query.setHint("eclipselink.join-fetch", "p.appointment.company");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.company");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.user");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.user.company");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.user.profile");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.user.profile.company");
			query.setHint("eclipselink.join-fetch", "p.appointment.status");
			query.setHint("eclipselink.join-fetch", "p.appointment.user");
			query.setHint("eclipselink.join-fetch", "p.appointment.user.company");
			query.setHint("eclipselink.join-fetch", "p.appointment.user.profile");
			query.setHint("eclipselink.join-fetch", "p.appointment.user.profile.company");
			query.setHint("eclipselink.join-fetch", "p.status");
			query.setHint("eclipselink.left-join-fetch", "p.inChargeStatus");
			query.setHint("eclipselink.left-join-fetch", "p.companyGroup");
		}
		
		query.setParameter("companyGroup", companyGroup);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<Processing> findByCdSendSmsTransaction(
			final Long cdSendSmsTransaction) {
		
		StringBuilder consulta = new StringBuilder(" SELECT p FROM ".concat(getClazz().getSimpleName()));
		consulta.append(" p JOIN FETCH p.appointment ");
		consulta.append(" WHERE p.cdSendSmsTransaction = :transactionSendSMS ");
		
		Query query = getEntityManager().createQuery(consulta.toString());

		/** Define HINTS para diminuir acesso à base de dados **/
		if (useJoin) {
			query.setHint("eclipselink.join-fetch", "p.appointment.company");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.company");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.user");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.user.company");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.user.profile");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.user.profile.company");
			query.setHint("eclipselink.join-fetch", "p.appointment.status");
			query.setHint("eclipselink.join-fetch", "p.appointment.user");
			query.setHint("eclipselink.join-fetch", "p.appointment.user.company");
			query.setHint("eclipselink.join-fetch", "p.appointment.user.profile");
			query.setHint("eclipselink.join-fetch", "p.appointment.user.profile.company");
			query.setHint("eclipselink.join-fetch", "p.status");
			query.setHint("eclipselink.left-join-fetch", "p.inChargeStatus");
			query.setHint("eclipselink.left-join-fetch", "p.companyGroup");
		}
		
		query.setParameter("transactionSendSMS", cdSendSmsTransaction);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<Processing> findByCdSendSmsTransactionAndMsisdn(
			final Long cdSendSmsTransaction, final String msisdn) {
		
		StringBuilder consulta = new StringBuilder(" SELECT p FROM ".concat(getClazz().getSimpleName()));
		consulta.append(" p JOIN FETCH p.appointment ");
		consulta.append(" WHERE p.cdSendSmsTransaction = :transactionSendSMS ");
		consulta.append(" AND p.msisdn=:msisdn ");
		
		Query query = getEntityManager().createQuery(consulta.toString());

		/** Define HINTS para diminuir acesso à base de dados **/
		if (useJoin) {
			query.setHint("eclipselink.join-fetch", "p.appointment.company");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.company");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.user");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.user.company");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.user.profile");
			query.setHint("eclipselink.left-join-fetch", "p.appointment.messageModel.user.profile.company");
			query.setHint("eclipselink.join-fetch", "p.appointment.status");
			query.setHint("eclipselink.join-fetch", "p.appointment.user");
			query.setHint("eclipselink.join-fetch", "p.appointment.user.company");
			query.setHint("eclipselink.join-fetch", "p.appointment.user.profile");
			query.setHint("eclipselink.join-fetch", "p.appointment.user.profile.company");
			query.setHint("eclipselink.join-fetch", "p.status");
			query.setHint("eclipselink.left-join-fetch", "p.inChargeStatus");
			query.setHint("eclipselink.left-join-fetch", "p.companyGroup");
		}

		query.setParameter("transactionSendSMS", cdSendSmsTransaction);
		query.setParameter("msisdn", msisdn);
		return query.getResultList();
	}
}