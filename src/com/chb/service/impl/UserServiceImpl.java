package com.chb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.dao.UserDao;
import com.chb.entity.User;
import com.chb.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findByUserPhone(String phone) {
		return userDao.findByUserPhone(phone);
	}

	@Override
	public User findByUserEmail(String email) {
		
		return userDao.findByUserEmail(email);
	}

	@Override
	public void insertUser(User user) {
		
		 userDao.insertUser(user);		
	}

	@Override
	public void updateUser(User user) {
		
		userDao.updateUser(user);
		
	}

}

