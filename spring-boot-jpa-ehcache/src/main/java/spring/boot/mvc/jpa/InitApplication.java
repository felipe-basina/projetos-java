package spring.boot.mvc.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class InitApplication extends SpringBootServletInitializer implements
		WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InitApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<InitApplication> applicationClass = InitApplication.class;
}