package br.com.web.ehcache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "personService")
public class PersonService {

	private PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> getAll() {
		return personRepository.findAll();
	}

	public Person getPersonByName(final String personName) {
		// return personRepository.findByName(personName);
		return personRepository.getPersonByName(personName);
	}

	public List<Person> getAllByName(final String name) {
		return personRepository.getAllByName(name);
	}
}
