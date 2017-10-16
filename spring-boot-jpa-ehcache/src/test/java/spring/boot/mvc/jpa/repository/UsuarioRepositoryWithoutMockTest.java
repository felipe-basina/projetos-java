package spring.boot.mvc.jpa.repository;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import spring.boot.mvc.jpa.config.PersistenceConfig;
import spring.boot.mvc.jpa.config.WebMvcConfig;
import spring.boot.mvc.jpa.model.Usuario;
import spring.boot.mvc.jpa.service.UsuarioService;
import spring.boot.mvc.jpa.service.UsuarioServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = {WebMvcConfig.class, PersistenceConfig.class})
@WebAppConfiguration
public class UsuarioRepositoryWithoutMockTest {

	@Autowired
	private CacheManager manager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private UsuarioService usuarioService;

	@Test
	public void findByCache() {
		usuarioService = new UsuarioServiceImpl(usuarioRepository);
		
		Usuario save = new Usuario("FELIPE", "NASCIMENTO", "11973008408");
		Usuario saved = save;
		saved.setId(1l);

		usuarioService.save(save);
		
		for (int i = 0; i < 4; i++) {
			usuarioService.findByNome("FELIPE");
		}
	}
	
	
	@Test
	public void findByCache2() {
		Usuario save = new Usuario("FELIPE", "NASCIMENTO", "11973008408");
		Usuario saved = save;
		saved.setId(1l);

		usuarioRepository.save(saved);
		
		for (int i = 0; i < 4; i++) {
			System.out.println("\n#### iteration: " + i + " - " + usuarioRepository.findByNome("FELIPE"));
		}
		
		System.out.println("\n#### iteration none: " + " - " + usuarioRepository.findByNome("FELIPi"));
	}
}