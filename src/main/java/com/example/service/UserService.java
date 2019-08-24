package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}
	
	public Optional<User> findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}
	
	public User create(User user) {
		return userRepository.save(user);
	}
	
	public User update(User user) {
		return userRepository.save(user);
	}
	
	public void updatePasswordByUserId(User user) {
		userRepository.updatePasswordByUserId(user.getUserId(), user.getPassword());
	}
	
	public void deleteById(String id) {
		userRepository.deleteById(id);
	}

}
