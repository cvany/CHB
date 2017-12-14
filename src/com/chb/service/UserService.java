package com.chb.service;

import com.chb.entity.User;

public interface UserService {
	User findByUsername(String username);
	
	User findByUserPhone(String phone);
	
	User findByUserEmail(String email);
	
	void insertUser(User user);
	
	void updateUser(User user);
}

