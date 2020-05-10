package com.spring.security.jwt.securityconfig;

import org.springframework.data.repository.CrudRepository;

import com.spring.security.jwt.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmailIgnoreCase(String username);
	
	

}
