package spring.boot.mvc.bootstrap.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import spring.boot.mvc.bootstrap.config.PersistenceConfig;
import spring.boot.mvc.bootstrap.config.TestContext;
import spring.boot.mvc.bootstrap.config.WebMvcConfig;
import spring.boot.mvc.bootstrap.form.model.TodoForm;
import spring.boot.mvc.bootstrap.service.TodoService;
import spring.boot.mvc.bootstrap.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebMvcConfig.class, PersistenceConfig.class })
@WebAppConfiguration
public class TodoRestControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private TodoService todoServiceMock;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		Mockito.reset(todoServiceMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void getAll() throws Exception {
		TodoForm first = new TodoForm(Long.valueOf("1"), "title1", "description1", "01/01/1990");
		first.setEmailId(Long.valueOf("1"));
		first.setEmail("email1@domain.com");
		
		TodoForm second = new TodoForm(Long.valueOf("2"), "title2", "description2", "02/02/1990");
		second.setEmailId(Long.valueOf("2"));
		second.setEmail("email2@domain.com");

		when(todoServiceMock.getAllForm()).thenReturn(
				Arrays.asList(first, second));

		mockMvc.perform(get("/todo/all"))
				.andExpect(status().isOk())
				.andExpect(
						content()
								.contentType(
										new MediaType(
												MediaType.APPLICATION_JSON
														.getType(),
												MediaType.APPLICATION_JSON
														.getSubtype(), Charset
														.forName("utf8"))))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].title", is("title1")))
				.andExpect(jsonPath("$[0].description", is("description1")))
				.andExpect(jsonPath("$[0].creationDate", is("01/01/1990")))
				.andExpect(jsonPath("$[0].emailId", is(1)))
				.andExpect(jsonPath("$[0].email", is("email1@domain.com")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].title", is("title2")))
				.andExpect(jsonPath("$[1].description", is("description2")))
				.andExpect(jsonPath("$[1].creationDate", is("02/02/1990")))
				.andExpect(jsonPath("$[1].emailId", is(2)))
				.andExpect(jsonPath("$[1].email", is("email2@domain.com")));

		verify(todoServiceMock, times(1)).getAllForm();
		verifyNoMoreInteractions(todoServiceMock);
	}

	@Test
	public void saveTodo() throws Exception {
		Long id = Long.valueOf("1");
		String title = "title";
		String description = "description";
		String creationDate = "01/01/1990";
		Long emailId = 1L; 
		String email = "email@domain.com";

		TodoForm form = new TodoForm();
		form.setTitle(title);
		form.setDescription(description);
		form.setCreationDate(creationDate);
		form.setEmailId(emailId);
		form.setEmail(email);

		TodoForm formReturn = new TodoForm(Long.valueOf(id.toString()), title, description, creationDate);
		formReturn.setEmailId(emailId);
		formReturn.setEmail(email);

		when(todoServiceMock.saveTodo(form)).thenReturn(formReturn);
		when(todoServiceMock.getAllForm()).thenReturn(Arrays.asList(formReturn));

		mockMvc.perform(
				post("/todo/save").contentType(TestUtil.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(form)))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].title", is(title)))
				.andExpect(jsonPath("$[0].description", is(description)))
				.andExpect(jsonPath("$[0].creationDate", is(creationDate)))
				.andExpect(jsonPath("$[0].emailId").value(1))
				.andExpect(jsonPath("$[0].email", is(email)));

		verify(todoServiceMock, times(1)).saveTodo(form);
	}

	@Test
	public void todoGet() throws Exception {
		Long id = Long.valueOf("1");
		String title = "title";
		String description = "description";
		String creationDate = "01/01/1990";
		Long emailId = 1L; 
		String email = "email@domain.com";

		TodoForm formReturn = new TodoForm(Long.valueOf(id.toString()), title, description, creationDate);
		formReturn.setEmailId(emailId);
		formReturn.setEmail(email);

		when(todoServiceMock.getById(Mockito.anyLong())).thenReturn(formReturn);
		
		mockMvc.perform(get("/todo/get/{id}", Long.valueOf("1")))
				.andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.title", is(title)))
				.andExpect(jsonPath("$.description", is(description)))
				.andExpect(jsonPath("$.creationDate", is(creationDate)))
				.andExpect(jsonPath("$.emailId").value(1))
				.andExpect(jsonPath("$.email", is(email)));

		verify(todoServiceMock, times(1)).getById(Mockito.anyLong());
		verifyNoMoreInteractions(todoServiceMock);
	}

	@Test
	public void deleteTodo() throws Exception {
		Long id = Long.valueOf("1");
		String title = "title";
		String description = "description";
		String creationDate = "01/01/1990";
		Long emailId = 1L; 
		String email = "email@domain.com";

		TodoForm formReturn = new TodoForm(Long.valueOf(id.toString()), title, description, creationDate);
		formReturn.setEmailId(emailId);
		formReturn.setEmail(email);
		
		doNothing().when(todoServiceMock).deleteTodoById(id);
		
		MvcResult result = mockMvc.perform(get("/todo/delete/{id}", id))
			.andExpect(status().isOk())
			.andExpect(
					content().contentType(TestUtil.APPLICATION_TEXT_ISO88591))
			.andReturn();
		
		org.junit.Assert.assertEquals("ok", result.getResponse().getContentAsString());
		
		verify(todoServiceMock, times(1)).deleteTodoById(id);
		verifyNoMoreInteractions(todoServiceMock);
	}
}