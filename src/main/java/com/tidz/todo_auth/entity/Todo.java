package com.tidz.todo_auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private boolean completed;

	@ManyToOne
	@JoinColumn(name = "user_todo_id")
	private UserTodo userTodo;

	public Todo() {

	}

	public Todo(String title, UserTodo userTodo) {
		this.title = title;
		this.userTodo = userTodo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public UserTodo getUserTodo() {
		return userTodo;
	}

	public void setUserTodo(UserTodo userTodo) {
		this.userTodo = userTodo;
	}

}
