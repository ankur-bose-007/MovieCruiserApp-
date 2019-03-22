package com.cts.moviecruiserauthenticationapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.moviecruiserauthenticationapp.exceptions.UserAlreadyExistsException;
import com.cts.moviecruiserauthenticationapp.exceptions.UserNotFoundException;
import com.cts.moviecruiserauthenticationapp.model.User;
import com.cts.moviecruiserauthenticationapp.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> existingUser = userRepository.findById(user.getUserId());
		if (existingUser.isPresent()) {
			throw new UserAlreadyExistsException("User with id already exists");
		}
		userRepository.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = userRepository.findByUserIdAndPassword(userId, password);
		if (null == user) {
			throw new UserNotFoundException("UserId and Password mismatch");
		}
		return user;
	}

}
