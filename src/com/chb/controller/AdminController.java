package com.chb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Admin;
import com.chb.entity.ResultMessage;
import com.chb.service.AdminService;
import com.chb.utils.JsonUtil;

/**
 * 管理员控制类
 * 
 * @author shilim
 *
 */
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;

	// 管理员登录接口
	@RequestMapping("admin/login")
	@ResponseBody
	public ResultMessage login(String admin, HttpSession session) throws Exception {
		return adminService.login(JsonUtil.jsonToObject(admin, Admin.class), session);
	}

	// 管理员注销接口
	@RequestMapping("admin/logout")
	@ResponseBody
	public ResultMessage logout(HttpSession session) throws Exception {
		return adminService.logout(session);
	}

	// 通过用户名获取管理员信息
	@RequestMapping("admin/isLogin")
	@ResponseBody
	public ResultMessage isLogin(HttpSession session) throws Exception {
		return adminService.isLogin(session);
	}
}
