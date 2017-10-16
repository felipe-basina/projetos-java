package spring.boot.mvc.bootstrap.config;

import javax.persistence.EntityManager;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import spring.boot.mvc.bootstrap.repository.TodoRepository;
import spring.boot.mvc.bootstrap.repository.UserRepository;
import spring.boot.mvc.bootstrap.service.TodoService;
import spring.boot.mvc.bootstrap.service.TodoServiceImpl;
import spring.boot.mvc.bootstrap.service.UserService;
import spring.boot.mvc.bootstrap.service.UserServiceImpl;


@Configuration
public class TestContext {
    
	@Bean
	@Primary
	public EntityManager getEntityManager() {
		return Mockito.mock(EntityManager.class);
	}
	
	@Bean
	public UserRepository userRepository() {
		return Mockito.mock(UserRepository.class);
	}
    
	@Bean
	public TodoRepository todoRepository() {
		return Mockito.mock(TodoRepository.class);
	}
	
    @Bean
    @Primary
    public UserService usuarioService() {
        return Mockito.mock(UserServiceImpl.class);
    }
    
    @Bean
    @Primary
    public TodoService todoService() {
    	return Mockito.mock(TodoServiceImpl.class);
    }
}