package com.chb.dao;

import org.springframework.stereotype.Repository;

import com.chb.entity.User;
/**
 * 用户dao层接口
 * @author 崔文元
 * 2017年12月4日
 */
@Repository("userDao")
public interface UserDao {
	User findByUsername(String username);

}
