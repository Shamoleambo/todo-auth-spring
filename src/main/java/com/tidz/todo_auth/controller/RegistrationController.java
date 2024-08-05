package com.tidz.todo_auth.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tidz.todo_auth.entity.UserTodo;
import com.tidz.todo_auth.repository.UserTodoRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserTodoRepository userTodoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String showRegistrationForm() {
		return "register";
	}

	@PostMapping
	public String registerUser(@RequestParam String username, @RequestParam String password) {
		UserTodo user = new UserTodo(username, this.passwordEncoder.encode(password),
				Collections.singletonList("ROLE_USER"));
		this.userTodoRepository.save(user);
		return "redirect:/login";
	}
}
