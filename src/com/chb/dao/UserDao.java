package com.chb.dao;

import org.springframework.stereotype.Repository;

import com.chb.entity.User;

@Repository("userDao")
public interface UserDao {
	User findByUsername(String username);

}
