package com.cts.moviecruiserauthenticationapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.moviecruiserauthenticationapp.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findByUserIdAndPassword(String userId,String password);
}
