package br.com.rest;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import br.com.rest.model.Todo;
import br.com.rest.repository.TodoRepository;

@SpringBootApplication
public class InitApplication {

	@Bean
	protected CommandLineRunner setInitialData(
			final TodoRepository todoRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... paramVarArgs) throws Exception {
				// Persiste informações iniciais
				for (int index = 0; index < 5; index++) {
					Todo todo = new Todo("Title " + index, "Description 0"
							+ index, new Date());
					todoRepository.save(todo);
				}

				// Recupera registros cadastrados
				for (Todo todo : todoRepository.findAll()) {
					System.out.println(" ----> " + todo);
				}
			}
		};
	}

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
		messageBundle.setBasename("classpath:message/messages_pt_BR");
		messageBundle.setDefaultEncoding("UTF-8");
		return messageBundle;
	}

	public static void main(String[] args) {
		SpringApplication.run(InitApplication.class, args);
	}
}