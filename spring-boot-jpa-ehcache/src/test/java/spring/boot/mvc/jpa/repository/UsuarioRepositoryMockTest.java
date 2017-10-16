package spring.boot.mvc.jpa.repository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import spring.boot.mvc.jpa.config.CacheeConfig;
import spring.boot.mvc.jpa.config.TestContext;
import spring.boot.mvc.jpa.config.WebMvcConfig;
import spring.boot.mvc.jpa.model.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, CacheeConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class UsuarioRepositoryMockTest {

	@Autowired
	private CacheManager manager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Before
	public void setUp() throws Exception {
		//usuarioRepository = mock(UsuarioRepository.class);
	}
	
	/*@Test
	public void findByCache() {
		Usuario u = new Usuario("teste", "teste1", "11199999");
		u.setId(1l);
		Usuario u2 = new Usuario();

		List<Usuario> o1 = new ArrayList<Usuario>();
		o1.add(u);
		o1.add(u2);
		
		usuarioRepository.save(u);
		
		List<Usuario> o3;
		String nome = "teste";
		
		// Primeira verificacao (sem cache)
		o3 = usuarioRepository.findByNome(nome);
		Usuario result = o3.get(0);
		assertThat(result, is(u));
		
		// Segunda verificacao (com cache)
		o3 = usuarioRepository.findByNome(nome);
		result = o3.get(0);
		assertThat(result, is(u));
	
		assertNull(manager.getCache("findByNomeCache").get("FELIPE"));
		assertNotNull(manager.getCache("findByNomeCache").get(nome));
	}*/
}