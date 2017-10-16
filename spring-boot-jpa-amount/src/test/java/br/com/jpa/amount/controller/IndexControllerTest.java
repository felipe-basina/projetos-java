package br.com.jpa.amount.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.jpa.amount.model.Many;
import br.com.jpa.amount.model.One;
import br.com.jpa.amount.model.Person;
import br.com.jpa.amount.service.PersonService;

public class IndexControllerTest {

	@Mock
	private PersonService personService;
	
	private MockMvc mockMvc;
	
	private List<Person> persons = new ArrayList<Person>();
	
	private static final Integer TOTAL_PERSONS = 6, TOTAL_MANIES = 2;
	
	@Before
	public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
 
        this.mockMvc = MockMvcBuilders.standaloneSetup(new IndexController(this.personService)).build();
        
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
	public void testTotal() throws Exception {
		Mockito.when(this.personService.getTotalOfRegisters())
			.thenReturn(TOTAL_PERSONS.longValue());
		
		this.mockMvc.perform(get("/total"))
			.andExpect(status().isOk())
			.andExpect(content().string(TOTAL_PERSONS.toString()));
		
		Mockito.verify(this.personService, times(1)).getTotalOfRegisters();
	}

	@Test
	public void testIndex() throws Exception {
		this.mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello!"));
	}

	@Test
	public void testGetAll() throws Exception {
		Mockito.when(this.personService.getPersonWithChilds())
			.thenReturn(this.persons);
		
		this.mockMvc.perform(get("/all"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("persons", hasSize(TOTAL_PERSONS)))
			.andExpect(view().name("all.jsp"));
		
		Mockito.verify(this.personService, times(1)).getPersonWithChilds();
	}

	@Test
	public void testList() throws Exception {
		Mockito.when(this.personService.getPersonWithChilds())
			.thenReturn(this.persons);
		
		this.mockMvc.perform(get("/mav"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("persons", hasSize(TOTAL_PERSONS)))
			.andExpect(
					model().attribute(
							"persons",
							hasItem(allOf(
									hasProperty("id"),
									hasProperty("name"),
									hasProperty("creationDate"),
									hasProperty("active"),
									hasProperty("one"),
									hasProperty("manies", hasSize(TOTAL_MANIES))))))
			.andExpect(view().name("list.jsp"));
		
		Mockito.verify(this.personService, times(1)).getPersonWithChilds();
	}

	@Test
	public void testListException() throws Exception {
		String errorMsg = "Erro...";
		
		Mockito.doThrow(new Exception(errorMsg))
			.when(this.personService).getPersonWithChilds();
		
		this.mockMvc.perform(get("/mav"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("errorMsg", errorMsg))
			.andExpect(view().name("list.jsp"));
		
		Mockito.verify(this.personService, times(1)).getPersonWithChilds();		
	}

	@Test
	public void testListInner() throws Exception {
		Mockito.when(this.personService.getPersonWithChilds())
			.thenReturn(this.persons);
		
		this.mockMvc.perform(get("/mav2"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("persons", hasSize(TOTAL_PERSONS)))
			.andExpect(
					model().attribute(
							"persons",
							hasItem(allOf(
									hasProperty("id"),
									hasProperty("name"),
									hasProperty("creationDate"),
									hasProperty("active"),
									hasProperty("one"),
									hasProperty("manies", hasSize(TOTAL_MANIES))))))
			.andExpect(view().name("list-inner.jsp"));
		
		Mockito.verify(this.personService, times(1)).getPersonWithChilds();
	}

	@Test
	public void testListInnerException() throws Exception {
		String errorMsg = "Erro...";
		
		Mockito.doThrow(new Exception(errorMsg))
			.when(this.personService).getPersonWithChilds();
		
		this.mockMvc.perform(get("/mav2"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("errorMsg", errorMsg))
			.andExpect(view().name("list-inner.jsp"));
		
		Mockito.verify(this.personService, times(1)).getPersonWithChilds();	
	}

}