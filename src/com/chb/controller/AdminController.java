package com.chb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Admin;
import com.chb.entity.ResultMessage;
import com.chb.service.AdminService;
import com.chb.utils.JsonUtil;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/**
 * 管理员登录类
 * @author 李卓宏
 *2017年12月6日
 */
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("adminLogin")           //管理员账号密码登陆
	@ResponseBody
	public ResultMessage adminLogin(String admin,HttpSession session ) throws Exception {
		System.out.println(admin+2);
		return adminService.adminLogin(JsonUtil.jsonToObject(admin, Admin.class),session);
	}
	

}
