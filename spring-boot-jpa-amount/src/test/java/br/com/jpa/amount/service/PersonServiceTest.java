package br.com.jpa.amount.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.jpa.amount.model.Many;
import br.com.jpa.amount.model.One;
import br.com.jpa.amount.model.Person;
import br.com.jpa.amount.repository.PersonRepository;

public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;
	
	private PersonService personService;
	
	private List<Person> persons = new ArrayList<Person>();
	
	private static final Integer TOTAL_PERSONS = 6, TOTAL_MANIES = 2;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		this.personService = new PersonService(this.personRepository);
        
        // Inicia a lista
        for (int index = 0; index < TOTAL_PERSONS; index++) {
        	Person person = new Person("name" + index, new Date(), (index % 2 == 0 ? true : false));
        	
        	One one = new One("name-desc-" + index, new Date(), new Date(System.currentTimeMillis()));
        	one.setPerson(person);
        	
        	for (int inner = 0; inner < TOTAL_MANIES; inner++) {
        		Many many = new Many("inner-desc-" + inner + " - " + index, new Random().nextLong());
        		many.setPerson(person);
        		person.getManies().add(many);
        	}
        	
        	persons.add(person);
        }
	}

	@Test
	public void testGetTotalOfRegisters() throws Exception {
		Mockito.when(this.personRepository.getTotalOfRegisters()).thenReturn(TOTAL_PERSONS.longValue());
		
		Long totalOfRegisters = this.personService.getTotalOfRegisters();
		
		Assert.assertNotNull(totalOfRegisters);
		Assert.assertTrue(totalOfRegisters.intValue() == TOTAL_PERSONS);
		
		Mockito.verify(this.personRepository, Mockito.times(1)).getTotalOfRegisters();
	}

	@Test
	public void testSave() {
		Person person = new Person("name", new Date(), true);
    	
    	One one = new One("name-desc", new Date(), new Date(System.currentTimeMillis()));
    	one.setPerson(person);
    	
    	for (int inner = 0; inner < TOTAL_MANIES; inner++) {
    		Many many = new Many("inner-desc", new Random().nextLong());
    		many.setPerson(person);
    		person.getManies().add(many);
    	}
    	
    	// Método void
    	Mockito.doAnswer(new Answer<Void>() {
    		@Override
    		public Void answer(InvocationOnMock invocation) throws Throwable {
    			return null;
    		}
		}).when(this.personRepository).save(person);
    	
    	this.personService.save(person);
    	
    	Mockito.verify(this.personRepository, Mockito.times(1)).save(person);
	}

	@Test
	public void testGetPersonWithChilds() throws Exception {
		Mockito.when(this.personRepository.getAllWithChilds()).thenReturn(persons);
		
		List<Person> list = this.personService.getPersonWithChilds();
		
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() == TOTAL_PERSONS);
		
		for (int index = 0; index < TOTAL_PERSONS; index++) {
			Assert.assertTrue(list.get(index).getManies().size() == TOTAL_MANIES);
		}
		
		Mockito.verify(this.personRepository, Mockito.times(1)).getAllWithChilds();
	}

	@Test
	public void testDeleteAll() {
    	// Método void
    	Mockito.doAnswer(new Answer<Void>() {
    		@Override
    		public Void answer(InvocationOnMock invocation) throws Throwable {
    			return null;
    		}
		}).when(this.personRepository).deleteAll();
    	
    	this.personService.deleteAll();
    	
    	Mockito.verify(this.personRepository, Mockito.times(1)).deleteAll();
	}

}
