package br.com.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rest.model.Message;
import br.com.rest.model.Todo;
import br.com.rest.model.TodoList;
import br.com.rest.repository.TodoRepository;
import br.com.rest.util.MessageCodeType;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

	private TodoRepository todoRepository;

	@Autowired
	public TodoController(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getAll() {
		return this.findAllTodo();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
		Todo todo = this.getTodoById(id);
		
		if (todo == null) {
			return this.setResponseEntity(MessageCodeType.ERROR_NOT_FOUND, 
					null, HttpStatus.NOT_FOUND);
		}
		
		return this.setResponseEntity(todo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<?> deleteItem(@PathVariable(value = "id") Long id) {
		Todo todo = this.getTodoById(id);
		
		if (todo == null) {
			return this.setResponseEntity(MessageCodeType.ERROR_NOT_FOUND, 
					null, HttpStatus.NOT_FOUND);
		}
		
		try {
			this.todoRepository.delete(todo);
		} catch (Exception ex) {
			return this.setResponseEntity(MessageCodeType.ERROR_REMOVE, 
					ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return this.setResponseEntity(MessageCodeType.OK_REMOVE, 
				null, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<?> saveItem(@Validated @RequestBody Todo todo) {
		try {
			// Set current date
			todo.setCreationDate(new Date());
			this.todoRepository.save(todo);
		} catch (Exception ex) {
			return this.setResponseEntity(MessageCodeType.ERROR_SAVE, 
					ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return this.setResponseEntity(MessageCodeType.OK_SAVE, 
				null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public ResponseEntity<?> updateItem(@PathVariable(value = "id") Long id, 
			@Validated @RequestBody Todo todo) {
		Todo currentTodo = this.getTodoById(id);
		
		if (currentTodo == null) {
			return this.setResponseEntity(MessageCodeType.ERROR_NOT_FOUND, 
					null, HttpStatus.NOT_FOUND);
		}
		
		try {
			currentTodo.setTitle(todo.getTitle());
			currentTodo.setDescription(todo.getDescription());
			this.todoRepository.save(currentTodo);
		} catch (Exception ex) {
			return this.setResponseEntity(MessageCodeType.ERROR_UPDATE, 
					ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		return this.setResponseEntity(MessageCodeType.OK_UPDATE, 
				null, HttpStatus.OK);
	}
	
	private ResponseEntity<TodoList> findAllTodo() {
		List<Todo> todos = this.todoRepository.findAll();
		TodoList todoList = new TodoList(todos, todos.size());
		return this.setResponseEntity(todoList, HttpStatus.OK);
	}
	
	private Todo getTodoById(Long id) {
		Todo todo = null;
		try {
			todo = this.todoRepository.findOne(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return todo;
	}
	
	private <T> ResponseEntity<T> setResponseEntity(T responseEntity, HttpStatus httpStatus) {
		return new ResponseEntity<T>(responseEntity, httpStatus);
	}
	
	private ResponseEntity<?> setResponseEntity(MessageCodeType messageCodeType, String details,
			HttpStatus httpStatus) {
		Message message = new Message(messageCodeType.getCode(),
				httpStatus.value(),
				messageCodeType.getDescription(), new Date());
		
		if (details != null && !"".equals(details)) {
			message.setDetails(details);
		}
		
		return new ResponseEntity<Message>(message, httpStatus);
	}
}