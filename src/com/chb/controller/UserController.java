package com.chb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.User;
import com.chb.service.UserService;
import com.chb.utils.*;
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
	

	
	@RequestMapping("sendEmail")
	public void sendEmail(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		
		
		User user=userService.findByUsername(username);
		
		if(user==null) {         //用户为空
			response.getWriter().write("null");
		}else {                    //用户不为空
			if(user.getEmail().equals(email)) {    //0  匹配  ，发送邮箱    
				response.getWriter().write("0");  
				String randomPass=String.valueOf((int)((Math.random()*9+1)*100000));  //生成6位随机数
				System.out.println(randomPass);
				sendEmail send = new sendEmail();
				send.send(email, randomPass);
			}else {                               //1 不匹配
				response.getWriter().write("1");
				
			}
		}
			
	}
	
	@RequestMapping("sendCode")                    //手机短信验证登陆
	public void sendCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String phoneNum=request.getParameter("phoneNum");
		User user=userService.findByUserPhone(phoneNum);
		
		if(user==null) {                               //手机用户为空
		   response.getWriter().write("0");
		}else {                                        //手机用户不为空，发短信
			HttpClientUtil sendCode =HttpClientUtil.getInstance();
			String randomCode=String.valueOf((int)((Math.random()*9+1)*100000));  //生成6位随机数
			System.out.println(randomCode);
			
			String context="你的验证码是"+randomCode;
			int result=sendCode.sendMsgUtf8(context, phoneNum);
			
			if(result>0) {                    //发送成功
				response.getWriter().write(randomCode);   //1 成功发送验证码
			}
		}
		
		
	}
	
	@RequestMapping("passLogin")           //账号密码登陆
	public void passLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		User user=userService.findByUsername(username);
		if(user!=null) {                                  //用户不为空
			if(user.getPassword().equals(password)) {       //密码正确
				response.getWriter().write("1");
			}else {                                        //密码不正确
				response.getWriter().write("2");
			}
		}else {                                             //用户为空
			response.getWriter().write("0");               //0  用户名不存在
		}
	}
	

}
