package br.com.sample.spring.ehcache.repository;

import br.com.sample.spring.ehcache.model.Movie;

public interface MovieRepository {
	public Movie findByDirector(String name);
	
	public Movie findByMovieName(String name);
}
