package spring.boot.mvc.jpa.config;

import org.mockito.Mockito;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import spring.boot.mvc.jpa.repository.UsuarioRepository;
import spring.boot.mvc.jpa.service.UsuarioService;


@Configuration
@EnableCaching
@ComponentScan(basePackages = "spring.boot.mvc.jpa")
public class TestContext {

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("findByNomeCache");
	}
	
    @Bean
    @Primary
    public UsuarioRepository usuarioRepository() {
        return Mockito.mock(UsuarioRepository.class);
    }
    
    @Bean
    @Primary
    public UsuarioService usuarioService() {
        return Mockito.mock(UsuarioService.class);
    }
}