package spring.boot.mvc.bootstrap.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import spring.boot.mvc.bootstrap.config.PersistenceConfig;
import spring.boot.mvc.bootstrap.config.TestContext;
import spring.boot.mvc.bootstrap.config.WebMvcConfig;
import spring.boot.mvc.bootstrap.form.model.UserForm;
import spring.boot.mvc.bootstrap.model.User;
import spring.boot.mvc.bootstrap.service.UserService;
import spring.boot.mvc.bootstrap.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebMvcConfig.class, PersistenceConfig.class })
@WebAppConfiguration
public class UserRestControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private UserService userServiceMock;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		Mockito.reset(userServiceMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void getAll() throws Exception {
		UserForm first = new UserForm(Long.valueOf("1"), "FirstName",
				"LastName", "flastname@domain.com");
		UserForm second = new UserForm(Long.valueOf("2"), "SecondName",
				"LastName", "slastname@domain.com");

		when(userServiceMock.getAllForm()).thenReturn(
				Arrays.asList(first, second));

		mockMvc.perform(get("/user/all"))
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
				.andExpect(jsonPath("$[0].name", is("FirstName")))
				.andExpect(jsonPath("$[0].lastName", is("LastName")))
				.andExpect(jsonPath("$[0].email", is("flastname@domain.com")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("SecondName")))
				.andExpect(jsonPath("$[1].lastName", is("LastName")))
				.andExpect(jsonPath("$[1].email", is("slastname@domain.com")));

		verify(userServiceMock, times(1)).getAllForm();
		verifyNoMoreInteractions(userServiceMock);
	}

	@Test
	public void saveUser() throws Exception {
		String name = "FirstName";
		String lastName = "LastName";
		String email = "flastname@domain.com";

		UserForm form = new UserForm(name, lastName, email);

		User user = new User(name, lastName, email);

		Date now = new Date();

		User userReturn = user;
		userReturn.setId(Long.valueOf("1"));
		userReturn.setCreationDate(now);
		userReturn.setUpdateDate(now);

		UserForm formReturn = new UserForm(userReturn.getId(),
				userReturn.getName(), userReturn.getLastName(),
				userReturn.getEmail());
		formReturn.setCreationDate(userReturn.getCreationDate());
		formReturn.setUpdateDate(userReturn.getUpdateDate());

		when(userServiceMock.getEntityFromUserForm(form)).thenReturn(user);
		when(userServiceMock.save(user)).thenReturn(userReturn);
		when(userServiceMock.getAllForm())
				.thenReturn(Arrays.asList(formReturn));

		mockMvc.perform(
				post("/user/save").contentType(TestUtil.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(form)))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is(name)))
				.andExpect(jsonPath("$[0].lastName", is(lastName)))
				.andExpect(jsonPath("$[0].email", is(email)))
				.andExpect(jsonPath("$[0].creationDate", notNullValue()))
				.andExpect(jsonPath("$[0].updateDate", notNullValue()));

		verify(userServiceMock, times(1)).save(user);
	}

	@Test
	public void saveUserWithEmailAlreadyRegistered() throws Exception {
		String name = "FirstName";
		String lastName = "LastName";
		String email = "flastname@domain.com";

		UserForm form = new UserForm(name, lastName, email);

		User user = new User(name, lastName, email);

		when(userServiceMock.getEntityFromUserForm(form)).thenReturn(user);
		when(userServiceMock.userExist(user)).thenReturn(true);

		mockMvc.perform(
				post("/user/save").contentType(TestUtil.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(form)))
				.andExpect(status().isConflict())
				.andExpect(
						content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(
						jsonPath("$.message", is("Email [".concat(email)
								.concat("] já cadastrado"))));

		verify(userServiceMock, times(1)).userExist(user);
	}

	@Test
	public void userGet() throws Exception {
		String name = "FirstName";
		String lastName = "LastName";
		String email = "flastname@domain.com";

		Date now = new Date();

		User user = new User(name, lastName, email);
		user.setId(Long.valueOf("1"));
		user.setCreationDate(now);
		user.setUpdateDate(now);

		UserForm form = new UserForm(Long.valueOf("1"), name, lastName, email);
		form.setCreationDate(now);
		form.setUpdateDate(now);

		when(userServiceMock.getById(Mockito.anyLong())).thenReturn(user);
		when(userServiceMock.getUserFormFromEntity(user)).thenReturn(form);
		
		mockMvc.perform(get("/user/get/{id}", Long.valueOf("1")))
				.andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is(name)))
				.andExpect(jsonPath("$.lastName", is(lastName)))
				.andExpect(jsonPath("$.email", is(email)))
				.andExpect(jsonPath("$.creationDate", notNullValue()))
				.andExpect(jsonPath("$.updateDate", notNullValue()));

		verify(userServiceMock, times(1)).getById(Mockito.anyLong());
		verify(userServiceMock, times(1)).getUserFormFromEntity(user);
	}

	@Test
	public void userGetNotFound() throws Exception {
		Long id = Long.valueOf("1");

		when(userServiceMock.getById(id)).thenReturn(null);
		
		mockMvc.perform(get("/user/get/{id}", id))
				.andExpect(status().isNotFound())
				.andExpect(
						content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(
						jsonPath("$.message", is("Nenhum usuário cadastrado com id ["
								.concat(String.valueOf(id)).concat("]"))));

		verify(userServiceMock, times(1)).getById(id);
	}

	@Test
	public void deleteUser() throws Exception {
		Long id = Long.valueOf("1");
		
		String name = "FirstName";
		String lastName = "LastName";
		String email = "flastname@domain.com";

		User user = new User(name, lastName, email);

		Date now = new Date();

		User userReturn = user;
		userReturn.setId(Long.valueOf("1"));
		userReturn.setCreationDate(now);
		userReturn.setUpdateDate(now);

		UserForm formReturn = new UserForm(userReturn.getId(),
				userReturn.getName(), userReturn.getLastName(),
				userReturn.getEmail());
		formReturn.setCreationDate(userReturn.getCreationDate());
		formReturn.setUpdateDate(userReturn.getUpdateDate());

		when(userServiceMock.getById(id)).thenReturn(userReturn);
		doNothing().when(userServiceMock).deleteUser(id);
		when(userServiceMock.getAllForm())
				.thenReturn(Arrays.asList(formReturn));
		
		mockMvc.perform(get("/user/delete/{id}", id))
			.andExpect(status().isOk())
			.andExpect(
					content().contentType(TestUtil.APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].name", is(name)))
			.andExpect(jsonPath("$[0].lastName", is(lastName)))
			.andExpect(jsonPath("$[0].email", is(email)))
			.andExpect(jsonPath("$[0].creationDate", notNullValue()))
			.andExpect(jsonPath("$[0].updateDate", notNullValue()));
		
		verify(userServiceMock, times(1)).deleteUser(id);
		verify(userServiceMock, times(1)).getAllForm();
	}

	@Test
	public void deleteUserNotFound() throws Exception {
		Long id = Long.valueOf("1");
		
		when(userServiceMock.getById(id)).thenReturn(null);
		
		mockMvc.perform(get("/user/delete/{id}", id))
				.andExpect(status().isNotFound())
				.andExpect(
						content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(
						jsonPath("$.message", is("Nenhum usuário cadastrado com id ["
								.concat(String.valueOf(id)).concat("]"))));

		verify(userServiceMock, times(1)).getById(id);
	}
	
	@Test
	public void showHomePage() throws Exception {
		UserForm form = new UserForm(1l, "teste", "testes", "teste@teste.com");

		when(userServiceMock.getAllForm()).thenReturn(Arrays.asList(form));

		mockMvc.perform(get("/user/all")).andExpect(status().isOk());
		// .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		// .andExpect(jsonPath("$", hasSize(1)));

		verify(userServiceMock, times(1)).getAllForm();
		verifyNoMoreInteractions(userServiceMock);
	}
}
