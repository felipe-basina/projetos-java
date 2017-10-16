package spring.boot.mvc.jpa.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import spring.boot.mvc.jpa.config.TestContext;
import spring.boot.mvc.jpa.config.WebMvcConfig;
import spring.boot.mvc.jpa.form.model.UsuarioForm;
import spring.boot.mvc.jpa.model.Usuario;
import spring.boot.mvc.jpa.service.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebMvcConfig.class })
@WebAppConfiguration
public class CRUDControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private UsuarioService usuarioServiceMock;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private MessageSource message;

	@Before
	public void setUp() throws Exception {
		Mockito.reset(usuarioServiceMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void index() throws Exception {
		List<UsuarioForm> usuarios = new ArrayList<UsuarioForm>();

		when(usuarioServiceMock.getAll()).thenReturn(usuarios);

		mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(view().name("usuario/form"))
				.andExpect(model().attribute("usuarios", hasSize(0)))
				.andExpect(model().attribute("command", new UsuarioForm()));
	}

	@Test
	public void getAll() throws Exception {
		List<UsuarioForm> usuarios = new ArrayList<UsuarioForm>();

		when(usuarioServiceMock.getAll()).thenReturn(usuarios);

		mockMvc.perform(get("/getAll")).andExpect(status().isOk())
				.andExpect(view().name("usuario/form"))
				.andExpect(model().attribute("usuarios", hasSize(0)))
				.andExpect(model().attribute("command", new UsuarioForm()));
	}

	@Test
	public void salvar() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		usuario.setTelefone("11973008408");
		usuario.setUltimoNome("NASCIMENTO");
		usuario.setNome("FELIPE");

		List<UsuarioForm> usuarios = new ArrayList<UsuarioForm>();
		UsuarioForm form = new UsuarioForm(usuario.getId(), usuario.getNome(),
				usuario.getUltimoNome(), usuario.getTelefone());

		usuarios.add(form);

		when(usuarioServiceMock.getAll()).thenReturn(usuarios);

		mockMvc.perform(
				post("/save").param("nome", "FELIPE")
						.param("ultimoNome", "NASCIMENTO")
						.param("telefone", "11973008408"))
				.andExpect(status().isOk())
				.andExpect(view().name("usuario/form"))
				.andExpect(model().attribute("usuarios", hasSize(1)))
				.andExpect(
						model().attribute(
								"usuarios",
								hasItem(allOf(
										hasProperty("id", is(1L)),
										hasProperty("nome", is("FELIPE")),
										hasProperty("ultimoNome",
												is("NASCIMENTO")),
										hasProperty("telefone",
												is("11973008408"))))));
	}

	@Test
	public void salvarDadosIncompletos() throws Exception {
		mockMvc.perform(post("/save").param("nome", "FELIPE"))
				.andExpect(status().isOk())
				.andExpect(view().name("usuario/form"))
				.andExpect(
						model().attribute(
								"mensagem",
								message.getMessage(
										"spring.boot.controller.save.msg.campos",
										null, Locale.getDefault())));
		 /*.andExpect(model().attribute("mensagem",
		 "Preencha todos os campos obrigatórios!!"));*/
		 
	}

	@Test
	public void atualizar() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		usuario.setTelefone("1173008408");
		usuario.setUltimoNome("NASCIMENTO");
		usuario.setNome("FELIPE");

		List<UsuarioForm> usuarios = new ArrayList<UsuarioForm>();
		UsuarioForm form = new UsuarioForm(usuario.getId(), usuario.getNome(),
				usuario.getUltimoNome(), "11973008408");

		usuarios.add(form);

		when(usuarioServiceMock.findByNome(Mockito.anyString())).thenReturn(
				usuario);
		when(usuarioServiceMock.getAll()).thenReturn(usuarios);

		mockMvc.perform(
				post("/save").param("nome", "FELIPE")
						.param("ultimoNome", "NASCIMENTO")
						.param("telefone", "11973008408"))
				.andExpect(status().isOk())
				.andExpect(view().name("usuario/form"))
				.andExpect(model().attribute("usuarios", hasSize(1)))
				.andExpect(
						model().attribute(
								"usuarios",
								hasItem(allOf(
										hasProperty("id", is(1L)),
										hasProperty("nome", is("FELIPE")),
										hasProperty("ultimoNome",
												is("NASCIMENTO")),
										hasProperty("telefone",
												is("11973008408"))))));
	}

	@Test
	public void getUser() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		usuario.setTelefone("11973008408");
		usuario.setUltimoNome("NASCIMENTO");
		usuario.setNome("FELIPE");

		List<UsuarioForm> usuarios = new ArrayList<UsuarioForm>();
		UsuarioForm form = new UsuarioForm(usuario.getId(), usuario.getNome(),
				usuario.getUltimoNome(), usuario.getTelefone());

		usuarios.add(form);

		when(usuarioServiceMock.findByNome(Mockito.anyString())).thenReturn(
				usuario);
		when(usuarioServiceMock.getAll()).thenReturn(usuarios);

		mockMvc.perform(get("/getUser").param("nome", "FELIPE"))
				.andExpect(status().isOk())
				.andExpect(view().name("usuario/form"))
				.andExpect(model().attribute("usuarios", hasSize(1)))
				.andExpect(
						model().attribute(
								"usuarios",
								hasItem(allOf(
										hasProperty("id", is(1L)),
										hasProperty("nome", is("FELIPE")),
										hasProperty("ultimoNome",
												is("NASCIMENTO")),
										hasProperty("telefone",
												is("11973008408"))))))
				.andExpect(
						model().attribute("command",
								usuario.getUsuarioFormFromUsuarioEntity()));
	}

	@Test
	public void getUserNotFound() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		usuario.setTelefone("11973008408");
		usuario.setUltimoNome("NASCIMENTO");
		usuario.setNome("FELIPE");

		List<UsuarioForm> usuarios = new ArrayList<UsuarioForm>();
		UsuarioForm form = new UsuarioForm(usuario.getId(), usuario.getNome(),
				usuario.getUltimoNome(), usuario.getTelefone());

		usuarios.add(form);

		when(usuarioServiceMock.findByNome(Mockito.anyString())).thenReturn(
				null);
		when(usuarioServiceMock.getAll()).thenReturn(usuarios);

		mockMvc.perform(get("/getUser").param("nome", "FELIPE"))
				.andExpect(status().isOk())
				.andExpect(view().name("usuario/form"))
				.andExpect(model().attribute("usuarios", hasSize(1)))
				.andExpect(
						model().attribute(
								"usuarios",
								hasItem(allOf(
										hasProperty("id", is(1L)),
										hasProperty("nome", is("FELIPE")),
										hasProperty("ultimoNome",
												is("NASCIMENTO")),
										hasProperty("telefone",
												is("11973008408"))))))
				.andExpect(
						model().attribute("mensagem",
								message.getMessage(
										"spring.boot.controller.getuser.msg.campos",
										new Object[] { "FELIPE" }, Locale.getDefault())));
	}

	@Test
	public void delete() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		usuario.setTelefone("11973008408");
		usuario.setUltimoNome("NASCIMENTO");
		usuario.setNome("FELIPE");

		List<UsuarioForm> usuarios = new ArrayList<UsuarioForm>();
		UsuarioForm form = new UsuarioForm(usuario.getId(), usuario.getNome(),
				usuario.getUltimoNome(), usuario.getTelefone());

		usuarios.add(form);

		when(usuarioServiceMock.findByNome("FELIPE")).thenReturn(usuario);
		when(usuarioServiceMock.getAll()).thenReturn(usuarios);
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}
		}).when(usuarioServiceMock).remove(usuario);

		mockMvc.perform(get("/remove").param("nome", "FELIPE"))
				.andExpect(status().isOk())
				.andExpect(view().name("usuario/form"))
				.andExpect(model().attribute("usuarios", hasSize(1)))
				.andExpect(
						model().attribute(
								"usuarios",
								hasItem(allOf(
										hasProperty("id", is(1L)),
										hasProperty("nome", is("FELIPE")),
										hasProperty("ultimoNome",
												is("NASCIMENTO")),
										hasProperty("telefone",
												is("11973008408"))))))
				.andExpect(model().attribute("command", new UsuarioForm()));
		
		Mockito.verify(usuarioServiceMock, times(1)).findByNome("FELIPE");
		Mockito.verify(usuarioServiceMock, times(1)).getAll();
	}

	@Test
	public void deleteUserNotFound() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(1l);
		usuario.setTelefone("11973008408");
		usuario.setUltimoNome("NASCIMENTO");
		usuario.setNome("FELIPE");

		List<UsuarioForm> usuarios = new ArrayList<UsuarioForm>();
		UsuarioForm form = new UsuarioForm(usuario.getId(), usuario.getNome(),
				usuario.getUltimoNome(), usuario.getTelefone());

		usuarios.add(form);

		when(usuarioServiceMock.findByNome("FELIPE")).thenReturn(
				null);
		when(usuarioServiceMock.getAll()).thenReturn(usuarios);

		mockMvc.perform(get("/remove").param("nome", "FELIPE"))
				.andExpect(status().isOk())
				.andExpect(view().name("usuario/form"))
				.andExpect(model().attribute("usuarios", hasSize(1)))
				.andExpect(
						model().attribute(
								"usuarios",
								hasItem(allOf(
										hasProperty("id", is(1L)),
										hasProperty("nome", is("FELIPE")),
										hasProperty("ultimoNome",
												is("NASCIMENTO")),
										hasProperty("telefone",
												is("11973008408"))))))
				.andExpect(
						model().attribute("mensagem",
								message.getMessage(
										"spring.boot.controller.remove.msg.campos",
										new Object[] { "FELIPE" }, Locale.getDefault())));
		
		Mockito.verify(usuarioServiceMock, times(1)).findByNome("FELIPE");
		Mockito.verify(usuarioServiceMock, times(1)).getAll();
	}
}
