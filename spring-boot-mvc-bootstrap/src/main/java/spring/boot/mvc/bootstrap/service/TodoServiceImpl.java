package spring.boot.mvc.bootstrap.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.boot.mvc.bootstrap.form.model.TodoForm;
import spring.boot.mvc.bootstrap.model.Todo;
import spring.boot.mvc.bootstrap.model.User;
import spring.boot.mvc.bootstrap.repository.BaseRepository;
import spring.boot.mvc.bootstrap.repository.TodoRepository;

@Service
public class TodoServiceImpl extends BaseServiceImpl<Todo> implements TodoService {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TodoServiceImpl.class);
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private TodoRepository todoRepo;
	
	private UserService userService;
	
	@Autowired
	public TodoServiceImpl(TodoRepository todoRepo, UserService userService) {
		this.todoRepo = todoRepo;
		this.userService = userService;
	}

	private List<Todo> getTodosWithUser() {		
		List<Todo> todos = new ArrayList<Todo>();
		
		try {
			
			/*Query query = getEntityManager().createNamedQuery("getTodoWithUser");
			todos = (ArrayList<Todo>) query.getResultList();*/
			
			todos = this.todoRepo.getTodoWithUser();
			
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		return todos;
	}
	
	@Override
	public List<TodoForm> getAllForm() {
		List<TodoForm> todos = new ArrayList<TodoForm>();
		for (Todo todo : this.getTodosWithUser()) {
			TodoForm form = new TodoForm(todo.getId(), 
					todo.getTitle(), todo.getDescription(), sdf.format(todo.getCreationDate()));
			form.setEmail(todo.getUsuario().getEmail());
			form.setEmailId(todo.getUsuario().getId());
			todos.add(form);
		}
		return todos;
	}

	@Override
	public TodoForm saveTodo(TodoForm form) {
		// Recupera registro do usuario associado
		User user = userService.getById(form.getEmailId());
		
		Todo todo;
		try {
			todo = form.getEntityFromTodoForm();
			todo.setUsuario(user);
			
			// Atualiza o registro do usuario com registro do 'todo'
			user.setTodos(new ArrayList<Todo>());
			user.getTodos().add(todo);

			if (form.getId() != null) { // Atualiza o registro
				
				todo = this.update(todo);
				
			} else { // Cria o registro
				
				todo = this.create(todo);
				
			}

			// Define o objeto para exibicao na tela
			form = new TodoForm(todo.getId(), todo.getTitle(), todo.getDescription(), sdf.format(todo.getCreationDate()));
			form.setEmail(user.getEmail());
			form.setEmailId(user.getId());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return form;
	}
	
	@Override
	public BaseRepository<Todo, Long> getRepository() {
		return todoRepo;
	}
	
	private Todo getTodoWithUserByTodoId(Long id) {
		Todo todo = null;
		
		try {

			/*Query query = getEntityManager().createNamedQuery("getTodoWithUserByTodoId");
			query.setParameter("todoId", id);
			
			todo = (Todo) query.getSingleResult();*/
			
			todo = this.todoRepo.getTodoWithUserByTodoId(id);
			
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		return todo;
	}
	
	@Override
	public TodoForm getById(Long id) {
		Todo todo = this.getTodoWithUserByTodoId(id);
		
		TodoForm form = null;
		if (todo != null) {
			form = new TodoForm(todo.getId(), todo.getTitle(), todo.getDescription(), sdf.format(todo.getCreationDate()));
			form.setEmailId(todo.getUsuario().getId());
			form.setEmail(todo.getUsuario().getEmail());
		}
		
		return form;
	}
	
	@Override
	public void deleteTodoById(Long id) {
		this.delete(id);
	}
}