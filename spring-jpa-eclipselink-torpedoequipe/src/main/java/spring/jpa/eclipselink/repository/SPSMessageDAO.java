package spring.jpa.eclipselink.repository;

import spring.jpa.eclipselink.domain.SPSMessage;

/**
 * The Interface SPSMessageDAO.
 */
public interface SPSMessageDAO extends BaseDAO<SPSMessage> {

	/**
	 * Find by msisdn.
	 *
	 * @param msisdn
	 *            the msisdn
	 * @return the company
	 */
	SPSMessage findByMsisdn(String msisdn);

	/**
	 * Find by msisdn and status.
	 *
	 * @param msisdn
	 *            the msisdn
	 * @param status
	 *            the status
	 * @return the sPS message
	 */
	SPSMessage findByMsisdnAndStatus(String msisdn, String status);

}
