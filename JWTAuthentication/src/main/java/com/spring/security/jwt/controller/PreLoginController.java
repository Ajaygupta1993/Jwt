package com.spring.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.jwt.domain.Responce;
import com.spring.security.jwt.model.User;
import com.spring.security.jwt.service.UserService;

@RestController
public class PreLoginController {
	@Autowired 
	private UserService userservice;
	
	@PostMapping(value="/registration")
	public ResponseEntity<Responce> rigistration(@RequestBody User user){
		User dbUser=userservice.save(user);
		
		if(dbUser !=null) {
			return new ResponseEntity<Responce>(new Responce("User Saved sucessfully"),HttpStatus.OK);
		}
		return null;
		
	}
	
	

}
