package com.chb.service;

import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.entity.User;

public interface UserService {
	User findByUsername(String username);
	
	User findByUserPhone(String phone);
	
	// 分页查询用户列表
	ResultMessage getUserListByPage(Page page);
}
