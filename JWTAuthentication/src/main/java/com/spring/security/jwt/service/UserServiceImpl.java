package com.spring.security.jwt.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.jwt.model.User;
import com.spring.security.jwt.securityconfig.UserRepository;
import com.spring.security.jwt.util.PasswordUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;

	@Override
	public User save(User user) {
		String password = PasswordUtil.getPasswordHash(user.getPassword());
		user.setPassword(password);
		user.setCreatedDate(new Date());

		return userRepo.save(user);
	}

	@Override
	public List<User> findAll() {

		return (List<User>) userRepo.findAll();
	}

	@Override
	public User findByEmail(String email) {
		
		return userRepo.findByEmailIgnoreCase(email);
	}

}
