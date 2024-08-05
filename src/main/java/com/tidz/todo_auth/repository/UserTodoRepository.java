package com.tidz.todo_auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tidz.todo_auth.entity.UserTodo;

public interface UserTodoRepository extends JpaRepository<UserTodo, Long> {

	Optional<UserTodo> findByUsername(String username);
}
