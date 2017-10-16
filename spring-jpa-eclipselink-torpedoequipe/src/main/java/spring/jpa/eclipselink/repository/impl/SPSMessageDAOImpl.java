package spring.jpa.eclipselink.repository.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import spring.jpa.eclipselink.domain.SPSMessage;
import spring.jpa.eclipselink.repository.SPSMessageDAO;

/**
 * The Class SPSMessageDAOImpl.
 */
@Repository("sPSMessageDAO")
public class SPSMessageDAOImpl extends BaseDAOImpl<SPSMessage> implements
		SPSMessageDAO {

	/**
	 * Instantiates a new notification dao impl.
	 */
	public SPSMessageDAOImpl() {
		super(SPSMessage.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public SPSMessage findByMsisdn(final String msisdn) {
		Query query = this.getEntityManager().createQuery(
				"select n from " + getClazz().getSimpleName()
						+ " n where n.msisdn = :param ");
		query.setParameter("param", msisdn);

		List<SPSMessage> sPSMessages = query.getResultList();
		SPSMessage sPSMessage = null;
		if (!sPSMessages.isEmpty()) {
			sPSMessage = sPSMessages.iterator().next();
		}
		return sPSMessage;
	}

	@Override
	@SuppressWarnings("unchecked")
	public SPSMessage findByMsisdnAndStatus(final String msisdn,
			final String status) {
		Query query = this.getEntityManager().createQuery(
				"select n from "
						+ getClazz().getSimpleName()
						+ " n where n.msisdn = :msisdn "
								.concat(" and n.supplyStatus = :status "));

		query.setParameter("msisdn", msisdn);
		query.setParameter("status", status);

		List<SPSMessage> sPSMessages = query.getResultList();
		SPSMessage sPSMessage = null;
		if (!sPSMessages.isEmpty()) {
			sPSMessage = sPSMessages.iterator().next();
		}
		return sPSMessage;
	}
}
