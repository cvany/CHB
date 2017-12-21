package com.chb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.entity.User;
import com.chb.service.AdminService;
import com.chb.utils.JsonUtil;
/**
 * 管理员管理用户类
 * @author 李卓宏
 *2017年12月7日
 */
@Controller
public class AdminManageUserController {

	@Autowired
	private AdminService adminService;
	
	//分页查询所有用户
	@RequestMapping("getUserListByPage")
	@ResponseBody
	public ResultMessage getUserListByPage(String page) throws Exception {
		System.out.println(page+"页面");
		return adminService.getUserListByPage(JsonUtil.jsonToObject(page, Page.class));
	}
	//管理员删除用户
	@RequestMapping("adminDeleteUser")
	@ResponseBody
	public ResultMessage adminDeleteUser(String user) throws Exception {
		System.out.println(user+"删除用户控制");
		return adminService.deleteUserById(JsonUtil.jsonToObject(user , User.class));
	}
}
