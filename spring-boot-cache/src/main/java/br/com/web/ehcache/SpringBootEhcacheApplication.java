package br.com.web.ehcache;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class SpringBootEhcacheApplication extends SpringBootServletInitializer
		implements WebApplicationInitializer {

	private static final Class<SpringBootEhcacheApplication> clazz = SpringBootEhcacheApplication.class;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEhcacheApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(clazz);
	}
}

@Component
class SampleData {

	@Bean
	public CommandLineRunner setSampleData(
			final PersonRepository personRepository) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				List<String> names = Arrays.asList("Maria", "José", "João",
						"Pedro");

				for (String personName : names) {
					personRepository.save(new Person(personName, new Date()));
				}

				// Imprime os registros salvos na base de dados
				for (Person person : personRepository.findAll()) {
					System.out.println(" -> " + person);
				}
			}
		};
	}

}