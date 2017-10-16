package spring.jpa.eclipselink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.jpa.eclipselink.domain.SPSMessage;
import spring.jpa.eclipselink.repository.SPSMessageDAO;

@Service
public class SPSMessageInterfaceImpl {

	@Autowired
	private SPSMessageDAO spsMessageDao;

	public SPSMessage recuperarPorMsisdn(final String msisdn) {
		return spsMessageDao.findByMsisdn(msisdn);
	}
	
	public SPSMessage recuperarPorMsisdnEStatus(final String msisdn,
			final String status) {
		return spsMessageDao.findByMsisdnAndStatus(msisdn, status);
	}
}