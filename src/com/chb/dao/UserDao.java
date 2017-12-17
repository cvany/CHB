package com.chb.dao;

import org.springframework.stereotype.Repository;

import com.chb.entity.User;
/**
 * 用户dao层接口
 * @author 黄雨晨
 * 2017年12月4日
 */
@Repository("userDao")
public interface UserDao {
	// 通过id查询用户名
	User selectUserNameByPrimaryKey(Integer id);
	
	User findByUsername(String username);
	
	User findByUserPhone(String phone);
	
	User findByUserEmail(String email);
	
	void insertUser(User user);
	
	void updateUser(User user);

}
