package br.com.web.ehcache;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Cacheable(value = "findByName", unless="#result == null")
	public Person findByName(String name);

	@Cacheable(value = "getPersonByName", unless="#result == null")
	@Query(value = "SELECT p FROM Person p WHERE UPPER(p.name) = UPPER(:name)")
	public Person getPersonByName(@Param("name") String name);

	@Cacheable(value = "getAllByName", unless="#result.size() <= 0")
	@Query(value = "SELECT p FROM Person p WHERE UPPER(p.name) = UPPER(:name)")
	public List<Person> getAllByName(@Param("name") String name);
}
