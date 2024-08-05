package com.tidz.todo_auth.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tidz.todo_auth.entity.UserTodo;
import com.tidz.todo_auth.repository.UserTodoRepository;

@Service
public class CustomUserDetailsService {

	@Autowired
	private UserTodoRepository userTodoRepository;

	public UserDetails loadUserbyUsername(String username) throws UsernameNotFoundException {
		Optional<UserTodo> userOptional = this.userTodoRepository.findByUsername(username);
		UserTodo user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return new User(user.getUsername(), user.getPassword(),
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
	}
}
