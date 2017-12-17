package com.chb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.service.AdminService;
import com.chb.utils.JsonUtil;
/**
 * 管理员管理数据类
 * @author 李卓宏
 *2017年12月14日
 */
@Controller
public class AdminManageDataController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("userData")
	@ResponseBody
	public List<Long> getUserData() {
		System.out.println("查询用户数据***");
		return adminService.getUserData();
	}
}
