package com.spring.security.jwt.securityconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.spring.security.jwt.model.JwtUSer;
import com.spring.security.jwt.model.User;

public class JwtUserFactory {

	public static JwtUSer create(User user) {
              System.out.println("=======================JwtUserFactory===========create==================================FFFFFFFFFFFFFFFFFFF=========");
		return new JwtUSer(user.getId(), user.getEmail(), user.getPassword(), user,
				mapToGrantedAuthorities(new ArrayList<String>(Arrays.asList("ROLE_" + user.getRole()))),
				user.isEnabled());
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
		
		return authorities.stream().map(Authority -> new SimpleGrantedAuthority(Authority)).collect(Collectors.toList());
	}

}
