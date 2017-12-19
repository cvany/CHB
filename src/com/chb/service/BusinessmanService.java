package com.chb.service;

import javax.servlet.http.HttpSession;

import com.chb.entity.Businessman;
import com.chb.entity.ResultMessage;

public interface BusinessmanService {
	// 商家登录
	ResultMessage login(Businessman businessman,HttpSession session);
	// 商家注销
	ResultMessage logout(HttpSession session);
	// 登录判断
	ResultMessage isLogin(HttpSession session);
	// 通过用户名查询商家 
	Businessman findByBusinessmanName(String businessmanName);
	// 通过手机号查询商家
	Businessman findByBusinessmanPhone(String phone);
	//存入商家
	void insertBusinessman(Businessman businessman);
	//根据id查找
	Businessman findById(int id);
}
