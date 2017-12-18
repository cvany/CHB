package com.chb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Admin;
import com.chb.entity.Complaint;
import com.chb.entity.Goods;
import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;
import com.chb.entity.ShopInData;
import com.chb.entity.User;
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
	
	 //管理员账号密码登陆
	@RequestMapping("adminLogin")          
	@ResponseBody
	public ResultMessage adminLogin(String admin,HttpSession session ) throws Exception {
		System.out.println(admin+2);
		return adminService.adminLogin(JsonUtil.jsonToObject(admin, Admin.class),session);
	}
	
	// 通过用户名获取管理员信息
	@RequestMapping("adminIsLogin")
	@ResponseBody
	public ResultMessage adminIsLogin(HttpSession session) throws Exception {
		return adminService.adminIsLogin(session);
	}
	// 管理员注销接口
	@RequestMapping("adminLogout")
	@ResponseBody
	public ResultMessage adminLogout(HttpSession session) throws Exception {
		return adminService.adminLogout(session);
	}
	//查看管理日志
	@RequestMapping("managerLog")
	@ResponseBody
	public List<String> getManagerLog() {
		System.out.println("查询管理日志数据***");
		return adminService.getManagerLog();
	}
	// 查看总数量信息
	@RequestMapping("welcome")
	@ResponseBody
	public List<Long> getAllData() {
		System.out.println("查询管理日志数据");
		return adminService.getAllData();
	}
	// 增加数据分析结果
	@RequestMapping("setData")
	@ResponseBody
	public String setDataAnalysis(String data) {
		System.out.println(data);
		return adminService.setDataAnalysis(data);
	}
	//查看管理日志
	@RequestMapping("getDataAnalysis")
	@ResponseBody
	public List<String> getDataAnalysis() {
		System.out.println("查询数据分析结果数据***");
		return adminService.getDataAnalysis();
	}
}
