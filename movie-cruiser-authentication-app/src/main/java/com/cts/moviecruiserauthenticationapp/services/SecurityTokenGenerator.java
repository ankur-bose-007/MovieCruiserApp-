package com.cts.moviecruiserauthenticationapp.services;

import java.util.Map;

import com.cts.moviecruiserauthenticationapp.model.User;

public interface SecurityTokenGenerator {
	Map<String,String> generateToken(User user);
}
