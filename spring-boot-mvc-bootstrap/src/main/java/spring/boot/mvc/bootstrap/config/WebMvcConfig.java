package spring.boot.mvc.bootstrap.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
		"spring.boot.mvc.bootstrap.model"
		,"spring.boot.mvc.bootstrap.controller"
		,"spring.boot.mvc.bootstrap.service"
		,"spring.boot.mvc.bootstrap.handlers"
		})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.jsp");
	}

	@Bean
	public UrlBasedViewResolver tilesViewResolver() {
		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		String[] definitions = new String[] { "/WEB-INF/layouts/tiles.xml" };

		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(definitions);
		return tilesConfigurer;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource reloadRes = new ReloadableResourceBundleMessageSource();
		reloadRes.setBasename("classpath:labels_pt_BR");
		reloadRes.setDefaultEncoding("ISO-8859-1");
		return reloadRes;
	}
}