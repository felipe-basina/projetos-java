package spring.jpa.eclipselink.repository;

import java.util.List;

import spring.jpa.eclipselink.domain.Receipt;

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
