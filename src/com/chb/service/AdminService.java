package com.chb.service;

import javax.servlet.http.HttpSession;

import com.chb.entity.Admin;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.entity.User;

public interface AdminService {

	Admin findByAdminName(String adminname);
	//登录接口
	ResultMessage adminLogin(Admin admin,HttpSession session);
	//查询用户
	ResultMessage getUserListByPage(Page page);
	//删除用户
	ResultMessage deleteUserById(User user);
	


}
