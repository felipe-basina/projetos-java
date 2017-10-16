package spring.boot.mvc.bootstrap.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.mvc.bootstrap.form.model.TodoForm;
import spring.boot.mvc.bootstrap.service.TodoService;

@RestController
public class TodoRestController {

	private static Logger log = LoggerFactory.getLogger(TodoRestController.class);

	private TodoService todoService;

	@Autowired
	public TodoRestController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	private static final String PREFIX_LOG = ".....";
	
	@RequestMapping(value = { "/todosave" }, method = { RequestMethod.GET })
	public void todoForm() {
		log.debug(PREFIX_LOG.concat("[/todosave] -> todoForm"));
	}
	
	@RequestMapping(value = { "/todo/save" }, method = { RequestMethod.POST }, consumes = "application/json")
	@ResponseBody
	public List<TodoForm> saveTodo(@RequestBody TodoForm form) {
		log.debug(PREFIX_LOG.concat("[/todo/save] -> saveTodo"));
		log.debug(PREFIX_LOG.concat("[/form]:\n\t").concat(form.toString()));
		
		form = todoService.saveTodo(form);
		log.debug(PREFIX_LOG.concat("TodoForm:\n") + form);
		
		return todoService.getAllForm();
	}
	
	@RequestMapping(value = { "/todo/all" }, method = { RequestMethod.GET })
	@ResponseBody
	public List<TodoForm> getAll() {
		log.debug(PREFIX_LOG.concat("[/todo/all] -> getAll"));
		return todoService.getAllForm();
	}
	
	@RequestMapping(value = { "/todo/get/{id}" }, method = { RequestMethod.GET })
	@ResponseBody
	public TodoForm getTodo(@PathVariable Long id) {
		log.debug(PREFIX_LOG.concat("[/todo/get/{id}] -> getTodo"));
		log.debug(PREFIX_LOG.concat("[/id]:\n\t").concat(String.valueOf(id)));		
		
		TodoForm form = todoService.getById(id);
		log.debug(PREFIX_LOG.concat("\n\tTodoForm: ".concat(form.toString())));
		
		return form;
	}
	
	@RequestMapping(value = { "/todo/delete/{id}" }, method = { RequestMethod.GET })
	public String removeTodo(@PathVariable Long id) {
		log.debug(PREFIX_LOG.concat("[/todo/delete/{id}] -> removeTodo"));
		log.debug(PREFIX_LOG.concat("[/id]:\n\t").concat(String.valueOf(id)));
		
		todoService.deleteTodoById(id);
		
		return "ok";
	}
}