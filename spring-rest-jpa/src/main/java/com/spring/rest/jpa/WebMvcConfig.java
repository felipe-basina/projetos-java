package com.spring.rest.jpa;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

@Configuration
@ImportResource(value = { "classpath:resources/spring-core-config.xml" })
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/redirect.jsp");
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {

				ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED,
						"/404.jsp");
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND,
						"/404.jsp");
				ErrorPage error500Page = new ErrorPage(
						HttpStatus.INTERNAL_SERVER_ERROR, "/404.jsp");

				container.addErrorPages(error401Page, error404Page,
						error500Page);
			}
		};
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		String[] definitions = new String[] { "/WEB-INF/layouts/tiles.xml" };

		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(definitions);
		return tilesConfigurer;
	}
}