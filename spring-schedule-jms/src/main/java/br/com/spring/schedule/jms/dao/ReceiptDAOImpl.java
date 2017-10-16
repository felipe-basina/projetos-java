package br.com.spring.schedule.jms.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.spring.schedule.jms.domain.Receipt;

/**
 * The Class ReceiptDAOImpl.
 */
@Repository("receiptDAO")
public class ReceiptDAOImpl extends BaseDAOImpl<Receipt> implements ReceiptDAO {

	/**
	 * Instantiates a new receipt dao impl.
	 */
	public ReceiptDAOImpl() {
		super(Receipt.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final List<Receipt> findAllByRange(final int maxResults) {
		Query query = this.getEntityManager().createNativeQuery(
				"SELECT * FROM SEMW_RECIBO_ENTREGA_MSE  ".concat(
						" WHERE FG_REGISTRO_ATIVO = 'Y' ").concat(
						" AND ROWNUM <= ? FOR UPDATE skip locked "),
				Receipt.class);
		query.setParameter(1, maxResults);
		return query.getResultList();
	}

}
