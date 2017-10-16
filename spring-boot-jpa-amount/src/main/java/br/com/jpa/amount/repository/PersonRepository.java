package br.com.jpa.amount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jpa.amount.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query(name = "getAllWithChilds", nativeQuery = false)
	public List<Person> getAllWithChilds();

	@Query(name = "getTotalOfRegisters", nativeQuery = true)
	public Long getTotalOfRegisters();
	
}
