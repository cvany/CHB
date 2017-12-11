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
	//管理员分页查询所有待审核商家
	@RequestMapping("getBusinessInDataListByPage")          
	@ResponseBody
	public ResultMessage getBusinessInDataListByPage(String page ) throws Exception {
		System.out.println(page+"审核商家");
		return adminService.getBusinessInDataListByPage(JsonUtil.jsonToObject(page, Page.class));
	}
	//管理员分页查询所有待审核商家
	@RequestMapping("checkDetails")          
	@ResponseBody
	public ResultMessage checkDetailsById(String shopInData ) throws Exception {
		System.out.println(shopInData+"审核商家");
		return adminService.checkDetailsById(JsonUtil.jsonToObject(shopInData, ShopInData.class));
	}
	//管理员审核通过商家
	@RequestMapping("checkBusinessOk")
	@ResponseBody
	public ResultMessage checkBusinessOk(String shop) throws Exception {
		System.out.println(shop+"审核通过");
		return adminService.checkBusinessOkById(JsonUtil.jsonToObject(shop , Shop.class));
	}
	//管理员审核通过商家
	@RequestMapping("checkBusinessFail")
	@ResponseBody
	public ResultMessage checkBusinessFail(String shop) throws Exception {
		System.out.println(shop+"审核不通过");
		return adminService.checkBusinessFailById(JsonUtil.jsonToObject(shop , Shop.class));
	}
		

}
