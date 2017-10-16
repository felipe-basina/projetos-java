package spring.boot.mvc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(
	    DefaultServletHandlerConfigurer configurer) {
	configurer.enable();
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
	reloadRes.setBasename("WEB-INF/messages/messages_pt_BR");
	reloadRes.setDefaultEncoding("ISO-8859-1");
	return reloadRes;
    }
}