package br.com.web.ehcache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	
	private PersonService personService;

	@Autowired
	private CacheManager cacheManager;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value = "/all")
	public List<Person> getAllPerson() {
		return personService.getAll();
	}

	@RequestMapping(value = "/find/{name}", method = RequestMethod.GET)
	public Person getPersonByName(@PathVariable(value = "name") String name) {
		this.printCacheInfo(name);
		return personService.getPersonByName(name);
	}

	@RequestMapping(value = "/all/{name}", method = RequestMethod.GET)
	public List<Person> getAllByName(@PathVariable(value = "name") String name) {
		this.printCacheInfo(name);
		return personService.getAllByName(name);
	}
	
	private void printCacheInfo(String name) {
		LOGGER.debug(" -> Check caching... ");
		Cache cache = cacheManager.getCache("getPersonByName");
		LOGGER.debug(" -> Cache is null? " + (cache == null));
		SimpleValueWrapper wrapper = (SimpleValueWrapper) cache.get(name);
		if (wrapper != null
				&& wrapper.get() != null) {
		LOGGER.debug(" --> Cache: " + cache.getName() 
				+ "\n --> " + wrapper
				+ "\n --> " + (Person) wrapper.get());
		}
	}
}
