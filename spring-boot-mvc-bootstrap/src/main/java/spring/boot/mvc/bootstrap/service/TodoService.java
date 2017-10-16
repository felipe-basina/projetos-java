package spring.boot.mvc.bootstrap.service;

import java.util.List;

import spring.boot.mvc.bootstrap.form.model.TodoForm;

public interface TodoService {
	
	public List<TodoForm> getAllForm();
	
	public TodoForm saveTodo(TodoForm form);
	
	public TodoForm getById(Long id);
	
	public void deleteTodoById(Long id);
	
}
