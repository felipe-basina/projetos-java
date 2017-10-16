package spring.jpa.eclipselink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.eclipselink.domain.Receipt;
import spring.jpa.eclipselink.repository.ReceiptDAO;

@Service
public class ReceiptInterfaceImpl {

	@Autowired
	private ReceiptDAO receiptDao;

	public List<Receipt> recuperarTodosPorFaixa(final int maxResults) {
		return receiptDao.findAllByRange(maxResults);
	}
}