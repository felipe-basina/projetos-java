package br.com.sample.spring.ehcache.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import br.com.sample.spring.ehcache.model.Movie;

@Repository("movieRepository")
public class MovieRepositoryImpl implements MovieRepository {

	// each call will delay 2 seconds, simulate the slow query call
	@Cacheable(value="movieFindCache", key="#name")
	public Movie findByDirector(String name) {
		this.slowQuery(2000L);
		System.out.println("findByDirector is running...");
		return new Movie(1, "Forrest Gump", "Robert Zemeckis");
	}

	@Cacheable(value="directorFindByMovieNameCache", key="#name")
	public Movie findByMovieName(String name) {
		this.slowQuery(3000L);
		System.out.println("findByMovieName is running...");
		return new Movie(2, "Star Wars", "George Luccas");
	}
	
	private void slowQuery(long seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
