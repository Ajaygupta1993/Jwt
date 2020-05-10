package com.spring.security.jwt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.jwt.model.User;
import com.spring.security.jwt.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userservice;

	@GetMapping(value = "/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userservice.findAll();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getUsers")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<User> getUsers(Principal principal) {
		User singleUser = userservice.findByEmail(principal.getName());
		return new ResponseEntity<User>(singleUser, HttpStatus.OK);
	}

	
	
	

}
