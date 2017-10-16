package spring.boot.mvc.jpa.repository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import spring.boot.mvc.jpa.config.CacheeConfig;
import spring.boot.mvc.jpa.config.TestContext;
import spring.boot.mvc.jpa.config.WebMvcConfig;
import spring.boot.mvc.jpa.model.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = {TestContext.class, CacheeConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class UsuarioRepositoryTest {

	@Autowired
	private CacheManager manager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	@SuppressWarnings("unchecked")
	public void findByCache() {
		Usuario save = new Usuario("FELIPE", "NASCIMENTO", "11973008408");
		Usuario saved = save;
		saved.setId(1l);
	
		usuarioRepository.save(saved);
		
		// Primeira verificacao (sem cache)
		usuarioRepository.findByNome("FELIPE");

		// Segunda verificacao (com cache)
		usuarioRepository.findByNome("FELIPE");
		
		assertNotNull(manager.getCache("findByNomeCache").get("FELIPE"));
		
		Cache cache = manager.getCache("findByNomeCache");
		ValueWrapper wrapper = cache.get("FELIPE");
		List<Usuario> list = (ArrayList<Usuario>) wrapper.get();
		assertThat(list.get(0), is(saved));
		
		//System.out.println(manager.getCache("findByNomeCache").get("FELIPE").get());
	}
}