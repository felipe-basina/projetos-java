package br.com.sample.spring.ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.sample.spring.ehcache.config.AppConfig;
import br.com.sample.spring.ehcache.repository.MovieRepository;

public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		Main main = new Main();
		
	    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	    MovieRepository obj = (MovieRepository) context.getBean("movieRepository");
	    
	    main.findByDirector(obj);
	    main.findByMovieName(obj);
	    main.findByMovieName2(obj);
	    
	    ((AnnotationConfigApplicationContext) context).close();
	    
	    System.exit(1);
	}
	
	private void findByDirector(MovieRepository obj) {
		/**
		 * Consulta 1 vez e as outras 2 recupera do cache
		 */
	    log.debug("Result : {}", obj.findByDirector("dummy"));
	    log.debug("Result : {}", obj.findByDirector("dummy"));
	    log.debug("Result : {}", obj.findByDirector("dummy"));
	    
	    /**
	     * Consulta 1 vez e a outra (1) recupera do cache
	     */
	    log.debug("Result : {}", obj.findByDirector("hi"));
	    log.debug("Result : {}", obj.findByDirector("hi"));

		/**
		 * Consulta 1 vez e as outras 2 recupera do cache
		 */
	    log.debug("Result : {}", obj.findByDirector("five"));
	    log.debug("Result : {}", obj.findByDirector("five"));
	    log.debug("Result : {}", obj.findByDirector("five"));		
	}
	
	private void findByMovieName(MovieRepository obj) {
		/**
		 * Consulta 1 vez e as outras 2 recupera do cache
		 */
		log.debug("Result : {}", obj.findByMovieName("other"));
		log.debug("Result : {}", obj.findByMovieName("other"));
		log.debug("Result : {}", obj.findByMovieName("other"));
	}
	
	private void findByMovieName2(MovieRepository obj) {
		/**
		 * Consulta 1 vez
		 */
		log.debug("Result : {}", obj.findByMovieName("other1"));

		/**
		 * Consulta 1 vez
		 */
		log.debug("Result : {}", obj.findByMovieName("other2"));
		
		/**
		 * Consulta 1 vez
		 */		
		log.debug("Result : {}", obj.findByMovieName("other3"));
	}
}