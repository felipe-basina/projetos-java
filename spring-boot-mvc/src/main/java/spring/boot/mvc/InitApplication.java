package spring.boot.mvc;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class InitApplication extends SpringBootServletInitializer implements
	WebApplicationInitializer {

    public static void main(String[] args) {
	ApplicationContext ctx = SpringApplication.run(InitApplication.class,
		args);

	System.out.println("Let's inspect the beans provided by Spring Boot:");

	String[] beanNames = ctx.getBeanDefinitionNames();
	Arrays.sort(beanNames);
	for (String beanName : beanNames) {
	    System.out.println(beanName);
	}
    }

    @Override
    protected SpringApplicationBuilder configure(
	    SpringApplicationBuilder application) {
	return application.sources(applicationClass);
    }

    private static Class<InitApplication> applicationClass = InitApplication.class;
}