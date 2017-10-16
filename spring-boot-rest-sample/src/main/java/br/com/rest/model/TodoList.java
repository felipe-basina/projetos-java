package br.com.rest.model;

import java.util.List;

public class TodoList {

	private List<Todo> todos;

	private int size;

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public TodoList(List<Todo> todos, int size) {
		super();
		this.todos = todos;
		this.size = size;
	}
	
}
