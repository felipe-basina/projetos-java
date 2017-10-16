package br.com.rest.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.rest.controller.validation.ControllerValidationHandler;
import br.com.rest.model.Todo;
import br.com.rest.repository.TodoRepository;
import br.com.rest.util.MessageCodeType;
import br.com.rest.util.MessageFormType;
import br.com.rest.util.Utils;

@WebAppConfiguration
public class TodoControllerTest extends AbstractClass {

	@Mock
	private TodoRepository todoRepository;

	@Autowired
    private WebApplicationContext wac;

	/** 
	 * Utilizado para realização de testes de unidade, com definição de mock para
	 * as dependências existentes
	 ***/
	private MockMvc mockMvcWithStandaloneSetup;
	
	/** Utilizado para realização de testes integrados **/
	private MockMvc mockMvcWithWebAppContextSetup;

	private List<Todo> todos = new ArrayList<Todo>();
	
	private static final int TODOS_SIZE_LIST = 2;

	private String title = "Title ";
	private String description = "Description 0";
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		final StaticApplicationContext applicationContext = new StaticApplicationContext();
		applicationContext.registerSingleton("controllerValidationHandler",
				ControllerValidationHandler.class);

		final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
		webMvcConfigurationSupport.setApplicationContext(applicationContext);

		mockMvcWithStandaloneSetup = MockMvcBuilders
				.standaloneSetup(new TodoController(this.todoRepository))
				.setHandlerExceptionResolvers(
						webMvcConfigurationSupport.handlerExceptionResolver())
				.build();
		
		mockMvcWithWebAppContextSetup = MockMvcBuilders.webAppContextSetup(this.wac).build();

