package com.chb.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.User;
import com.chb.service.UserService;
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
}
