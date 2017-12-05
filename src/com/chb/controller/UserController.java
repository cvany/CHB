package com.chb.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Page;
import com.chb.entity.ResultMessage;
import com.chb.entity.User;
import com.chb.service.UserService;
import com.chb.utils.JsonUtil;
/**
 * 用户控制类
 * @author 崔文元
 *2017年12月4日
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("findUser")
	@ResponseBody
	public String findUser() throws IOException{
		User user = userService.findByUsername("小花");
		return user.getPassword();
	}
	
	@RequestMapping("test")
	public void test(User user){
		System.out.println(user.toString());
	}
	
	@RequestMapping("getUserListByPage")
	@ResponseBody
	public ResultMessage getUserByPage(String page) throws Exception {
		return userService.getUserListByPage(JsonUtil.jsonToObject(page, Page.class));
	}
}
