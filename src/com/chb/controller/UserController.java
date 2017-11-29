package com.chb.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.User;
import com.chb.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("findUser")
	@ResponseBody
	public String findUser() throws IOException{
		User user = userService.findByUsername("С��");
		System.out.println("����ִ������###############################"+user.getPassword());
		return user.getPassword()+"������";
	}
	
	@RequestMapping("test")
	public void test(User user){
		System.out.println(user.toString());
	}
}
