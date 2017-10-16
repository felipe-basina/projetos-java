package br.com.exemplo.persistencia.transacao.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.exemplo.persistencia.transacao.model.Audit;
import br.com.exemplo.persistencia.transacao.model.OperationEnum;
import br.com.exemplo.persistencia.transacao.model.Person;
import br.com.exemplo.persistencia.transacao.repository.AuditRepository;

@Service
public class AuditServiceManager {

	private static final Logger LOGGER = Logger.getLogger(AuditServiceManager.class);
	
	private EntityManager em;
	
	private AuditRepository auditRepository;
	
	@Autowired
	public AuditServiceManager(AuditRepository auditRepository,
			EntityManager em) {
		this.auditRepository = auditRepository;
		this.em = em;
	}
	
	public void deleteAllAudit() {
		try {
			for (Audit audit : this.getAudits()) {
				auditRepository.delete(audit.getId());
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Audit> getAudits() {
		List<Audit> audits = new ArrayList<Audit>();
		
		try {
			Query query = em.createNamedQuery("getAllAudit");
			audits = (ArrayList<Audit>) query.getResultList();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		return audits;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void setAuditInfo(Person person, OperationEnum operation) {
		if (person == null
				|| person.getId() == null
				|| person.getId() <= 0) {
			person = new Person();
			person.setId(0L);
		}
		
		Audit audit = new Audit();
		audit.setCreationDate(new Date());
		audit.setOperation(operation);
		audit.setPerson(person.getId());
		
		auditRepository.save(audit);
	}
}
