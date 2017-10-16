package br.com.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rest.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
