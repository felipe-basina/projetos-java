package spring.boot.mvc.jpa.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import spring.boot.mvc.jpa.form.model.UsuarioForm;
import spring.boot.mvc.jpa.model.Usuario;
import spring.boot.mvc.jpa.repository.UsuarioRepository;

public class UsuarioServiceImplTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private UsuarioService usuarioService;

	@Before
	public void setUp() throws Exception {
		usuarioRepository = Mockito.mock(UsuarioRepository.class);
		usuarioService = new UsuarioServiceImpl(usuarioRepository);
	}

	@Test
	public void saveOrUpdade() {
		Usuario save = new Usuario("FELIPE", "NASCIMENTO", "11973008408");
		Usuario saved = save;
		saved.setId(1l);

		when(usuarioRepository.save(save)).thenReturn(saved);

		Usuario retorno = usuarioService.save(save);

		verify(usuarioRepository, times(1)).save(saved);
		verifyNoMoreInteractions(usuarioRepository);

		assertNotNull(retorno.getId());
		assertThat(retorno.getId(), is(1l));
		assertThat(retorno.getNome(), is("FELIPE"));
		assertThat(retorno.getUltimoNome(), is("NASCIMENTO"));
		assertThat(retorno.getTelefone(), is("11973008408"));
	}

	@Test
	public void getAll() {		
		Usuario saved01 = new Usuario("FELIPE", "NASCIMENTO", "11973008408");
		saved01.setId(1l);

		Usuario saved02 = new Usuario("TESTE", "OTESTE", "1173008408");
		saved02.setId(2l);
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(saved01);
		usuarios.add(saved02);
		
		when(usuarioRepository.findAll()).thenReturn(usuarios);

		List<UsuarioForm> usuariosForm = usuarioService.getAll();

		verify(usuarioRepository, times(1)).findAll();
		verifyNoMoreInteractions(usuarioRepository);
		assertThat(usuariosForm.size(), is(2));
		assertThat(usuariosForm.get(0).getId(), is(1l));
		assertThat(usuariosForm.get(0).getNome(), is("FELIPE"));
		assertThat(usuariosForm.get(0).getUltimoNome(), is("NASCIMENTO"));
		assertThat(usuariosForm.get(0).getTelefone(), is("11973008408"));
		assertThat(usuariosForm.get(1).getId(), is(2l));
		assertThat(usuariosForm.get(1).getNome(), is("TESTE"));
		assertThat(usuariosForm.get(1).getUltimoNome(), is("OTESTE"));
		assertThat(usuariosForm.get(1).getTelefone(), is("1173008408"));
	}

	@Test
	public void remove() {
		Usuario save = new Usuario("FELIPE", "NASCIMENTO", "11973008408");
		save.setId(1l);

		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}
		}).when(usuarioRepository).delete(1l);

		usuarioService.remove(save);

		verify(usuarioRepository, times(1)).delete(1l);
		verifyNoMoreInteractions(usuarioRepository);
	}

	@Test
	public void findByNome() {		
		Usuario saved = new Usuario("FELIPE", "NASCIMENTO", "11973008408");
		saved.setId(1l);
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(saved);
		
		//when(usuarioRepository.findByNome("FELIPE")).thenReturn(usuarios);

		Usuario retorno = usuarioService.findByNome("FELIPE");

		verify(usuarioRepository, times(1)).findByNome("FELIPE");
		verifyNoMoreInteractions(usuarioRepository);
		assertThat(retorno.getId(), is(1l));
		assertThat(retorno.getNome(), is("FELIPE"));
		assertThat(retorno.getUltimoNome(), is("NASCIMENTO"));
		assertThat(retorno.getTelefone(), is("11973008408"));
	}	
}