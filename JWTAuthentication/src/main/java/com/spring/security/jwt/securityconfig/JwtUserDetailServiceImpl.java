package com.spring.security.jwt.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.jwt.model.User;
@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userrepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("================JwtUserDetailServiceImpl=================loadUserByUsername=================EEEEEEEEEEEEEEEEEEEEEEEE===");
		User user=userrepository.findByEmailIgnoreCase(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("User not found '%s'. ", username));
		}
		else {
			return JwtUserFactory.create(user);
		}
		
		
	}
	

}
