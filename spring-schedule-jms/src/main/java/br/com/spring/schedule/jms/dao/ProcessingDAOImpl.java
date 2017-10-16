package br.com.spring.schedule.jms.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.spring.schedule.jms.domain.CompanyGroup;
import br.com.spring.schedule.jms.domain.Processing;

/**
 * The Class ProcessingDAOImpl.
 */
@Repository("processingDAO")
public class ProcessingDAOImpl extends BaseDAOImpl<Processing> implements
		ProcessingDAO {

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
		Query query = getEntityManager().createQuery(
				"select p from " + getClazz().getSimpleName()
						+ " p where p.companyGroup=:companyGroup");
		query.setParameter("companyGroup", companyGroup);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<Processing> findByCdSendSmsTransaction(
			final Long cdSendSmsTransaction) {
		Query query = getEntityManager()
				.createQuery(
						"SELECT p FROM "
								+ getClazz().getSimpleName()
								+ " p WHERE p.cdSendSmsTransaction = :transactionSendSMS ");
		query.setParameter("transactionSendSMS", cdSendSmsTransaction);
		return query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<Processing> findByCdSendSmsTransactionAndMsisdn(
			final Long cdSendSmsTransaction, final String msisdn) {
		Query query = getEntityManager()
				.createQuery(
						"SELECT p FROM "
								+ getClazz().getSimpleName()
								+ " p WHERE p.cdSendSmsTransaction = :transactionSendSMS "
								+ " AND p.msisdn=:msisdn");
		query.setParameter("transactionSendSMS", cdSendSmsTransaction);
		query.setParameter("msisdn", msisdn);
		return query.getResultList();
	}
}
