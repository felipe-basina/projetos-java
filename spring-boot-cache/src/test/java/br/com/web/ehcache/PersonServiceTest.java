package br.com.web.ehcache;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfigurationTest.class })
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	private PersonService personService;

	@Autowired
	private CacheManager cache;

	@Before
	public void setUp() {
		personRepository = Mockito.mock(PersonRepository.class);
		personService = new PersonService(personRepository);
	}

	@Test
	public void testGetPersonByNameWithCache() {
		Person person = new Person("teste", new Date());
		person.setId(6L);

		String name = "teste";

		Mockito.when(personRepository.getPersonByName(name)).thenReturn(person);

		Person person1 = personService.getPersonByName(name);
		System.out.println(person1);

		SimpleValueWrapper wrapper = (SimpleValueWrapper) cache.getCache(
				"getPersonByName").get(name);
		// Assert.assertNotNull(wrapper);

		// System.out.println((Person) wrapper.get());

		// Assert.assertEquals(person1, (Person) wrapper.get());
	}
}
