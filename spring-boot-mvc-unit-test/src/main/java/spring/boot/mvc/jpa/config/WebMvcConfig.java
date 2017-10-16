package spring.boot.mvc.jpa.config;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan(basePackages = "spring.boot.mvc.jpa")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WebMvcConfig.class);

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public HandlerInterceptor adapter() {
		return new HandlerInterceptorAdapter() {
			@Override
			public void postHandle(HttpServletRequest request,
					HttpServletResponse response, Object handler,
					ModelAndView modelAndView) throws Exception {
				LOGGER.debug("# Inside interceptor...");

				Throwable t = (Throwable) request
						.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
				if (t != null) {
					LOGGER.debug("# Exception detected: " + t.getMessage());
				} else {
					LOGGER.debug("# None exception detected!");
				}
			}
		};
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