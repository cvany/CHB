package com.chb.service;

import javax.servlet.http.HttpSession;

import com.chb.entity.Admin;
import com.chb.entity.ResultMessage;

public interface AdminService {
	// 管理员登录
	ResultMessage login(Admin admin,HttpSession session);
	// 管理员注销
	ResultMessage logout(HttpSession session);
	// 登录判断
	ResultMessage isLogin(HttpSession session);
}
