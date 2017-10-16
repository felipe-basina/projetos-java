package spring.boot.mvc.bootstrap.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.boot.mvc.bootstrap.model.Todo;

@Transactional
public interface TodoRepository extends BaseRepository<Todo, Long> {

	@Override
	@Query(value = "SELECT MAX(id) FROM todo", nativeQuery = false)
	long getLastId();

	@Query(name = "getTodoWithUser", value = "SELECT t FROM todo t JOIN FETCH t.usuario ORDER BY t.usuario.creationDate DESC")
	public List<Todo> getTodoWithUser();
	
	@Query(name = "getTodoWithUserByTodoId", value = "SELECT t FROM todo t JOIN FETCH t.usuario WHERE t.id = :todoId")
	public Todo getTodoWithUserByTodoId(@Param(value = "todoId") Long todoId);
}