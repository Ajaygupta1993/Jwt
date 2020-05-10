package com.spring.security.jwt.domain;

import com.spring.security.jwt.model.User;

public class UserDto {
    private User user;
    private String token;
	public UserDto(User user, String token) {
		super();
		this.user = user;
		this.token = token;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
    
}
