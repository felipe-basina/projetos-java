package br.com.spring.boot.web.security.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.spring.boot.web.security.jpa")
public class Application extends SpringBootServletInitializer implements
		WebApplicationInitializer {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
