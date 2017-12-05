package com.chb.service;

import com.chb.entity.User;

public interface UserService {
	User findByUsername(String username);
	
	User findByUserPhone(String phone);
}
