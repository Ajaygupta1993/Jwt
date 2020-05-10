package com.spring.security.jwt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.jwt.UnathorizedException;
import com.spring.security.jwt.domain.UserDto;
import com.spring.security.jwt.interceptor.JwtTokanUtil;
import com.spring.security.jwt.model.JwtUSer;
import com.spring.security.jwt.model.User;

@RestController
public class AuthenticationController {
	
	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokanUtil jwtTokanUtil;
	
	@PostMapping(value="/login")
	public ResponseEntity<UserDto> login(@RequestBody User user, HttpServletRequest request,HttpServletResponse responce){
		try {
			System.out.println("=============AuthenticationController======AAAAAAAAAAAAa==============loginMethod");
			Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			System.out.println("=============AuthenticationController======BBBBBBBBBBBBBBBBBB==============loginMethod");
			final JwtUSer userDetails=(JwtUSer) authentication.getPrincipal();
			System.out.println("=============AuthenticationController======CCCCCCCCCCCCCCCCCCC==============loginMethod");
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final String token=jwtTokanUtil.genrateToken(userDetails);
			responce.setHeader("token", token);
			return new ResponseEntity<UserDto>(new UserDto(userDetails.getUser(), token),HttpStatus.OK);
			
		}catch (Exception e) {
			throw new UnathorizedException(e.getMessage());
		}
		
		
	}

}
