package com.tidz.todo_auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tidz.todo_auth.entity.Todo;
import com.tidz.todo_auth.entity.UserTodo;
import com.tidz.todo_auth.repository.TodoRepository;
import com.tidz.todo_auth.repository.UserTodoRepository;

@Controller
@RequestMapping("/todos")
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

	private UserTodoRepository userTodoRepository;

	@GetMapping
	public String listTodos(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		UserTodo user = this.userTodoRepository.findByUsername(userDetails.getUsername()).orElse(null);
		List<Todo> todos = this.todoRepository.findByUser(user);
		model.addAttribute("todos", todos);
		return "todos";
	}

	@PostMapping
	public String addTodo(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String title) {
		UserTodo user = this.userTodoRepository.findByUsername(userDetails.getUsername()).orElse(null);
		Todo todo = new Todo(title, user);
		this.todoRepository.save(todo);
		return "redirect:/todos";
	}

	@PostMapping("/complete")
	public String completeTodo(@RequestParam Long id) {
		Todo todo = this.todoRepository.findById(id).orElse(null);
		if (todo != null) {
			todo.setCompleted(true);
			this.todoRepository.save(todo);
		}
		return "redirect:/todos";
	}
}