		for (int index = 0; index < TODOS_SIZE_LIST; index++) {
			Todo todo = new Todo(title + index, description + index, new Date());
			todo.setId(Long.parseLong(String.valueOf(index)));
			todos.add(todo);
		}
	}
	
	@Test
	public void getAll() throws Exception {
		Mockito.when(this.todoRepository.findAll()).thenReturn(todos);

		this.mockMvcWithStandaloneSetup
				.perform(get("/todo"))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("todos", hasSize(TODOS_SIZE_LIST)))
				.andExpect(jsonPath("todos[0]['id']", is(0)))
				.andExpect(jsonPath("todos[0]['title']", is(title + "0")))
				.andExpect(
						jsonPath("todos[0]['description']", is(description
								+ "0")))
				.andExpect(jsonPath("todos[1]['id']", is(1)))
				.andExpect(jsonPath("todos[1]['title']", is(title + "1")))
				.andExpect(
						jsonPath("todos[1]['description']", is(description
								+ "1")))
				.andExpect(jsonPath("size", is(TODOS_SIZE_LIST)));

		Mockito.verify(this.todoRepository, times(1)).findAll();
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}
	
	@Test
	public void getById() throws Exception {
		Mockito.when(this.todoRepository.findOne(Mockito.anyLong()))
			.thenReturn(todos.get(0));

		this.mockMvcWithStandaloneSetup
				.perform(get("/todo/" + 1))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("id", is(0)))
				.andExpect(jsonPath("title", is(title + "0")))
				.andExpect(
						jsonPath("description", is(description
								+ "0")));

		Mockito.verify(this.todoRepository, times(1)).findOne(Mockito.anyLong());
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}
	
	@Test
	public void getByIdNotFoundException() throws Exception {
		Mockito.when(this.todoRepository.findOne(Mockito.anyLong()))
				.thenReturn(null);

		this.mockMvcWithStandaloneSetup
				.perform(get("/todo/" + 1))
				.andExpect(status().isNotFound())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(
						jsonPath("id",
								is(MessageCodeType.ERROR_NOT_FOUND.getCode())))
				.andExpect(jsonPath("code", is(HttpStatus.NOT_FOUND.value())))
				.andExpect(
						jsonPath("description",
								is(MessageCodeType.ERROR_NOT_FOUND
										.getDescription())));

		Mockito.verify(this.todoRepository, times(1))
				.findOne(Mockito.anyLong());
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}

	@Test
	public void deleteItem() throws Exception {
		Todo todo = todos.get(0);

		Mockito.when(this.todoRepository.findOne(Mockito.anyLong()))
				.thenReturn(todo);

		// Método void
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}
		}).when(this.todoRepository).delete(todo);

		this.mockMvcWithStandaloneSetup
				.perform(delete("/todo/" + 1))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(
						jsonPath("id", is(MessageCodeType.OK_REMOVE.getCode())))
				.andExpect(
						jsonPath("code",
								is(HttpStatus.OK.value())))
				.andExpect(
						jsonPath("description",
								is(MessageCodeType.OK_REMOVE.getDescription())));

		Mockito.verify(this.todoRepository, times(1))
				.findOne(Mockito.anyLong());
		Mockito.verify(this.todoRepository, times(1)).delete(todo);
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}
	
	@Test
	public void deleteItemNotFoundException() throws Exception {
		Mockito.when(this.todoRepository.findOne(Mockito.anyLong()))
				.thenReturn(null);

		this.mockMvcWithStandaloneSetup
				.perform(delete("/todo/" + 1))
				.andExpect(status().isNotFound())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(
						jsonPath("id",
								is(MessageCodeType.ERROR_NOT_FOUND.getCode())))
				.andExpect(
						jsonPath("code",
								is(HttpStatus.NOT_FOUND.value())))
				.andExpect(
						jsonPath("description",
								is(MessageCodeType.ERROR_NOT_FOUND
										.getDescription())));

		Mockito.verify(this.todoRepository, times(1))
				.findOne(Mockito.anyLong());
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}

	@Test
	public void deleteItemInteralServerError() throws Exception {
		Todo todo = todos.get(0);

		Mockito.when(this.todoRepository.findOne(Mockito.anyLong()))
				.thenReturn(todo);

		String errorMessage = "Excecao delete...";

		Mockito.doThrow(new RuntimeException(errorMessage))
				.when(this.todoRepository).delete(todo);

		this.mockMvcWithStandaloneSetup
				.perform(delete("/todo/" + 1))
				.andExpect(status().isInternalServerError())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(
						jsonPath("id",
								is(MessageCodeType.ERROR_REMOVE.getCode())))
				.andExpect(
						jsonPath("code",
								is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
				.andExpect(
						jsonPath("description", is(MessageCodeType.ERROR_REMOVE
								.getDescription())))
				.andExpect(jsonPath("details", is(errorMessage)));

		Mockito.verify(this.todoRepository, times(1))
				.findOne(Mockito.anyLong());
		Mockito.verify(this.todoRepository, times(1)).delete(todo);
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}

	@Test
	public void saveItem() throws Exception {
		String title = "titulo";
		String description = "description";

		Todo todo = new Todo(title, description, null);

		Mockito.when(this.todoRepository.save(todo)).thenReturn(todo);

		this.mockMvcWithStandaloneSetup
				.perform(
						post("/todo/").contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE).content(
								Utils.convertObjectToJsonBytes(todo)))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(
						jsonPath("id", is(MessageCodeType.OK_SAVE.getCode())))
				.andExpect(
						jsonPath("code",
								is(HttpStatus.OK.value())))						
				.andExpect(
						jsonPath("description",
								is(MessageCodeType.OK_SAVE.getDescription())));

		Mockito.verify(this.todoRepository, times(1)).save(todo);
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}

	@Test
	public void saveItemInternalServerError() throws Exception {
		String title = "titulo";
		String description = "description";

		Todo todo = new Todo(title, description, null);

		String errorMessage = "Excecao save...";

		Mockito.doThrow(new RuntimeException(errorMessage))
				.when(this.todoRepository).save(todo);

		this.mockMvcWithStandaloneSetup
				.perform(
						post("/todo/").contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE).content(
								Utils.convertObjectToJsonBytes(todo)))
				.andExpect(status().isInternalServerError())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(
						jsonPath("id", is(MessageCodeType.ERROR_SAVE.getCode())))
				.andExpect(
						jsonPath("code",
								is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
				.andExpect(
						jsonPath("description",
								is(MessageCodeType.ERROR_SAVE.getDescription())))
				.andExpect(jsonPath("details", is(errorMessage)));

		Mockito.verify(this.todoRepository, times(1)).save(todo);
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}

	/**
	 * Palavra chave para pesquisa:
	 * junit jsonpath contains
	 * 
	 * Referência:
	 * http://stackoverflow.com/questions/26481059/testing-jsonpath-that-array-contains-specfic-objects-in-any-order 
	 ***/
	@Test
	public void saveItemNullParameters() throws Exception {
		Todo todo = new Todo(null, null, null);

		ResultActions resultActions = this.mockMvcWithWebAppContextSetup
				.perform(
						post("/todo/").contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE).content(
								Utils.convertObjectToJsonBytes(todo)))
				.andExpect(status().isBadRequest())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("totalErrors", is(2)))
				.andExpect(jsonPath("msgFormValidation").isArray())
				.andExpect(jsonPath("msgFormValidation", hasSize(2)))
				.andExpect(
						jsonPath("msgFormValidation[?(@.id == \'1\')]")
								.exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.message == \'"
										+ msgSource.getMessage(
												"error.title.notnull", null,
												Locale.getDefault()) + "\')]")
								.exists())
				.andExpect(
						jsonPath("msgFormValidation[?(@.id == \'2\')]")
								.exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.message == \'"
										+ msgSource.getMessage(
												"error.description.notnull",
												null, Locale.getDefault())
										+ "\')]").exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.type == \'"
										+ MessageFormType.ERROR.toString()
										+ "\')]").exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.httpStatus == \'"
										+ HttpStatus.BAD_REQUEST.value()
										+ "\')]").exists());

		// Exibe o resultado
		resultActions.andDo(MockMvcResultHandlers.print());

		Mockito.verify(this.todoRepository, times(0)).save(todo);
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}
	
	@Test
	public void saveItemMinSize() throws Exception {
		Todo todo = new Todo("", "", null);

		ResultActions resultActions = this.mockMvcWithWebAppContextSetup
				.perform(
						post("/todo/").contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE).content(
								Utils.convertObjectToJsonBytes(todo)))
				.andExpect(status().isBadRequest())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("totalErrors", is(2)))
				.andExpect(jsonPath("msgFormValidation").isArray())
				.andExpect(jsonPath("msgFormValidation", hasSize(2)))
				.andExpect(
						jsonPath("msgFormValidation[?(@.id == \'1\')]")
								.exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.message == \'"
										+ msgSource.getMessage(
												"error.title.size", null,
												Locale.getDefault()) + "\')]")
								.exists())
				.andExpect(
						jsonPath("msgFormValidation[?(@.id == \'2\')]")
								.exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.message == \'"
										+ msgSource.getMessage(
												"error.description.size", null,
												Locale.getDefault()) + "\')]")
								.exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.type == \'"
										+ MessageFormType.ERROR.toString()
										+ "\')]").exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.httpStatus == \'"
										+ HttpStatus.BAD_REQUEST.value()
										+ "\')]").exists());

		// Exibe o resultado
		resultActions.andDo(MockMvcResultHandlers.print());

		Mockito.verify(this.todoRepository, times(0)).save(todo);
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}

	@Test
	public void updateItem() throws Exception {
		String title = "tituloUp";
		String description = "descriptionUp";
		String id = "1";

		Todo todo = new Todo(title, description, null);

		Mockito.when(this.todoRepository.findOne(Mockito.anyLong()))
				.thenReturn(todo);
		Mockito.when(this.todoRepository.save(todo)).thenReturn(todo);

		this.mockMvcWithStandaloneSetup
				.perform(
						put("/todo/" + id).contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE).content(
								Utils.convertObjectToJsonBytes(todo)))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(
						jsonPath("id", is(MessageCodeType.OK_UPDATE.getCode())))
				.andExpect(jsonPath("code", is(HttpStatus.OK.value())))
				.andExpect(
						jsonPath("description",
								is(MessageCodeType.OK_UPDATE.getDescription())));

		Mockito.verify(this.todoRepository, times(1))
				.findOne(Mockito.anyLong());
		Mockito.verify(this.todoRepository, times(1)).save(todo);
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}
	
	@Test
	public void updateItemNotFoundException() throws Exception {
		String title = "tituloUp";
		String description = "descriptionUp";
		String id = "1";

		Todo todo = new Todo(title, description, null);

		Mockito.when(this.todoRepository.findOne(Mockito.anyLong()))
				.thenReturn(null);

		this.mockMvcWithStandaloneSetup
				.perform(
						put("/todo/" + id).contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE).content(
								Utils.convertObjectToJsonBytes(todo)))
				.andExpect(status().isNotFound())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(
						jsonPath("id",
								is(MessageCodeType.ERROR_NOT_FOUND.getCode())))
				.andExpect(
						jsonPath("code",
								is(HttpStatus.NOT_FOUND.value())))								
				.andExpect(
						jsonPath("description",
								is(MessageCodeType.ERROR_NOT_FOUND
										.getDescription())));

		Mockito.verify(this.todoRepository, times(1))
				.findOne(Mockito.anyLong());
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}

	@Test
	public void updateItemInternalServerError() throws Exception {
		String title = "tituloUp";
		String description = "descriptionUp";
		String id = "1";

		Todo todo = new Todo(title, description, null);

		String errorMessage = "Excecao update...";

		Mockito.when(this.todoRepository.findOne(Mockito.anyLong())).thenReturn(todo);
		Mockito.doThrow(new RuntimeException(errorMessage))
				.when(this.todoRepository).save(todo);

		this.mockMvcWithStandaloneSetup
				.perform(
						put("/todo/" + id).contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE).content(
								Utils.convertObjectToJsonBytes(todo)))
				.andExpect(status().isInternalServerError())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(
						jsonPath("id", is(MessageCodeType.ERROR_UPDATE.getCode())))
				.andExpect(
						jsonPath("code", is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
				.andExpect(
						jsonPath("description",
								is(MessageCodeType.ERROR_UPDATE.getDescription())))
				.andExpect(jsonPath("details", is(errorMessage)));

		Mockito.verify(this.todoRepository, times(1)).findOne(Mockito.anyLong());
		Mockito.verify(this.todoRepository, times(1)).save(todo);
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}
	
	@Test
	public void updateItemNullParameters() throws Exception {
		String id = "1";
		
		Todo todo = new Todo(null, null, null);

		ResultActions resultActions = this.mockMvcWithWebAppContextSetup
				.perform(
						put("/todo/" + id).contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE).content(
								Utils.convertObjectToJsonBytes(todo)))
				.andExpect(status().isBadRequest())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("totalErrors", is(2)))
				.andExpect(jsonPath("msgFormValidation").isArray())
				.andExpect(jsonPath("msgFormValidation", hasSize(2)))
				.andExpect(
						jsonPath("msgFormValidation[?(@.id == \'1\')]")
								.exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.message == \'"
										+ msgSource.getMessage(
												"error.title.notnull", null,
												Locale.getDefault()) + "\')]")
								.exists())
				.andExpect(
						jsonPath("msgFormValidation[?(@.id == \'2\')]")
								.exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.message == \'"
										+ msgSource.getMessage(
												"error.description.notnull",
												null, Locale.getDefault())
										+ "\')]").exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.type == \'"
										+ MessageFormType.ERROR.toString()
										+ "\')]").exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.httpStatus == \'"
										+ HttpStatus.BAD_REQUEST.value()
										+ "\')]").exists());

		// Exibe o resultado
		resultActions.andDo(MockMvcResultHandlers.print());

		Mockito.verify(this.todoRepository, times(0)).findOne(Mockito.anyLong());
		Mockito.verify(this.todoRepository, times(0)).save(todo);
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}
	
	@Test
	public void updateItemMinSize() throws Exception {
		String id = "1";
		
		Todo todo = new Todo("", "", null);

		ResultActions resultActions = this.mockMvcWithWebAppContextSetup
				.perform(
						put("/todo/" + id).contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE).content(
								Utils.convertObjectToJsonBytes(todo)))
				.andExpect(status().isBadRequest())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("totalErrors", is(2)))
				.andExpect(jsonPath("msgFormValidation").isArray())
				.andExpect(jsonPath("msgFormValidation", hasSize(2)))
				.andExpect(
						jsonPath("msgFormValidation[?(@.id == \'1\')]")
								.exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.message == \'"
										+ msgSource.getMessage(
												"error.title.size", null,
												Locale.getDefault()) + "\')]")
								.exists())
				.andExpect(
						jsonPath("msgFormValidation[?(@.id == \'2\')]")
								.exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.message == \'"
										+ msgSource.getMessage(
												"error.description.size",
												null, Locale.getDefault())
										+ "\')]").exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.type == \'"
										+ MessageFormType.ERROR.toString()
										+ "\')]").exists())
				.andExpect(
						jsonPath(
								"msgFormValidation[?(@.httpStatus == \'"
										+ HttpStatus.BAD_REQUEST.value()
										+ "\')]").exists());

		// Exibe o resultado
		resultActions.andDo(MockMvcResultHandlers.print());

		Mockito.verify(this.todoRepository, times(0)).findOne(Mockito.anyLong());
		Mockito.verify(this.todoRepository, times(0)).save(todo);
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}
	
	@Test
	public void methodNotAllowed() throws Exception {
		Todo todo = new Todo();
		
		// The 'post' operation cannot receive any parameter on a query string fashion
		this.mockMvcWithWebAppContextSetup
				.perform(
						post("/todo/" + 1).contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE).content(
								Utils.convertObjectToJsonBytes(todo)))
				.andExpect(status().isMethodNotAllowed())
				.andExpect(
						content().contentType(
								MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("msgFormValidation[0]['id']", is(1)))
				.andExpect(
						jsonPath("msgFormValidation[0]['httpStatus']",
								is(HttpStatus.METHOD_NOT_ALLOWED.value())))
				.andExpect(
						jsonPath("msgFormValidation[0]['message']", is(msgSource.getMessage(
								"error.method.notallowed", null,
								Locale.getDefault()))))
				.andExpect(
						jsonPath("msgFormValidation[0]['type']", is(MessageFormType.ERROR.toString())));

		Mockito.verify(this.todoRepository, times(0)).findOne(Mockito.anyLong());
		Mockito.verify(this.todoRepository, times(0)).findAll();
		Mockito.verify(this.todoRepository, times(0)).save(todo);
		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}
}