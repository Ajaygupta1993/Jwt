package com.spring.security.jwt.domain;

import java.io.Serializable;

public class Responce implements Serializable {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public Responce(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
