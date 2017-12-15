package com.chb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.chb.service.AdminService;
/**
 * 管理员管理数据类
 * @author 李卓宏
 *2017年12月14日
 */
@Controller
public class AdminManageDataController {
	@Autowired
	private AdminService adminService;
}
