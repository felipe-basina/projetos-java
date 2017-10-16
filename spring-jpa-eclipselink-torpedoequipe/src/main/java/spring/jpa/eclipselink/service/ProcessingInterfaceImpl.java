package spring.jpa.eclipselink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.eclipselink.domain.CompanyGroup;
import spring.jpa.eclipselink.domain.Processing;
import spring.jpa.eclipselink.repository.ProcessingDAO;

@Service
public class ProcessingInterfaceImpl {

	@Autowired
	private ProcessingDAO processingDao;

	public List<Processing> recuperarPorGrupoEmpresa(
			final CompanyGroup companyGroup) {
		return processingDao.findByCompanyGroup(companyGroup);
	}
	
	public List<Processing> recuperarPorCdTransacaoSms(
			final Long cdSendSmsTransaction) {
		return processingDao.findByCdSendSmsTransaction(cdSendSmsTransaction);
	}
	
	public List<Processing> recuperarPorCdTransacaoSmsEMsisdn(
			final Long cdSendSmsTransaction, final String msisdn) {
		return processingDao.findByCdSendSmsTransactionAndMsisdn(cdSendSmsTransaction, msisdn);
	}
}