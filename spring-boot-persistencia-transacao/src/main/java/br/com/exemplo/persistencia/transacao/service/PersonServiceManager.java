package br.com.exemplo.persistencia.transacao.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.exemplo.persistencia.transacao.model.OperationEnum;
import br.com.exemplo.persistencia.transacao.model.Person;
import br.com.exemplo.persistencia.transacao.repository.PersonRepository;

@Service
public class PersonServiceManager {

	private static final Logger LOGGER = Logger.getLogger(PersonServiceManager.class);
	
	private static final boolean THROW_EXCEPTION = false;

	private EntityManager em;
	
	private PersonRepository personRepository;
	
	private AuditServiceManager auditService;
	
	@Autowired
	public PersonServiceManager(PersonRepository personRepository,
			AuditServiceManager auditService,
			EntityManager em) {
		this.personRepository = personRepository;
		this.auditService = auditService;
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	private List<Person> getAllPerson() {
		List<Person> list = new ArrayList<Person>();
		
		try {
			Query query = em.createNamedQuery("getAllPerson");
			list = (ArrayList<Person>) query.getResultList();
			auditService.setAuditInfo(null, OperationEnum.LIST_ALL);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			auditService.setAuditInfo(null, OperationEnum.LIST_ALL_ERROR);
		}
		
		return list;
	}
	
	public void deleteAllPerson() {
		List<Person> allPerson = this.getAllPerson();
		
		try {
			for (Person person : allPerson) {
				personRepository.delete(person.getId());
			}
			
			auditService.setAuditInfo(null, OperationEnum.REMOVE);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			auditService.setAuditInfo(null, OperationEnum.REMOVE_ERROR);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Person savePerson(Person p) throws Exception {
		try {
			p = personRepository.save(p);
			
			if (THROW_EXCEPTION) {
				throw new Exception("Erro para roll-back"); // Teste para roll - back
			} else{
				auditService.setAuditInfo(p, OperationEnum.SAVE);
				return p;
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			auditService.setAuditInfo(null, OperationEnum.SAVE_ERROR);
			throw new Exception(ex.getMessage());
		}
	}
	
}