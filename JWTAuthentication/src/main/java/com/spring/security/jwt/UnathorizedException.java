package com.spring.security.jwt;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnathorizedException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	protected static MessageSourceAccessor message = SpringSecurityMessageSource.getAccessor();
	
	public UnathorizedException() {
		super(message.getMessage("AbstractAccessDecisionManager.accessDenied","Access Is denied"));
	}
	public UnathorizedException(String message) {
		super(message);
	}
	
	

}
