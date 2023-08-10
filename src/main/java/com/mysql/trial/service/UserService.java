package com.mysql.trial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.mysql.trial.repository.UserRepository;
import com.mysql.trial.entity.User;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	public User getByUsername(String username) {
		return userRepository.findByUsername(username);
		
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User deleteUser(String username) {
		return userRepository.deleteByUsername(username);
	}

	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}	
}
