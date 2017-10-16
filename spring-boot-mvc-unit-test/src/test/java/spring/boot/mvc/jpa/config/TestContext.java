package spring.boot.mvc.jpa.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import spring.boot.mvc.jpa.repository.UsuarioRepository;
import spring.boot.mvc.jpa.service.UsuarioService;


@Configuration
public class TestContext {

    @Bean
    public UsuarioRepository usuarioRepository() {
        return Mockito.mock(UsuarioRepository.class);
    }
    
    @Bean
    @Primary
    public UsuarioService usuarioService() {
        return Mockito.mock(UsuarioService.class);
    }
}