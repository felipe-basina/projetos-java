package br.com.spring.schedule.jms.dao;

import java.util.List;

import br.com.spring.schedule.jms.domain.Receipt;

/**
 * The Interface ReceiptDAO.
 */
public interface ReceiptDAO extends BaseDAO<Receipt> {

	/**
	 * Find all by range.
	 *
	 * @param maxResults
	 *            the max results
	 * @return the list
	 */
	List<Receipt> findAllByRange(int maxResults);

}
