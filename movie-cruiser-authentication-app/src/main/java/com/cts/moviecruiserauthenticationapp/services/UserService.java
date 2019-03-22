package com.cts.moviecruiserauthenticationapp.services;

import com.cts.moviecruiserauthenticationapp.exceptions.UserAlreadyExistsException;
import com.cts.moviecruiserauthenticationapp.exceptions.UserNotFoundException;
import com.cts.moviecruiserauthenticationapp.model.User;

public interface UserService {
	boolean saveUser(User user) throws UserAlreadyExistsException;

	User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException;
}
