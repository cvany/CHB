package com.chb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chb.entity.Page;
import com.chb.entity.User;

/**
 * 用户dao层接口
 * 
 * @author 崔文元 2017年12月4日
 */
@Repository("userDao")
public interface UserDao {
	User findByUsername(String username);
	
	User findByUserPhone(String phone);

	// 条件分页查询总条数
	Long selectCount(Page page);

	// 条件分页查询用户列表
	List<User> selectUserByPage(Page page);
}
