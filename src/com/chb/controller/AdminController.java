package com.chb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chb.entity.Admin;
import com.chb.service.AdminService;

/**
 * 管理员登录类
 * @author 李卓宏
 *2017年12月6日
 */
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("adminLogin")           //账号密码登陆
	public void adminLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String adminname=request.getParameter("adminname");
		String password=request.getParameter("password");
		System.out.println("33333");
		
		Admin user=adminService.findByUserName(adminname);
		if(user!=null) {                                  //用户不为空
			if(user.getPassword().equals(password)) {       //密码正确
				response.getWriter().write("1");
			}else {                                        //密码不正确
				response.getWriter().write("2");
			}
		}else {                                             //用户为空
			response.getWriter().write("0");               // 用户名不存在
		}
	}
	

}
