package com.tidz.todo_auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tidz.todo_auth.entity.Todo;
import com.tidz.todo_auth.entity.UserTodo;
import com.tidz.todo_auth.repository.TodoRepository;
import com.tidz.todo_auth.repository.UserTodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private UserTodoRepository userRepository;

	public List<Todo> getTodosByUser(String username) {
		UserTodo user = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		return this.todoRepository.findByUser(user);
	}

	public void addTodoForUser(String username, String title) {
		UserTodo user = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new IllegalArgumentException("User not found"));
		Todo todo = new Todo(title, user);
		this.todoRepository.save(todo);
	}

	public void completeTodoById(Long id) {
		Todo todo = this.todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Todo not found"));
		todo.setCompleted(true);
		this.todoRepository.save(todo);
	}

}
