package com.spring.security.jwt.service;

import java.util.List;

import com.spring.security.jwt.model.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	User findByEmail(String email);

}
