package br.com.spring.schedule.jms.dao;

import java.util.List;

import br.com.spring.schedule.jms.domain.CompanyGroup;
import br.com.spring.schedule.jms.domain.Processing;

/**
 * The Interface ProcessingDAO.
 */
public interface ProcessingDAO extends BaseDAO<Processing> {

	/**
	 * Find by company group.
	 *
	 * @param companyGroup
	 *            the company group
	 * @return the list
	 */
	List<Processing> findByCompanyGroup(CompanyGroup companyGroup);

	/**
	 * Find by cd send sms transaction.
	 *
	 * @param cdSendSmsTransaction
	 *            the cd send sms transaction
	 * @return the processing
	 */
	List<Processing> findByCdSendSmsTransaction(Long cdSendSmsTransaction);

	/**
	 * Find by cd send sms transaction and msisdn.
	 *
	 * @param cdSendSmsTransaction
	 *            the cd send sms transaction
	 * @param msisdn
	 *            the msisdn
	 * @return the processing
	 */
	List<Processing> findByCdSendSmsTransactionAndMsisdn(
			Long cdSendSmsTransaction, String msisdn);
}
