package br.com.jpa.amount.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.jpa.amount.model.Person;
import br.com.jpa.amount.repository.PersonRepository;

@Service
public class PersonService {

	private static Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
	
	private PersonRepository personRepository;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public Long getTotalOfRegisters() throws Exception {
		try {
			return personRepository.getTotalOfRegisters();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new Exception("Nao deveria ter chegado aqui: " 
					+ ex.getMessage());
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Person person) {
		LOGGER.debug("Saving person...");
		
		try {
			personRepository.save(person);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	public List<Person> getPersonWithChilds() throws Exception {
		LOGGER.debug("Getting persons...");
		
		try {
			return personRepository.getAllWithChilds();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new Exception("Nao deveria ter chegado aqui! Erro na recuperacao de registros...: "
					+ ex.getMessage());
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAll() {
		LOGGER.debug("Deleting person...");
		
		try {
			personRepository.deleteAll();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
}
