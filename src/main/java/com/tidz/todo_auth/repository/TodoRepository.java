package com.tidz.todo_auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tidz.todo_auth.entity.Todo;
import com.tidz.todo_auth.entity.UserTodo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	List<Todo> findByUser(UserTodo userTodo);
}
